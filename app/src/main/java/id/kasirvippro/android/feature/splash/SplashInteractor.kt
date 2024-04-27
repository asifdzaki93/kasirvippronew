package id.kasirvippro.android.feature.splash

import android.content.Context
import android.os.Handler
import id.kasirvippro.android.utils.AppSession

class SplashInteractor(var output: SplashContract.InteractorOutput?) : SplashContract.Interactor {

    private val appSession = AppSession()

    override fun delayScreen(context:Context, time:Long) {
        val key = appSession.getToken(context)

        Handler().postDelayed({
            output?.delaySuccess(key.isNullOrEmpty())
        },time)

    }



}