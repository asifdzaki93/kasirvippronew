package id.kasirvippro.android.feature.order.main;

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.transaction.DetailPayment
import id.kasirvippro.android.models.transaction.Order
import id.kasirvippro.android.models.transaction.RequestTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class OrderInteractor(var output: OrderContract.InteractorOutput?) :
    OrderContract.Interactor {

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

    override fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestTransaction,img:String?) {
        val key = appSession.getToken(context)
        req.key = key
        disposable.add(restModel.orderDirect(req,img).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessOrder()
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

    override fun callGetProductsAPI(context: Context, restModel: ProductRestModel,haveStok:String,page: Int?) {
        val key = appSession.getToken(context)
        val trx = "YES"
        disposable.add(restModel.choose(key!!,trx,haveStok,page).subscribeWith(object : DisposableObserver<List<Product>>() {

            override fun onNext(@NonNull response: List<Product>) {
                output?.onSuccessGetProducts(response)
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


    override fun getToken(context: Context): String? {
        return appSession.getToken(context)
    }

    override fun callGetProductsVariableAPI(context: Context, restModel: ProductRestModel,id_product:String,haveStok:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.chooseVariable(key!!,id_product,haveStok).subscribeWith(object : DisposableObserver<List<Product>>() {

            override fun onNext(@NonNull response: List<Product>) {
                output?.onSuccessGetProductsVariable(response)
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

    override fun callCheckDiscountAPI(context: Context, restModel: TransactionRestModel, total: Double,id:String?) {
        val key = getToken(context)
        disposable.add(restModel.checkPayment(key!!,total,id).subscribeWith(object : DisposableObserver<List<DetailPayment>>() {

            override fun onNext(@NonNull response: List<DetailPayment>) {
                output?.onSuccessCheckDiscount(response)
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