package id.kasirvippro.android.feature.shift.closingShift.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.closeShift.CloseShift
import id.kasirvippro.android.models.closeShift.CloseShiftRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.BluetoothUtil
import id.kasirvippro.android.utils.ImageHelper
import id.kasirvippro.android.utils.PermissionUtil

class ClosingShiftDetailPresenter(val context: Context, val view: ClosingShiftDetailContract.View) : BasePresenter<ClosingShiftDetailContract.View>(),
    ClosingShiftDetailContract.Presenter, ClosingShiftDetailContract.InteractorOutput {


    private var interactor = ClosingShiftDetailInteractor(this)
    private var restModel = CloseShiftRestModel(context)
    private var data:CloseShift? = null
    private lateinit var storagePermission: PermissionCallback
    private val permissionUtil = PermissionUtil(context)
    //private var id: String? = null
    private lateinit var bluetoothPermission:PermissionCallback


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as CloseShift
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            view.setData(it.cash_actual,it.name_cashier,it.initial_date,it.cash_awal,it.sales_debt,it.sales_return,it.variance,it.ppn,it.sc,it.sales_non_cash,it.shift,it.flag,it.sales_cash,it.status,it.total_sales)
        }


        storagePermission = object :PermissionCallback{
            override fun onSuccess() {
                ImageHelper.takeScreenshot(context,view.getParentLayout())
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_write_external))
            }
        }

        bluetoothPermission = object : PermissionCallback{
            override fun onSuccess() {

                if(BluetoothUtil.isBluetoothOn()){
                    view.openPrinterPage()
                }
                else{
                    BluetoothUtil.openBluetooth(context)
                }

            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_bluetooth))
            }

        }
    }

    override fun onCheck(et_pemasukan:String) {

        if(et_pemasukan.isBlank() || et_pemasukan.isEmpty() || "0" == et_pemasukan){
            view.showMessage(999,"cash actual is required")
            return
        }


        interactor.callEditAPI(context,restModel,data?.id!!,et_pemasukan)
    }

    override fun onCheckShare() {
        permissionUtil.checkWriteExternalPermission(storagePermission)
    }

    override fun getDataStruk(): CloseShift {
        return data!!
    }


    override fun dailyClose() {
        interactor.callDailyClosingAPI(context,restModel,data?.id!!)
    }

    override fun onCheckBluetooth() {
        permissionUtil.checkBluetoothPermission(bluetoothPermission)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessDailyClosing(message: String?) {
        view.showMessage(999,message)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onSuccessEdit(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

}