package id.kasirvippro.android.feature.report.rawMaterial.main

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class RawMaterialInteractor(var output: RawMaterialContract.InteractorOutput?) :
    RawMaterialContract.Interactor {

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