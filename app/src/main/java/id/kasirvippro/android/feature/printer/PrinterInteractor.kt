package id.kasirvippro.android.feature.printer

import android.content.Context
import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class PrinterInteractor(var output: PrinterContract.InteractorOutput?) : PrinterContract.Interactor {

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