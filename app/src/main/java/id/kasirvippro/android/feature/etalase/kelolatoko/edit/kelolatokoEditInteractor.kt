package id.kasirvippro.android.feature.etalase.kelolatoko.edit

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.kelolatoko.KelolatokoRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class kelolatokoEditInteractor(var output: kelolatokoEditContract.InteractorOutput?) : kelolatokoEditContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callEditAPI(context: Context, model:KelolatokoRestModel, id:String, nama_toko: String,alamat_toko:String,warna_toko:String,jam_operasional:String,linkfb:String,linkinstagram:String,nowa:String,subdomain:String,img:String?) {
        val key = appSession.getToken(context)
        disposable.add(model.update(key!!,id,nama_toko,alamat_toko,warna_toko,jam_operasional,linkfb,linkinstagram,nowa,subdomain,img).subscribeWith(object : DisposableObserver<Message>() {

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
}