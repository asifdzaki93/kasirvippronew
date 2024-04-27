package id.kasirvippro.android.feature.transaction.successadd;

import android.content.Context
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class SuccessAddInteractor(var output: SuccessAddContract.InteractorOutput?) : SuccessAddContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetDetailAPI(context: Context, restModel: TransactionRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getDetailTransaction(key!!,id).subscribeWith(object : DisposableObserver<DetailTransaction>() {

            override fun onNext(@NonNull response: DetailTransaction) {
                output?.onSuccessGetDetail(response)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                var errorMessage = "There is an error"
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }


}