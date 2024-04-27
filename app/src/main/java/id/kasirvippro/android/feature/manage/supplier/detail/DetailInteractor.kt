package id.kasirvippro.android.feature.manage.supplier.detail;

import io.reactivex.disposables.CompositeDisposable

class DetailInteractor(var output: DetailContract.InteractorOutput?) :
    DetailContract.Interactor {

    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }
}