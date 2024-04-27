package id.kasirvippro.android.feature.shift.closingShift.detail

import android.content.Context
import android.content.Intent
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.closeShift.CloseShift
import id.kasirvippro.android.models.closeShift.CloseShiftRestModel

interface ClosingShiftDetailContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setData(cash_actual:String?, name_cashier:String?, initial_date:String?, cash_awal:String?, sales_debt:String?, sales_return:String?, variance:String?, ppn:String?, sc:String?, sales_non_cash:String?, shift:String?,flag:String?,sales_cash:String?,status:String?,total_sales:String?)
        fun getParentLayout(): NestedScrollView
        fun openPrinterPage()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onCheckShare()
        fun dailyClose()
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun onCheck(pemasukan:String)
        fun onCheckBluetooth()
        fun getDataStruk(): CloseShift
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callEditAPI(context: Context, model:CloseShiftRestModel, id:String,cash_actual:String)
        fun callDailyClosingAPI(context: Context, model: CloseShiftRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessDailyClosing(message: String?)
        fun onSuccessEdit(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}