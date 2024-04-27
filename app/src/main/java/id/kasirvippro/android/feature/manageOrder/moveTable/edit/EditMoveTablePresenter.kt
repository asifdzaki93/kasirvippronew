package id.kasirvippro.android.feature.manageOrder.moveTable.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel
import id.kasirvippro.android.utils.AppConstant

class EditMoveTablePresenter(val context: Context, val view: EditMoveTableContract.View) : BasePresenter<EditMoveTableContract.View>(),
    EditMoveTableContract.Presenter, EditMoveTableContract.InteractorOutput {


    private var interactor: EditMoveTableInteractor = EditMoveTableInteractor(this)
    private var tableRestModel = TableRestModel(context)
    private var table:Table?=null
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

    override fun onCheck(name: String,id_table: String) {
        if(name.isNullOrBlank() || name.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name_table))
            return
        }
        if(id_table.isNullOrBlank() || id_table.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name_table))
            return
        }

        val id_table = table?.id_table

        if (id_table != null) {
            interactor.callMoveTableAPI(context,tableRestModel,data?.id_table!!,name,id_table)
        }
    }

    override fun updateTable(data: Table?) {
        table = data
        view.setMoveTableName(data)
    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessMoveTable(msg: String?) {
        view.showMessage(200,msg)
       view.onClose(Activity.RESULT_OK)
   }

    override fun onFailedEditTable(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

}