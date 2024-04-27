package id.kasirvippro.android.feature.report.absent

import io.reactivex.disposables.CompositeDisposable

class AbsentInteractor(var output: AbsentContract.InteractorOutput?) :
    AbsentContract.Interactor {

    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

}