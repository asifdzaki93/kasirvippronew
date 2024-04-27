package id.kasirvippro.android.feature.printerLabel

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.BluetoothCallback
import id.kasirvippro.android.models.transaction.DetailLabel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.BluetoothConnectTask
import id.kasirvippro.android.utils.BluetoothStateReceiver
import id.kasirvippro.android.utils.BluetoothUtil
import id.kasirvippro.android.utils.print.PrinterLabelUtil
import java.io.IOException

class PrinterLabelPresenter(val context: Context, val view: PrinterLabelContract.View) : BasePresenter<PrinterLabelContract.View>(),
    PrinterLabelContract.Presenter, PrinterLabelContract.InteractorOutput {

    private var interactor = PrinterLabelInteractor(this)
    private var data:DetailLabel?=null
    private var bluetoothCallback: BluetoothCallback? = null
    private var bluetoothSocket: BluetoothSocket? = null
    private var bluetoothStateReceiver: BluetoothStateReceiver? = null
    private var connectBluetoothTask: BluetoothConnectTask? = null
    private var connectedDevice: BluetoothDevice? = null
    private var logo: Drawable? = null
    private var premium:Boolean = false


    override fun onViewCreated(intent: Intent) {
        premium = "1" == interactor.getUserPaket(context)

        bluetoothCallback = object : BluetoothCallback{
            override fun onPowerOn(intent: Intent?) {
                if (BluetoothUtil.isBluetoothOn()) {
                    checkDevice()
                } else {
                    view.showEmpty()
                }
            }

            override fun onPowerOff(intent: Intent?) {
                view.showEmpty()
            }

            override fun onConnected(socket: BluetoothSocket?, taskType: Int) {
                bluetoothSocket = socket
                view.hideLoadingDialog()
                PrinterLabelUtil.print(bluetoothSocket, data, null, premium)
            }

            override fun onError(msg: String?) {
                view.hideLoadingDialog()
                view.showToast(msg!!)
            }
        }

        data = intent.getSerializableExtra(AppConstant.DATA) as DetailLabel
        logo = ContextCompat.getDrawable(context,R.drawable.logo_print)
        bluetoothStateReceiver = BluetoothStateReceiver(bluetoothCallback)
        val filter = IntentFilter()
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        context.registerReceiver(bluetoothStateReceiver, filter)
        checkDevice()
    }

    override fun onDestroy() {
        interactor.onDestroy()
        clearBluetoothSocket()
        cancelBluetoothConnectTask()
    }

    override fun checkDevice() {
        val devices = BluetoothUtil.getPairedDevices()
        if (devices == null) {
            view.showEmpty()
            return
        }
        if (devices.isEmpty()) {
            view.showEmpty()
            return
        }
        view.clearList()
        view.showContent()
        view.addAll(devices)
    }

    override fun onPrint() {
        if(view.onSelectedDevice() == null){
            view.showToast("Failed to connect printer")
            return
        }
        if (connectedDevice === view.onSelectedDevice() && bluetoothSocket != null) {
            PrinterLabelUtil.print(bluetoothSocket, data, null, premium)
        } else {
            view.showLoadingDialog()
            connectBluetoothTask =
                BluetoothConnectTask(bluetoothCallback, 1, "Failed to connect printer")
            connectBluetoothTask!!.execute(view.onSelectedDevice())
            connectedDevice = view.onSelectedDevice()
        }
    }

    private fun clearBluetoothSocket() {
        try {
            if (bluetoothSocket != null) {
                bluetoothSocket!!.close()
            }

        } catch (e: IOException) {
            e.printStackTrace()
            bluetoothSocket = null
        }

    }

    private fun cancelBluetoothConnectTask() {
        if (connectBluetoothTask != null) {
            connectBluetoothTask!!.cancel(true)
            connectBluetoothTask = null
        }

    }
}