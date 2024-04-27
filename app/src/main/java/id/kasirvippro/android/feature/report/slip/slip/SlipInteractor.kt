package id.kasirvippro.android.feature.report.slip.slip

import android.content.Context
import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class SlipInteractor(var output: SlipContract.InteractorOutput?) :
    SlipContract.Interactor {

    private var appSession = AppSession()
    private var disposable = CompositeDisposable()


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