package id.kasirvippro.android.feature.closeShift.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.closeShift.CloseShift
import id.kasirvippro.android.models.closeShift.CloseShiftRestModel

interface CloseShiftListContract {

    interface View : BaseViewImpl {
        fun onClose(status:Int)
        fun setData(list:List<CloseShift>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun showMessage(code: Int, msg: String?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
        fun deleteDetail()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetsAPI(context: Context, restModel:CloseShiftRestModel)
        fun callDeleteDetailAPI(context: Context, restModel: CloseShiftRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGets(list:List<CloseShift>)
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessDeleteDetail(message: String?)
    }


}