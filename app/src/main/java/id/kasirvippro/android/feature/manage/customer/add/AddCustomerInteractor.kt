package id.kasirvippro.android.feature.manage.customer.add

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class AddCustomerInteractor(var output: AddCustomerContract.InteractorOutput?) : AddCustomerContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callAddCustomerAPI(context: Context, model: CustomerRestModel, name:String,email:String,phone:String,address:String,customercode:String) {
        val key = appSession.getToken(context)
        disposable.add(model.add(key!!,name,email,phone,address,customercode).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessAddCustomer(response.message)
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
                output?.onFailedAddCustomer(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }
}