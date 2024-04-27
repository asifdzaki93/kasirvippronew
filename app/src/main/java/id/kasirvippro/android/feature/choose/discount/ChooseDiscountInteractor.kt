package id.kasirvippro.android.feature.choose.discount

import android.content.Context
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.discount.DiscountRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.DiscountSQL
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ChooseDiscountInteractor(var output: ChooseDiscountContract.InteractorOutput?) : ChooseDiscountContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetsAPI(context: Context, restModel: DiscountRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!).subscribeWith(object : DisposableObserver<List<Discount>>() {

            override fun onNext(@NonNull response: List<Discount>) {
                output?.onSuccessGets(response)
                val dataManager = DataManager (context)
                dataManager.clearDiscountAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val discount = DiscountSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_discount.toString(),
                            item.name_discount.toString(),
                            item.note.toString(),
                            item.type.toString(),
                            item.nominal.toString(),
                        )
                        inc++
                        var result = dataManager.addDiscount(discount)
                    }
                }
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