package id.kasirvippro.android.feature.filterDate.main

import io.reactivex.disposables.CompositeDisposable

class MainInteractor(var output: MainContract.InteractorOutput?) : MainContract.Interactor {

    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }
}