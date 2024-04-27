package id.kasirvippro.android.feature.report.preOrder.daily

import android.content.Context
import id.kasirvippro.android.models.report.HistoryPreOrder
import id.kasirvippro.android.models.report.ReportPreOrder
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ListPreOrderInteractor(var output: ListPreOrderContract.InteractorOutput?) : ListPreOrderContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetHistoryAPI(context: Context, restModel: ReportRestModel,awal:String,akhir:String,status:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.preorder(key!!,awal,akhir,status).subscribeWith(object : DisposableObserver<List<HistoryPreOrder>>() {

            override fun onNext(@NonNull response: List<HistoryPreOrder>) {
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

    override fun callGetSearchAPI(context: Context, restModel: ReportRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.searchpreorder(key!!,id).subscribeWith(object : DisposableObserver<List<ReportPreOrder>>() {

            override fun onNext(@NonNull response: List<ReportPreOrder>) {
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