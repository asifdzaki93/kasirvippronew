package id.kasirvippro.android.feature.manageStock.stockHistoryRawMaterial.data

import android.content.Context
import id.kasirvippro.android.models.transaction.DetailHistory
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class DataHistoryInteractor(var output: DataHistoryContract.InteractorOutput?) : DataHistoryContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetProductsAPI(context: Context, restModel: TransactionRestModel, id: String, awal:String, akhir:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getHistoryStockRawMaterial(key!!,id,awal,akhir).subscribeWith(object : DisposableObserver<List<DetailHistory>>() {

            override fun onNext(@NonNull response: List<DetailHistory>) {
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



}