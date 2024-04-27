package id.kasirvippro.android.feature.transaction.detailSplit

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.transaction.*
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import id.kasirvippro.android.models.transaction.TransactionRestModel

class DetailInteractor(var output: DetailContract.InteractorOutput?) : DetailContract.Interactor {

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

    override fun callGetDetailAPI(context: Context, restModel: TransactionRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getDetailTransaction(key!!,id).subscribeWith(object : DisposableObserver<DetailTransaction>() {

            override fun onNext(@NonNull response: DetailTransaction) {
                output?.onSuccessGetDetail(response)
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

    override fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestTransaction) {
        val key = appSession.getToken(context)
        req.key = key
        disposable.add(restModel.updateDirect(req).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessUpdate()
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

    override fun callGetDetailSupplierAPI(context: Context, restModel: TransactionRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getDetailTransactionSupplier(key!!,id).subscribeWith(object : DisposableObserver<DetailTransaction>() {

            override fun onNext(@NonNull response: DetailTransaction) {
                output?.onSuccessGetDetail(response)
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

    override fun callDeleteDetailAPI(context: Context, restModel: TransactionRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.deleteAddTransaction(key!!,id).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessDeleteDetail(response.message)
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

    override fun callPayOrderAPI(context: Context, restModel: TransactionRestModel, req: ReqTrans, note: String, id: String) {
        val key = appSession.getToken(context)
        req.key = key
        disposable.add(restModel.paySplit(req,note,id).subscribeWith(object : DisposableObserver<Order>() {

            override fun onNext(@NonNull response: Order) {
                output?.onSuccessOrder(response)
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

    override fun callPayPiutangAPI(context: Context, restModel: TransactionRestModel, id: String, total: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.payPiutang(key!!,id,total).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessPay(response.message)
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

    override fun callPayHutangAPI(context: Context, restModel: TransactionRestModel, id: String, total: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.payHutang(key!!,id,total).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessPay(response.message)
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


    override fun callDeleteProductAPI(context: Context, restModel: TransactionRestModel, no_invoice: String, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.deleteProductItem(key!!,no_invoice,id).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessDeleteProduct(response.message)
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
    override fun callPlusProductAPI(context: Context, restModel: TransactionRestModel, no_invoice: String, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.plusProductItem(key!!,no_invoice,id).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessDeleteProduct(response.message)
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
    }override fun callMinusProductAPI(context: Context, restModel: TransactionRestModel, no_invoice: String, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.minusProductItem(key!!,no_invoice,id).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessDeleteProduct(response.message)
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

    override fun callSupplierDeleteDetailAPI(context: Context, restModel: TransactionRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.deleteSupplierDetailTransaction(key!!,id).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessDeleteDetail(response.message)
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