package id.kasirvippro.android.feature.sell.addCustomer

import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel


class AddCustomerPresenter(val context: Context, val view: AddCustomerContract.View) : BasePresenter<AddCustomerContract.View>(),
    AddCustomerContract.Presenter, AddCustomerContract.InteractorOutput {

    private var interactor = AddCustomerInteractor(this)
    private var restModel = CustomerRestModel(context)

    override fun onViewCreated() {

    }

    override fun onCheck(name:String,email:String,phone:String,address:String) {
        if(name.isNullOrBlank() || name.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name))
            return
        }

/*        if(email.isNullOrBlank() || email.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_email))
            return
        }

        if(!Helper.isEmailValid(email)){
            view.showMessage(999,context.getString(R.string.err_email_format))
            return
        }*/

        if(phone.isNullOrBlank() || phone.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_phone))
            return
        }

        if(!Helper.isPhoneValid(phone)){
            view.showMessage(999,context.getString(R.string.err_phone_format))
            return
        }

        if(address.isNullOrBlank() || address.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_address))
            return
        }

        interactor.callAddCustomerAPI(context,restModel,name,email,phone,address)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessAdd(data:List<Customer>) {
        if(data.isEmpty()){
            onFailedAPI(999,"There is an error")
            return
        }
        view.onSuccess(data[0])
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }
}