package id.kasirvippro.android.feature.manage.supplier.list

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.SupplierSQL
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class SupplierListInteractor(var output: SupplierListContract.InteractorOutput?) :
    SupplierListContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetSuppliersAPI(context: Context, restModel: SupplierRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!).subscribeWith(object : DisposableObserver<List<Supplier>>() {

            override fun onNext(@NonNull response: List<Supplier>) {
                output?.onSuccessGetSuppliers(response)
                val dataManager = DataManager (context)
                dataManager.clearSupplierAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val supplier = SupplierSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_supplier.toString(),
                            item.name_supplier.toString(),
                            item.telephone.toString(),
                            item.email.toString(),
                            item.address.toString(),
                        )
                        inc++
                        var result = dataManager.addSupplier(supplier)
                    }
                }
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

    override fun callDeleteSupplierAPI(context: Context, restModel: SupplierRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.delete(key!!,id).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessDeleteSupplier(response.message)
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

    override fun callSearchSupplierAPI(context: Context, restModel: SupplierRestModel, search: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.search(key!!,search).subscribeWith(object : DisposableObserver<List<Supplier>>() {

            override fun onNext(@NonNull response: List<Supplier>) {
                output?.onSuccessGetSuppliers(response)
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