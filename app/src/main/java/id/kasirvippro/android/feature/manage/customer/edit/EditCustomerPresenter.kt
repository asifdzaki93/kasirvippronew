package id.kasirvippro.android.feature.manage.customer.edit

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.CustomerSQL
import id.kasirvippro.android.sqlite.Model.CustomerSQLUpdate
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.Helper

class EditCustomerPresenter(val context: Context, val view: EditCustomerContract.View) : BasePresenter<EditCustomerContract.View>(),
    EditCustomerContract.Presenter, EditCustomerContract.InteractorOutput {

    private var interactor = EditCustomerInteractor(this)
    private var restModel = CustomerRestModel(context)
    private var data : Customer ?= null
    private var newdata : Customer ?= null



    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as Customer
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            view.setCustomer(it.name_customer,it.email,it.telephone,it.address,it.customercode)
        }
    }

    override fun onCheck(name:String,email:String,phone:String,address:String,customercode:String) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name))
            return
        }

        if(phone.isBlank() || phone.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_phone))
            return
        }

        if(address.isBlank() || address.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_address))
            return
        }

        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context?.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val dataManager = DataManager (context)
        val appSession = AppSession()
        val key = appSession.getToken(context)

        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    newdata = Customer()
                    newdata?.id_customer = data?.id_customer
                    newdata?.name_customer = name
                    newdata?.email = email
                    newdata?.telephone = phone
                    newdata?.address = address
                    newdata?.customercode = customercode
                    interactor.callEditCustomerAPI(context,restModel,data?.id_customer!!,name,email,phone,address,customercode)
                    return
                }
            }
        }
        val customerUpdate = CustomerSQLUpdate(
            data?.inc!!,
            key!!,
            data?.id_customer!!,
            name,
            phone,
            email,
            address,
            customercode
        )
        val customer =
            CustomerSQL(
                data?.inc!!,
                key!!,
                data?.id_customer!!,
                name,
                phone,
                email,
                address,
                customercode
            )
        var result = dataManager.addCustomerUpdate(customerUpdate!!)
        var result2 = dataManager.updateCustomer(customer!!)
        Toast.makeText(context,"Update Local", Toast.LENGTH_SHORT).show()
        (context as EditCustomerActivity)
        view.onClose(Activity.RESULT_OK)
        (context as Activity).finish()


    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessEditCustomer(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedEditCustomer(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

}