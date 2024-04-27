package id.kasirvippro.android.feature.manageOrder.kitchen.transactionSuccess

import android.content.Context
import id.kasirvippro.android.models.transaction.HistoryTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class TransactionSuccessInteractor(var output: TransactionSuccessContract.InteractorOutput?) : TransactionSuccessContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetHistorySuccessAPI(context: Context, restModel: TransactionRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getTransactionSuccessOrder(key!!).subscribeWith(object : DisposableObserver<List<HistoryTransaction>>() {

            override fun onNext(@NonNull response: List<HistoryTransaction>) {
                output?.onSuccessGetHistoryApi(response)
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