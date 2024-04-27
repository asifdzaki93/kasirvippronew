package id.kasirvippro.android.feature.filterDate.monthly

import io.reactivex.disposables.CompositeDisposable

class MonthlyInteractor(var output: MonthlyContract.InteractorOutput?) : MonthlyContract.Interactor {

    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }
}