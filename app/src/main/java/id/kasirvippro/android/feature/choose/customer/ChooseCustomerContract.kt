package id.kasirvippro.android.feature.choose.customer

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel


interface ChooseCustomerContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Customer>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun onSelected(data:Customer)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
        fun search(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDataAPI(context: Context, restModel:CustomerRestModel)
        fun callSearchAPI(context: Context, restModel:CustomerRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetData(list:List<Customer>)
        fun onFailedAPI(code:Int,msg:String)
    }


}