package id.kasirvippro.android.feature.manage.productVariant.edit

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class EditProductVariantInteractor(var output: EditProductVariantContract.InteractorOutput?) : EditProductVariantContract.Interactor {

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

    override fun callEditProductAPI(context: Context, model: ProductRestModel, id:String, name:String, unit:String, kode:String, idkategori:String, jual:String, beli:String, stok:String,
                                    minstok:String, img:String?, desk:String,online:String,haveSTok:String,grosir:String,tax:String,alertstock:String) {
        val key = appSession.getToken(context)
        disposable.add(model.update(key!!,id, name,  unit, kode, idkategori, jual, beli, stok, minstok, img, desk,online, haveSTok,grosir,tax,alertstock).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessEditProduct(response.message)
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
                output?.onFailedByBarcode(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }
}