package id.kasirvippro.android.feature.hotNews.view

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class ViewNewsInteractor(var output: ViewNewsContract.InteractorOutput?) : ViewNewsContract.Interactor {

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