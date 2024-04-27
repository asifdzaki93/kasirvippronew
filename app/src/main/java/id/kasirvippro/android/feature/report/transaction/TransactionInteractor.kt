package id.kasirvippro.android.feature.report.transaction

import android.content.Context
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.models.report.ReportTransaksi
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class TransactionInteractor(var output: TransactionContract.InteractorOutput?) :
    TransactionContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetReportsAPI(context: Context, restModel: ReportRestModel, awal:String, akhir:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.transactions(key!!,awal,akhir).subscribeWith(object : DisposableObserver<List<ReportTransaksi>>() {

            override fun onNext(@NonNull response: List<ReportTransaksi>) {
                output?.onSuccessGetReports(response)
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

    override fun callSearchAPI(context: Context, restModel:ReportRestModel, search:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.searchTransactions(key!!,search).subscribeWith(object : DisposableObserver<List<ReportTransaksi>>() {

            override fun onNext(@NonNull response: List<ReportTransaksi>) {
                output?.onSuccessGetReports(response)
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

    override fun callSortAPI(context: Context, restModel: ReportRestModel, awal:String, akhir:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.sortTransactions(key!!,awal,akhir).subscribeWith(object : DisposableObserver<List<ReportTransaksi>>() {

            override fun onNext(@NonNull response: List<ReportTransaksi>) {
                output?.onSuccessGetReports(response)
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