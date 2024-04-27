package id.kasirvippro.android.feature.history.main;

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class HistoryInteractor(var output: HistoryContract.InteractorOutput?) :
    HistoryContract.Interactor {

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