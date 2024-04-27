package id.kasirvippro.android.feature.manage.customer.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel


interface CustomerListContract {

    interface View : BaseViewImpl {
        fun setCustomers(list:List<Customer>)
        fun setData(list:List<Customer>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openAddPage()
        fun openEditPage(data:Customer)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadCustomers()
        fun deleteCustomer(id:String,position:Int,increment:String)
        fun searchCustomer(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetCustomersAPI(context: Context, restModel:CustomerRestModel)
        fun callDeleteCustomerAPI(context: Context, restModel:CustomerRestModel, id:String)
        fun callSearchCustomerrAPI(context: Context, restModel:CustomerRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetCustomers(list:List<Customer>)
        fun onSuccessDeleteCustomer(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}