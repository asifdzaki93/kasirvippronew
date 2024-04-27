package id.kasirvippro.android.feature.manage.customer.add

import android.app.Activity
import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.CustomerSQL
import id.kasirvippro.android.sqlite.Model.CustomerSQLAdd
import id.kasirvippro.android.utils.AppSession


class AddCustomerPresenter(val context: Context, val view: AddCustomerContract.View) : BasePresenter<AddCustomerContract.View>(),
    AddCustomerContract.Presenter, AddCustomerContract.InteractorOutput {

    private var interactor = AddCustomerInteractor(this)
    private var restModel = CustomerRestModel(context)



    override fun onViewCreated() {

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
                    interactor.callAddCustomerAPI(context,restModel,name,email,phone,address,customercode)
                    return
                }
            }
        }
        val customerAdd =
            CustomerSQLAdd(
                "0",
                key!!,
                name,
                phone,
                email,
                address,
                customercode
            )
        val customer =
            CustomerSQL(
                "0",
                "0",
                key!!,
                name,
                phone,
                email,
                address,
                customercode
            )
        var result = dataManager.addCustomerAdd(customerAdd!!)
        var result2 = dataManager.addCustomer(customer!!)
        Toast.makeText(context,"Add Local", Toast.LENGTH_SHORT).show()
        (context as AddCustomerActivity).hideLoading()
        view.onClose(Activity.RESULT_OK)
        (context as Activity).finish()

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessAddCustomer(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAddCustomer(code: Int, msg: String) {
        view.showMessage(code,msg)
    }
}