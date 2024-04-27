package id.kasirvippro.android.feature.report.preOrder.main

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class PreOrderInteractor(var output: PreOrderContract.InteractorOutput?) :
    PreOrderContract.Interactor {

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