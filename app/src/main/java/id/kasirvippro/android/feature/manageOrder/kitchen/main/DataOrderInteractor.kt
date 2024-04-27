package id.kasirvippro.android.feature.manageOrder.kitchen.main;

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class DataOrderInteractor(var output: DataOrderContract.InteractorOutput?) :
    DataOrderContract.Interactor {

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