package id.kasirvippro.android.feature.spend.main;

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.transaction.RequestSpend
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class SpendInteractor(var output: SpendContract.InteractorOutput?) :
    SpendContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }


    override fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestSpend,img:String?) {
        val key = appSession.getToken(context)
        req.key = key
        disposable.add(restModel.add(key!!, req.date!!, req.amount, req,img).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessOrder()
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