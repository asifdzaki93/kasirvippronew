package id.kasirvippro.android.feature.forgot

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.UserRestModel

interface ForgotContract {

    interface View : BaseViewImpl {
        fun onClose(status:Int)
        fun showMessage(code: Int, msg: String?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(email:String,telpon:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callForgotAPI(context: Context, model: UserRestModel, email:String,telpon:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAPI(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}