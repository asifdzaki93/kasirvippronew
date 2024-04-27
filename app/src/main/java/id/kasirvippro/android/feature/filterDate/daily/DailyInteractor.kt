package id.kasirvippro.android.feature.filterDate.daily

import io.reactivex.disposables.CompositeDisposable

class DailyInteractor(var output: DailyContract.InteractorOutput?) : DailyContract.Interactor {

    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }
}