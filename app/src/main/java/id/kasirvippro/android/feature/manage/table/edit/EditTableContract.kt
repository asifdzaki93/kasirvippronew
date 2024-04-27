package id.kasirvippro.android.feature.manage.table.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.table.TableRestModel

interface EditTableContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setTableName(name: String?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun onCheck(name:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callEditTableAPI(context: Context, model:TableRestModel, id:String, name:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditTable(msg: String?)
        fun onFailedEditTable(code:Int,msg:String)
    }


}