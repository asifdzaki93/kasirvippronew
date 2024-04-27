package id.kasirvippro.android.feature.report.keuangan;

import android.content.Context
import id.kasirvippro.android.models.report.ReportLabaRugi
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class KeuanganInteractor(var output: KeuanganContract.InteractorOutput?) :
    KeuanganContract.Interactor {

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
        disposable.add(restModel.labaRugi(key!!,awal,akhir).subscribeWith(object : DisposableObserver<ReportLabaRugi>() {

            override fun onNext(@NonNull response: ReportLabaRugi) {
                output?.onSuccessGetReports(response)
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