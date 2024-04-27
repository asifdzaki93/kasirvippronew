package id.kasirvippro.android.feature.manage.priceVariant.edit

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.priceVariant.PriceVariantRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class EditPriceVariantInteractor(var output: EditPriceVariantContract.InteractorOutput?) : EditPriceVariantContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun getUserPaket(context: Context): String? {
        return appSession.getPackage(context)
    }

    override fun callEditProductAPI(context: Context, model: PriceVariantRestModel, id:String, minimal:String, nominal:String) {
        val key = appSession.getToken(context)
        disposable.add(model.update(key!!,id, minimal,  nominal).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessEditProduct(response.message)
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