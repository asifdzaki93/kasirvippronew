package id.kasirvippro.android.feature.addOn.workManagement.dataHistory

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.DetailJob
import id.kasirvippro.android.models.transaction.TransactionRestModel


interface DataHistoryContract {

    interface View : BaseViewImpl {
        fun setProducts(list:List<DetailJob>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun getIdProduct(): String?
        fun setInfo(name:String?,date:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadProducts()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetProductsAPI(context: Context, restModel:TransactionRestModel, phone_number:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetProducts(list:List<DetailJob>)
        fun onSuccessDeleteProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}