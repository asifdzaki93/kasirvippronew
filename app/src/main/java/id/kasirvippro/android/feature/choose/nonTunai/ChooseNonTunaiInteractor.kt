package id.kasirvippro.android.feature.choose.nonTunai

import android.content.Context
import id.kasirvippro.android.models.transaction.NonTunai
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.LinkSQL
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ChooseNonTunaiInteractor(var output: ChooseNonTunaiContract.InteractorOutput?) : ChooseNonTunaiContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()


    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetDataAPI(context: Context, restModel: TransactionRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getNonTunai(key!!).subscribeWith(object : DisposableObserver<List<NonTunai>>() {

            override fun onNext(@NonNull response: List<NonTunai>) {
                output?.onSuccessGetData(response)
                val dataManager = DataManager (context)
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val link = LinkSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_link.toString(),
                            item.name_link.toString(),
                            item.img.toString(),
                        )
                        inc++
                        var result = dataManager.addLink(link)
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