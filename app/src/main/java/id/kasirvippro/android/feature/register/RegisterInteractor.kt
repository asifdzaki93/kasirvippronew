package id.kasirvippro.android.feature.register

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.currency.Currency
import id.kasirvippro.android.models.currency.CurrencyRestModel
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.rest.entity.RestException
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class RegisterInteractor(var output: RegisterContract.InteractorOutput?) : RegisterContract.Interactor {

    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callRegisterAPI(context: Context, model: UserRestModel, toko:String,currency:String,name:String,email:String,telpon:String,password:String,alamat:String,referal:String,typestore:String,decimal:String) {
        disposable.add(model.register(toko,currency,name,email,telpon,password,alamat,referal,typestore,decimal).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessAPI(response.message)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                val errorMessage: String
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = if(e.message.isNullOrEmpty()){
                        e.message ?: "Terjadi kesalahan"
                    } else{
                        e.message.toString()
                    }

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

    override fun callGetCurrenciesAPI(context: Context, restModel: CurrencyRestModel) {
        disposable.add(restModel.getCurrencies().subscribeWith(object : DisposableObserver<List<Currency>>() {

            override fun onNext(@NonNull response: List<Currency>) {
                output?.onSuccessGetCurrencies(response)
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