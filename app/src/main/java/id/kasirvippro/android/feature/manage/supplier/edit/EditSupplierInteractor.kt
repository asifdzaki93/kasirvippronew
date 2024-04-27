package id.kasirvippro.android.feature.manage.supplier.edit

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class EditSupplierInteractor(var output: EditSupplierContract.InteractorOutput?) : EditSupplierContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callEditSupplierAPI(context: Context, model: SupplierRestModel, id:String, name: String,email:String,phone:String,address:String) {
        val key = appSession.getToken(context)
        disposable.add(model.update(key!!,id,name,email,phone,address).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessEditSupplier(response.message)
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
                output?.onFailedEditSupplier(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }
}