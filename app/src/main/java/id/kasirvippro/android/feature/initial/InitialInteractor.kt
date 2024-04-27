package id.kasirvippro.android.feature.initial

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.initial.InitialRestModel
import id.kasirvippro.android.models.initial.Initial
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class InitialInteractor(var output: InitialContract.InteractorOutput?) : InitialContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callInitialAPI(context: Context, model: InitialRestModel, modal_awal: String, sift: String) {
        val key = appSession.getToken(context)
        disposable.add(model.input(key!!,modal_awal,sift).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessEdit(response.message)
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

    override fun callGetInitialAPI(context: Context, restModel: InitialRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getInitial(key!!).subscribeWith(object : DisposableObserver<List<Initial>>() {

            override fun onNext(@NonNull response: List<Initial>) {
               // output?.onSuccessGetInitial(response)
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