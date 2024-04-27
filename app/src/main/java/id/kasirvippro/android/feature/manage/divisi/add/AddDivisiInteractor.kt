package id.kasirvippro.android.feature.manage.divisi.add

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.divisi.DivisiRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class AddDivisiInteractor(var output: AddDivisiContract.InteractorOutput?) : AddDivisiContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callAddCategoryAPI(context: Context, model: DivisiRestModel, name_divisi: String) {
        val key = appSession.getToken(context)
        disposable.add(model.addDivisi(key!!,name_divisi).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessAddCategory(response.message)
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
                output?.onFailedAddCategory(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }
}