package id.kasirvippro.android.feature.manage.supplier.add

import android.app.Activity
import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.SupplierSQL
import id.kasirvippro.android.sqlite.Model.SupplierSQLAdd
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.Helper


class AddSupplierPresenter(val context: Context, val view: AddSupplierContract.View) : BasePresenter<AddSupplierContract.View>(),
    AddSupplierContract.Presenter, AddSupplierContract.InteractorOutput {

    private var interactor = AddSupplierInteractor(this)
    private var restModel = SupplierRestModel(context)


    override fun onViewCreated() {

    }



    override fun onCheck(
        name: String,
        email: String,
        telpon: String,
        alamat: String
    ) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name))
            return
        }

/*        if(!email.isBlank() && email.isEmpty()){
            if(!Helper.isEmailValid(email)){
                view.showMessage(999,context.getString(R.string.err_email_format))
                return
            }
        }*/

        if(telpon.isBlank() || telpon.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_phone))
            return
        }

        if(alamat.isBlank() || alamat.isEmpty()){
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
                    interactor.callAddSupplierAPI(context,restModel,name,email,telpon,alamat)
                    return
                }
            }
        }
        val supplierAdd =
            SupplierSQLAdd(
                "0",
                key!!,
                name,
                email,
                telpon,
                alamat
            )
        val supplier =
            SupplierSQL(
                "0",
                "0",
                key!!,
                name,
                email,
                telpon,
                alamat
            )
        var result = dataManager.addSupplierAdd(supplierAdd!!)
        var result2 = dataManager.addSupplier(supplier!!)
        Toast.makeText(context,"Add Local", Toast.LENGTH_SHORT).show()
        (context as AddSupplierActivity).hideLoading()
        view.onClose(Activity.RESULT_OK)
        (context as Activity).finish()

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessAddSupplier(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAddSupplier(code: Int, msg: String) {
        view.showMessage(code,msg)
    }


}