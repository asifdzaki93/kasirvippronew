package id.kasirvippro.android.feature.manageOrder.menuOrder

import android.content.Context
import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class MenuOrderInteractor(var output: MenuOrderContract.InteractorOutput?) : MenuOrderContract.Interactor {

    private var appSession = AppSession()
    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun getToken(context: Context): String? {
        return appSession.getToken(context)
    }

    override fun getUserLevel(context: Context): String? {
        return appSession.getLevel(context)
    }

    override fun getUserPaket(context: Context): String? {
        return appSession.getPackage(context)
    }


}