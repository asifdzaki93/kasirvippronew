package id.kasirvippro.android.feature.manage.divisi.list;

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.divisi.Divisi
import id.kasirvippro.android.models.divisi.DivisiRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class DivisiListInteractor(var output: DivisiListContract.InteractorOutput?) : DivisiListContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetAPI(context: Context, restModel: DivisiRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getDivisi(key!!).subscribeWith(object : DisposableObserver<List<Divisi>>() {

            override fun onNext(@NonNull response: List<Divisi>) {
                output?.onSuccessGet(response)
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

    override fun callDeleteAPI(context: Context, restModel: DivisiRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.deleteDivisi(key!!,id).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessDelete(response.message)
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