package id.kasirvippro.android.feature.manage.supplier.add

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class AddSupplierInteractor(var output: AddSupplierContract.InteractorOutput?) : AddSupplierContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callAddSupplierAPI(
        context: Context,
        model: SupplierRestModel,
        name: String,
        email: String,
        telpon: String,
        alamat: String
    ) {
        val key = appSession.getToken(context)
        disposable.add(model.add(key!!,name,email,telpon,alamat).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessAddSupplier(response.message)
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
                output?.onFailedAddSupplier(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }
}