package id.kasirvippro.android.feature.manageOrder.moveTable.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel

interface EditMoveTableContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setTableName(name: String?)
        fun setMoveTableName(data: Table?)
        fun openChooseTable()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun onCheck(name:String,id_table:String)
        fun updateTable(data:Table?)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callMoveTableAPI(context: Context, model:TableRestModel, id:String,  id_table:String, name:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessMoveTable(msg: String?)
        fun onFailedEditTable(code:Int,msg:String)
    }


}