package id.kasirvippro.android.feature.manage.table.edit

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.feature.manage.discount.edit.EditActivity
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.TableSQL
import id.kasirvippro.android.sqlite.Model.TableSQLUpdate
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession

class EditTablePresenter(val context: Context, val view: EditTableContract.View) : BasePresenter<EditTableContract.View>(),
    EditTableContract.Presenter, EditTableContract.InteractorOutput {


    private var interactor: EditTableInteractor = EditTableInteractor(this)
    private var tableRestModel = TableRestModel(context)
    private var data:Table? = null


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as Table
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            view.setTableName(it.name_table)
        }
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
                    interactor.callEditTableAPI(context,tableRestModel,data?.id_table!!,name)
                    return
                }
            }
        }
        val tableUpdate = TableSQLUpdate(
            data?.inc!!,
            key!!,
            data?.id_table!!,
            name
        )
        val table =
            TableSQL(
                data?.inc!!,
                key,
                data?.id_table!!,
                name
            )
        var result = dataManager.addTableUpdate(tableUpdate)
        var result2 = dataManager.updateTable(table)
        Toast.makeText(context,"Update Local", Toast.LENGTH_SHORT).show()
        (context as EditActivity)
        view.onClose(Activity.RESULT_OK)
        (context as Activity).finish()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessEditTable(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedEditTable(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

}