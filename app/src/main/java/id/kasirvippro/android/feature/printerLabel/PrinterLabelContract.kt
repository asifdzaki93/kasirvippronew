package id.kasirvippro.android.feature.printerLabel

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl

interface PrinterLabelContract {

    interface View : BaseViewImpl {
        fun onClose()
        fun openBluetoothSetting()
        fun showEmpty()
        fun showContent()
        fun addAll(data: List<BluetoothDevice>)
        fun clearList()
        fun onSelectedDevice(): BluetoothDevice?
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun checkDevice()
        fun onPrint()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context): String?

    }

    interface InteractorOutput : BaseInteractorOutputImpl {

    }


}