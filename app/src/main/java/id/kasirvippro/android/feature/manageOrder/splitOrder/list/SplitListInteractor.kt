package id.kasirvippro.android.feature.manageOrder.splitOrder.list;

import android.content.Context
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class SplitListInteractor(var output: SplitListContract.InteractorOutput?) : SplitListContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetTransactionsAPI(context: Context, restModel: TransactionRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getTransactionsNotable(key!!).subscribeWith(object : DisposableObserver<List<Transaction>>() {

            override fun onNext(@NonNull response: List<Transaction>) {
                output?.onSuccessGetTransactions(response)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
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