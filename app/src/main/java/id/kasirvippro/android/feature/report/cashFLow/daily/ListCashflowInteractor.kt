package id.kasirvippro.android.feature.report.listCashflow

import android.content.Context
import id.kasirvippro.android.models.transaction.HistoryFlowCash
import id.kasirvippro.android.models.transaction.FlowCash
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ListCashflowInteractor(var output: ListCashflowContract.InteractorOutput?) : ListCashflowContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetHistoryAPI(context: Context, restModel: TransactionRestModel,awal:String,akhir:String,status:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.cashFlow(key!!,awal,akhir,status).subscribeWith(object : DisposableObserver<List<HistoryFlowCash>>() {

            override fun onNext(@NonNull response: List<HistoryFlowCash>) {
                output?.onSuccessGetHistory(response)
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

    override fun callGetSearchAPI(context: Context, restModel: TransactionRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.searchCashflow(key!!,id).subscribeWith(object : DisposableObserver<List<FlowCash>>() {

            override fun onNext(@NonNull response: List<FlowCash>) {
                output?.onSuccessGetSearch(response)
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