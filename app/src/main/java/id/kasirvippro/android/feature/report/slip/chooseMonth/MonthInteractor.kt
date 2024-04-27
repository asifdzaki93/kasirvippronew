package id.kasirvippro.android.feature.report.slip.chooseMonth

import android.content.Context
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.slip.Absent
import id.kasirvippro.android.models.slip.Slip
import id.kasirvippro.android.models.slip.SlipRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class MonthInteractor(var output: MonthContract.InteractorOutput?) :
    MonthContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()


    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetSlipAPI(context: Context, restModel: SlipRestModel, id:String?,date:FilterDialogDate) {
        var key = appSession.getToken(context)
        id?.let {
            key = it
        }
        disposable.add(restModel.getPaySlip(key!!,date.firstDate?.date.toString(),date.lastDate?.date.toString()).subscribeWith(object : DisposableObserver<List<Slip>>() {

            override fun onNext(@NonNull response: List<Slip>) {
                output?.onSuccessPaySlip(response)
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

    override fun callGetAbsenAPI(context: Context, restModel: SlipRestModel, id:String?,date:FilterDialogDate) {
        var key = appSession.getToken(context)
        id?.let {
            key = it
        }
        disposable.add(restModel.getAbsent(key!!,date.firstDate?.date.toString(),date.lastDate?.date.toString()).subscribeWith(object : DisposableObserver<List<Absent>>() {

            override fun onNext(@NonNull response: List<Absent>) {
                output?.onSuccessAbsent(response)
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