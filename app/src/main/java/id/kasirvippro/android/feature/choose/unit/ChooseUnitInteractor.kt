package id.kasirvippro.android.feature.choose.unit

import android.content.Context
import id.kasirvippro.android.models.unit.Unit
import id.kasirvippro.android.models.unit.UnitRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ChooseUnitInteractor(var output: ChooseUnitContract.InteractorOutput?) : ChooseUnitContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetsAPI(context: Context, restModel: UnitRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getUnit(key!!).subscribeWith(object : DisposableObserver<List<Unit>>() {

            override fun onNext(@NonNull response: List<Unit>) {
                output?.onSuccessGets(response)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "Terjadi kesalahan"
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