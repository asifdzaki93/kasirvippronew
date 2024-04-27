package id.kasirvippro.android.feature.shift.closingShift.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.closeShift.CloseShift
import id.kasirvippro.android.models.closeShift.CloseShiftRestModel

interface ClosingShiftListContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setData(list:List<CloseShift>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openEditPage(data:CloseShift)
        fun openPrinterPage()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onCheckBluetooth()
        fun dailyClose()
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
        fun getDataStruk(): CloseShift
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetsAPI(context: Context, restModel:CloseShiftRestModel)
        fun callDailyClosingAPI(context: Context, model: CloseShiftRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessDailyClosing(message: String?)
        fun onSuccessGets(list:List<CloseShift>)
        fun onFailedAPI(code:Int,msg:String)
    }


}