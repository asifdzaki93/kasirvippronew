package id.kasirvippro.android.feature.report.cashflow.main

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class CashflowInteractor(var output: CashflowContract.InteractorOutput?) :
    CashflowContract.Interactor {

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