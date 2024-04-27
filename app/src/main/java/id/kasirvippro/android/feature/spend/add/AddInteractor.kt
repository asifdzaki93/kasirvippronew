package id.kasirvippro.android.feature.spend.add

import io.reactivex.disposables.CompositeDisposable

class AddInteractor(var output: AddContract.InteractorOutput?) : AddContract.Interactor {

    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }


}