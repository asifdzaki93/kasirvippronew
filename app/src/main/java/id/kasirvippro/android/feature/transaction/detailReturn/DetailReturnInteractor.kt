package id.kasirvippro.android.feature.transaction.detailReturn

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class DetailReturnInteractor(var output: DetailReturnContract.InteractorOutput?) : DetailReturnContract.Interactor {

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
        disposable.add(restModel.getDetailTransactionReturn(key!!,id).subscribeWith(object : DisposableObserver<DetailTransaction>() {

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

    override fun callGetDetailSupplierAPI(context: Context, restModel: TransactionRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getDetailTransactionReturnSupplier(key!!,id).subscribeWith(object : DisposableObserver<DetailTransaction>() {

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
        disposable.add(restModel.deleteDetailTransaction(key!!,id).subscribeWith(object : DisposableObserver<Message>() {

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