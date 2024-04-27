package id.kasirvippro.android.feature.pulsaPpob.productPpob

import android.content.Context
import id.kasirvippro.android.models.productPpob.ProductPpob
import id.kasirvippro.android.models.productPpob.ProductPpobRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ProductPpobInteractor(var output: ProductPpobContract.InteractorOutput?) : ProductPpobContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetProductAPI(context: Context, restModel: ProductPpobRestModel, jenis: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getProduct(key!!,jenis).subscribeWith(object : DisposableObserver<List<ProductPpob>>() {

            override fun onNext(@NonNull response: List<ProductPpob>) {
                output?.onSuccessGetProduct(response)

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