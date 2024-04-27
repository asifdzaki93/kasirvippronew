package id.kasirvippro.android.feature.choose.color

import android.content.Context
import id.kasirvippro.android.models.color.Color
import id.kasirvippro.android.models.color.ColorRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ChooseColorInteractor(var output: ChooseColorContract.InteractorOutput?) : ChooseColorContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetsAPI(context: Context, restModel: ColorRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getColor(key!!).subscribeWith(object : DisposableObserver<List<Color>>() {

            override fun onNext(@NonNull response: List<Color>) {
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