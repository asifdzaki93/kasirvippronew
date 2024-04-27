package id.kasirvippro.android.feature.choose.sift

import android.content.Context
import id.kasirvippro.android.models.sift.Sift
import id.kasirvippro.android.models.sift.SiftRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ChooseSiftInteractor(var output: ChooseSiftContract.InteractorOutput?) : ChooseSiftContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetsAPI(context: Context, restModel: SiftRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getSift(key!!).subscribeWith(object : DisposableObserver<List<Sift>>() {

            override fun onNext(@NonNull response: List<Sift>) {
                output?.onSuccessGets(response)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "Terjadi kesalahan"
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