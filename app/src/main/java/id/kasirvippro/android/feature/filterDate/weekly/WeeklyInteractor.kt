package id.kasirvippro.android.feature.filterDate.weekly

import io.reactivex.disposables.CompositeDisposable

class WeeklyInteractor(var output: WeeklyContract.InteractorOutput?) : WeeklyContract.Interactor {

    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }
}