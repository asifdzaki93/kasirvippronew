package id.kasirvippro.android.feature.manage.staff.edit

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.staff.StaffRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class EditStaffInteractor(var output: EditStaffContract.InteractorOutput?) : EditStaffContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callEditAPI(context: Context, model: StaffRestModel, id:String, name: String,email:String,phone:String,address:String,
                             gaji:String,komisi:String,tunjangan:String,potongan:String,toko:String,level:String,img:String?) {
        val key = appSession.getToken(context)
        disposable.add(model.update(key!!,id,name,email,phone,address,gaji,komisi,tunjangan,potongan,toko,level,img).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessEdit(response.message)
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

    override fun callGetStoressAPI(context: Context, restModel: StoreRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!).subscribeWith(object : DisposableObserver<List<Store>>() {

            override fun onNext(@NonNull response: List<Store>) {
                output?.onSuccessGetStore(response)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
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