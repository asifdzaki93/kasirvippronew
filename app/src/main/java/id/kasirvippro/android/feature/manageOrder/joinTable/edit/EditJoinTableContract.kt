package id.kasirvippro.android.feature.manageOrder.joinTable.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel

interface EditJoinTableContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setTableName(name: String?)
        fun setJoinTableName(data: Table?)
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
        fun callJoinTableAPI(context: Context, model:TableRestModel, id:String,  id_table:String, name:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessJoinTable(msg: String?)
        fun onFailedEditTable(code:Int,msg:String)
    }


}