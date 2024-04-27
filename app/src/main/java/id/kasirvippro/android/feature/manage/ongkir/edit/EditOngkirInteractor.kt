package id.kasirvippro.android.feature.manage.ongkir.edit

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.ongkir.OngkirRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class EditOngkirInteractor(var output: EditOngkirContract.InteractorOutput?) : EditOngkirContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callEditCategoryAPI(context: Context, model: OngkirRestModel, id:String, name_ongkir: String, nominal: String) {
        val key = appSession.getToken(context)
        disposable.add(model.updateOngkir(key!!,id,name_ongkir,nominal).subscribeWith(object : DisposableObserver<Message>() {

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