package id.kasirvippro.android.feature.forgot

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.rest.entity.RestException
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ForgotInteractor(var output: ForgotContract.InteractorOutput?) : ForgotContract.Interactor {

    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callForgotAPI(context: Context, model: UserRestModel, email:String,telpon:String) {
        disposable.add(model.forgotPassword(email,telpon).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessAPI(response.message)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = if(e.message.isNullOrEmpty()){
                        e.message ?: "Terjadi kesalahan"
                    } else{
                        e.message.toString()
                    }

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