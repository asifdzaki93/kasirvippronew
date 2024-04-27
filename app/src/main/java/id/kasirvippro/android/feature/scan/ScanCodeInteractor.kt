package id.kasirvippro.android.feature.scan

import io.reactivex.disposables.CompositeDisposable

class ScanCodeInteractor(val output: ScanCodeContract.InteractorOutput) :

    ScanCodeContract.Interactor {
    private var compositeDisposable = CompositeDisposable()

    override fun destroy() {
        compositeDisposable.clear()
    }


}
