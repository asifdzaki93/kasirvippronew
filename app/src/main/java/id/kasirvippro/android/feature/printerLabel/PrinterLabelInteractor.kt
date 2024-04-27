package id.kasirvippro.android.feature.printerLabel

import android.content.Context
import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class PrinterLabelInteractor(var output: PrinterLabelContract.InteractorOutput?) : PrinterLabelContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun getUserPaket(context: Context): String? {
        return appSession.getPackage(context)
    }
}