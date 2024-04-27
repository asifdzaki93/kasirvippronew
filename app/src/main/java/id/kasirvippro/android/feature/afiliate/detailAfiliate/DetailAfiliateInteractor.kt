package id.kasirvippro.android.feature.afiliate.detailAfiliate

import android.content.Context
import com.google.gson.Gson
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class DetailAfiliateInteractor(var output: DetailAfiliateContract.InteractorOutput?) : DetailAfiliateContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun loadProfile(context: Context): User? {
        val json = appSession.getSharedPreferenceString(context,AppConstant.USER) ?: return null

        return Gson().fromJson(json,User::class.java)
    }
}