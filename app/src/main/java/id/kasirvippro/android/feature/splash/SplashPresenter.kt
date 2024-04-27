package id.kasirvippro.android.feature.splash

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.AppConstant

class SplashPresenter(val context: Context, val view: SplashContract.View) : BasePresenter<SplashContract.View>(),
        SplashContract.Presenter, SplashContract.InteractorOutput {


    private var interactor: SplashInteractor = SplashInteractor(this)

    override fun onViewCreated() {
        interactor.delayScreen(context,AppConstant.SPLASH_TIMER)
    }

    override fun delaySuccess(isLogin:Boolean) {
        if(isLogin){
            view.openLoginScreen()
        }
        else{
            view.restartMainActivity()
        }

    }
}