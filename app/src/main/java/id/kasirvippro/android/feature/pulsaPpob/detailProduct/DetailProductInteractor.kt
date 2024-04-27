package id.kasirvippro.android.feature.pulsaPpob.detailProduct

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.pulsaPpob.DetailPpob
import id.kasirvippro.android.models.pulsaPpob.PulsaPpobRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class DetailProductInteractor(var output: DetailProductContract.InteractorOutput?) : DetailProductContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callPrefixAPI(context: Context, restModel: PulsaPpobRestModel, brand: String, search: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.cekTagihan(key!!,brand,search).subscribeWith(object : DisposableObserver<List<DetailPpob>>() {

            override fun onNext(@NonNull response: List<DetailPpob>) {
                output?.onSuccessPrefix(response)
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

    override fun callOrderAPI(context: Context,model: PulsaPpobRestModel,phone: String,sku: String,hargajual: String,pin: String,ref_id: String,hargaagent: String) {
        val key = appSession.getToken(context)
        disposable.add(model.bayar(key!!,phone,sku,hargajual,pin,ref_id,hargaagent).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessOrder(response.message)
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