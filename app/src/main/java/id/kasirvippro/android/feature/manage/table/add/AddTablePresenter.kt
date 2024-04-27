package id.kasirvippro.android.feature.manage.table.add

import android.app.Activity
import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.feature.manage.discount.add.AddActivity
import id.kasirvippro.android.models.table.TableRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.TableSQL
import id.kasirvippro.android.sqlite.Model.TableSQLAdd
import id.kasirvippro.android.utils.AppSession

class AddTablePresenter(val context: Context, val view: AddTableContract.View) : BasePresenter<AddTableContract.View>(),
    AddTableContract.Presenter, AddTableContract.InteractorOutput {


    private var interactor: AddTableInteractor = AddTableInteractor(this)
    private var tableRestModel = TableRestModel(context)


    override fun onViewCreated() {

    }

    override fun onCheck(name: String) {
        if(name.isNullOrBlank() || name.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name_table))
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
                    interactor.callAddTableAPI(context,tableRestModel,name)
                    return
                }
            }
        }
        val tableAdd =
            TableSQLAdd(
                "0",
                key!!,
                name
            )
        val table =
            TableSQL(
                "0",
                "0",
                key!!,
                name
            )
        var result = dataManager.addTableAdd(tableAdd!!)
        var result2 = dataManager.addTable(table!!)
        Toast.makeText(context,"Add Table Local", Toast.LENGTH_SHORT).show()
        (context as AddActivity).hideLoading()
        view.onClose(Activity.RESULT_OK)
        (context as Activity).finish()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessAddTable(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAddTable(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

}