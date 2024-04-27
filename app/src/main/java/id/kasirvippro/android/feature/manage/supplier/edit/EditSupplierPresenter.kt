package id.kasirvippro.android.feature.manage.supplier.edit

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.SupplierSQL
import id.kasirvippro.android.sqlite.Model.SupplierSQLUpdate
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.Helper

class EditSupplierPresenter(val context: Context, val view: EditSupplierContract.View) : BasePresenter<EditSupplierContract.View>(),
    EditSupplierContract.Presenter, EditSupplierContract.InteractorOutput {


    private var interactor = EditSupplierInteractor(this)
    private var restModel = SupplierRestModel(context)
    private var data : Supplier ?= null


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as Supplier
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            view.setSupplier(it.name_supplier,it.email,it.telephone,it.address)
        }

    }

    override fun onCheck(name:String,email:String,phone:String,address:String) {
        if(name.isNullOrBlank() || name.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name))
            return
        }

/*        if(!email.isBlank() && email.isNotEmpty()){
            if(!Helper.isEmailValid(email)){
                view.showMessage(999,context.getString(R.string.err_email_format))
                return
            }
        }*/

        if(phone.isNullOrBlank() || phone.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_phone))
            return
        }

        if(address.isNullOrBlank() || address.isNullOrEmpty()){
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
                    interactor.callEditSupplierAPI(context,restModel,data?.id_supplier!!,name,email,phone,address)
                    return
                }
            }
        }
        val supplierUpdate = SupplierSQLUpdate(
            data?.inc!!,
            key!!,
            data?.id_supplier!!,
            name,
            phone,
            email,
            address
        )
        val supplier =
            SupplierSQL(
                data?.inc!!,
                key!!,
                data?.id_supplier!!,
                name,
                phone,
                email,
                address
            )
        var result = dataManager.addSupplierUpdate(supplierUpdate!!)
        var result2 = dataManager.updateSupplier(supplier!!)
        Toast.makeText(context,"Update Local", Toast.LENGTH_SHORT).show()
        (context as EditSupplierActivity)
        view.onClose(Activity.RESULT_OK)
        (context as Activity).finish()


    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessEditSupplier(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedEditSupplier(code: Int, msg: String) {
        view.showMessage(code,msg)
    }



}