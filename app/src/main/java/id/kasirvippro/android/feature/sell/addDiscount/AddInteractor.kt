package id.kasirvippro.android.feature.sell.addDiscount

import android.content.Context
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.discount.DiscountRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class AddInteractor(var output: AddContract.InteractorOutput?) : AddContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callAddAPI(context: Context, model: DiscountRestModel, code: String,desc:String,jenis:String,nominal:String) {
        val key = appSession.getToken(context)
            disposable.add(model.addPenjualan(key!!,code,desc,jenis,nominal).subscribeWith(object : DisposableObserver<List<Discount>>() {

            override fun onNext(@NonNull response: List<Discount>) {
                output?.onSuccessAdd(response)
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