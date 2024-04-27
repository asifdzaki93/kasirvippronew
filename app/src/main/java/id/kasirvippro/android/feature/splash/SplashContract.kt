package id.kasirvippro.android.feature.splash

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl

interface SplashContract {

    interface View : BaseViewImpl {
        fun openLoginScreen()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()

    }

    interface Interactor : BaseInteractorImpl {
        fun delayScreen(context: Context, time:Long)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun delaySuccess(isLogin:Boolean)
    }


}