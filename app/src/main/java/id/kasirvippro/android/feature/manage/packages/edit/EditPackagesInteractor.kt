package id.kasirvippro.android.feature.manage.packages.edit

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.packages.PackagesRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class EditPackagesInteractor(var output: EditPackagesContract.InteractorOutput?) : EditPackagesContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callEditPackagesAPI(context: Context, model: PackagesRestModel, id:String, name: String, price: String, img: String?) {
        val key = appSession.getToken(context)
        disposable.add(model.updatePackages(key!!,id,name,price, img).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessEditCategory(response.message)
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
                output?.onFailedEditCategory(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }
}