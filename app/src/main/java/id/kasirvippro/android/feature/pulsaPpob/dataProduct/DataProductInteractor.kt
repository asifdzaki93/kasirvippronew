package id.kasirvippro.android.feature.pulsaPpob.dataProduct

import android.content.Context
import id.kasirvippro.android.models.pulsaPpob.PulsaPpob
import id.kasirvippro.android.models.pulsaPpob.PulsaPpobRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class DataProductInteractor(var output: DataProductContract.InteractorOutput?) : DataProductContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callPrefixAPI(context: Context, restModel: PulsaPpobRestModel, category: String, brand: String, search: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.searchProduck(key!!,category,brand,search).subscribeWith(object : DisposableObserver<List<PulsaPpob>>() {

            override fun onNext(@NonNull response: List<PulsaPpob>) {
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
}