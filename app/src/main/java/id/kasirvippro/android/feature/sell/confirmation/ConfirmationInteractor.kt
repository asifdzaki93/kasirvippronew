package id.kasirvippro.android.feature.sell.confirmation

import android.content.Context
import id.kasirvippro.android.models.price.Price
import id.kasirvippro.android.models.price.PriceRestModel
import id.kasirvippro.android.models.transaction.DetailPayment
import id.kasirvippro.android.models.transaction.Order
import id.kasirvippro.android.models.transaction.RequestTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ConfirmationInteractor(var output: ConfirmationContract.InteractorOutput?) :
    ConfirmationContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun getToken(context: Context): String? {
        return appSession.getToken(context)
    }

    override fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestTransaction, note: String) {
        val key = getToken(context)
        req.key = key
        disposable.add(restModel.order(req,note).subscribeWith(object : DisposableObserver<Order>() {

            override fun onNext(@NonNull response: Order) {
                output?.onSuccessOrder(response)
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

    override fun callGetPriceAPI(context: Context, restModel: PriceRestModel, nominal: Double) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!, nominal).subscribeWith(object : DisposableObserver<List<Price>>() {

            override fun onNext(@NonNull response: List<Price>) {
                output?.onSuccessGetPrice(response)
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

    override fun callCheckDiscountAPI(context: Context, restModel: TransactionRestModel, total: Double,id:String?) {
        val key = getToken(context)
        disposable.add(restModel.checkPayment(key!!,total,id).subscribeWith(object : DisposableObserver<List<DetailPayment>>() {

            override fun onNext(@NonNull response: List<DetailPayment>) {
                output?.onSuccessCheckDiscount(response)
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