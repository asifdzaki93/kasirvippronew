package id.kasirvippro.android.feature.transaction.detailSpend

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.transaction.DetailSpend
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class DetailSpendInteractor(var output: DetailSpendContract.InteractorOutput?) : DetailSpendContract.Interactor {

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
        disposable.add(restModel.getDetailSpend(key!!,id).subscribeWith(object : DisposableObserver<DetailSpend>() {

            override fun onNext(@NonNull response: DetailSpend) {
                output?.onSuccessGetDetail(response)
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

    override fun callDeleteDetailAPI(context: Context, restModel: TransactionRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.deleteDetailSpend(key!!,id).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessDeleteDetail(response.message)
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