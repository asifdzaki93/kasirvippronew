package id.kasirvippro.android.feature.addOn.workManagement.transaction

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.DetailJob
import id.kasirvippro.android.models.transaction.TransactionRestModel


interface TransactionListContract {

    interface View : BaseViewImpl {
        fun setData(list:List<DetailJob>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openEditPage(data:DetailJob)
        fun getIdProduct(): String?
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDataAPI(context: Context, restModel:TransactionRestModel, operator:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetData(list:List<DetailJob>)
        fun onFailedAPI(code:Int,msg:String)
    }


}