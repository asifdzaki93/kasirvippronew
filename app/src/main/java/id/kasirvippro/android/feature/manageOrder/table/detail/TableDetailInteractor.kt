package id.kasirvippro.android.feature.manageOrder.table.detail;

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class TableDetailInteractor(var output: TableDetailContract.InteractorOutput?) :
    TableDetailContract.Interactor {

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