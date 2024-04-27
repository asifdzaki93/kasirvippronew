package id.kasirvippro.android.feature.main

import android.content.Context
import com.google.gson.Gson
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class MainInteractor(var output: MainContract.InteractorOutput?) : MainContract.Interactor{
    private var disposable = CompositeDisposable()
    private var appSession = AppSession()

    override fun loadProfile(context: Context): User? {
        val json = appSession.getSharedPreferenceString(context, AppConstant.USER) ?: return null

        return Gson().fromJson(json, User::class.java)
    }

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onLogout() {
        appSession.clearSession()
        output?.onLogoutSuccess()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

}