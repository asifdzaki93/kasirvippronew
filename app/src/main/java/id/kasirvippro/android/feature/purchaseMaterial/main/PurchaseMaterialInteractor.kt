package id.kasirvippro.android.feature.purchaseMaterial.main;

import android.content.Context
import id.kasirvippro.android.models.transaction.Order
import id.kasirvippro.android.models.transaction.RequestRawMaterial
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class PurchaseMaterialInteractor(var output: PurchaseMaterialContract.InteractorOutput?) :
    PurchaseMaterialContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }


    override fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestRawMaterial) {
        val key = appSession.getToken(context)
        req.key = key
        disposable.add(restModel.orderraw(req).subscribeWith(object : DisposableObserver<Order>() {

            override fun onNext(@NonNull response: Order) {
                output?.onSuccessOrder(response)
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