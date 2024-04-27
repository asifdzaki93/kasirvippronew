package id.kasirvippro.android.feature.login

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.Login
import id.kasirvippro.android.models.user.UserRestModel

interface LoginContract {

    interface View : BaseViewImpl {
        fun enableLoginBtn(isLogin:Boolean)
        fun showLoginSuccess()
        fun openRegisterPage()
        fun openForgotPage()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onDestroy()
        fun onViewCreated()
        fun onBtnLoginCheck(phone:String,password:String)
        fun onLogin(phone:String,password:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun saveSession(user:Login)
        fun saveDeviceToken(token:String?)
        fun clearSession()
        fun callLoginAPI(context: Context, restModel: UserRestModel, phone:String, password:String, latitude:Double, longitude:Double)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessLogin(list:List<Login>)
        fun onFailedAPI(code:Int,msg:String)
    }


}