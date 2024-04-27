package id.kasirvippro.android.feature.label.main;

import android.content.Context
import id.kasirvippro.android.models.addOn.AddOn
import id.kasirvippro.android.models.addOn.AddOnRestModel
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class LabelInteractor(var output: LabelContract.InteractorOutput?) :
    LabelContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callSearchByBarcodeAPI(context: Context, restModel: ProductRestModel, search: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.searchByBarcode(key!!,search).subscribeWith(object : DisposableObserver<List<Product>>() {

            override fun onNext(@NonNull response: List<Product>) {
                output?.onSuccessByBarcode(response)
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
                output?.onFailedBarcode(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

    override fun callGetAddonAPI(context: Context, restModel: AddOnRestModel, id_product: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!,id_product).subscribeWith(object : DisposableObserver<List<AddOn>>() {

            override fun onNext(@NonNull response: List<AddOn>) {
                output?.onSuccessGetAddon(response)
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