package id.kasirvippro.android.feature.afiliate.mains

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class ManageAfiliateInteractor(var output: ManageAfiliateContract.InteractorOutput?) :
    ManageAfiliateContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }
}