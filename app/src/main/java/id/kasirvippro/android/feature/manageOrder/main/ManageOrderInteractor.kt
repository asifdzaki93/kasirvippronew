package id.kasirvippro.android.feature.manageOrder.main;

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class ManageOrderInteractor(var output: ManageOrderContract.InteractorOutput?) :
    ManageOrderContract.Interactor {

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