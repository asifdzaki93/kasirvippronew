package id.kasirvippro.android.feature.splash

import android.os.Bundle
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity


class SplashActivity : BaseActivity<SplashPresenter, SplashContract.View>(), SplashContract.View {

    override fun createPresenter(): SplashPresenter {
        return SplashPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_splash
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        getPresenter()?.onViewCreated()
    }

    override fun openLoginScreen() {
        restartLoginActivity()
    }


}
