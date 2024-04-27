package id.kasirvippro.android.feature.manage.customer.detail;

import id.kasirvippro.android.utils.AppSession
import io.reactivex.disposables.CompositeDisposable

class CustomerDetailInteractor(var output: CustomerDetailContract.InteractorOutput?) :
    CustomerDetailContract.Interactor {

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