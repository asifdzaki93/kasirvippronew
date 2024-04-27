package id.kasirvippro.android.feature.choose.decimal;

import android.content.Context
import id.kasirvippro.android.models.currency.CurrencyRestModel
import id.kasirvippro.android.models.currency.Decimal
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ChooseDecimalInteractor(var output: ChooseDecimalContract.InteractorOutput?) : ChooseDecimalContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetDataAPI(context: Context, restModel: CurrencyRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getDecimal().subscribeWith(object : DisposableObserver<List<Decimal>>() {

            override fun onNext(@NonNull response: List<Decimal>) {
                output?.onSuccessGetData(response)

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