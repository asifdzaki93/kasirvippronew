package id.kasirvippro.android.feature.manage.rawMaterial.add

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class AddRawMaterialInteractor(var output: AddRawMaterialPresenter) : AddRawMaterialContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun getUserPaket(context: Context): String? {
        return appSession.getPackage(context)
    }

    override fun callAddProductAPI(context: Context, model: RawMaterialRestModel, name:String,unit:String,jual:String,stok:String,
                                   desk:String) {
        val key = appSession.getToken(context)
        disposable.add(model.add(key!!,name,unit,jual, stok,desk).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessAddProduct(response.message)
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