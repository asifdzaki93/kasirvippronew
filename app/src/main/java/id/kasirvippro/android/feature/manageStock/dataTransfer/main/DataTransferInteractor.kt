package id.kasirvippro.android.feature.manageStock.dataTransfer.main;

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class DataTransferInteractor(var output: DataTransferContract.InteractorOutput?) :
    DataTransferContract.Interactor {

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