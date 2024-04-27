package id.kasirvippro.android.feature.manage.table.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.table.TableRestModel

interface AddTableContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(name:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callAddTableAPI(context: Context, model:TableRestModel,name:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAddTable(msg: String?)
        fun onFailedAddTable(code:Int,msg:String)
    }


}