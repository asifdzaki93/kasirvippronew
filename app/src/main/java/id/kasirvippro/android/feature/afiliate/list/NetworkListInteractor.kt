package id.kasirvippro.android.feature.afiliate.list

import android.content.Context
import id.kasirvippro.android.models.network.Network
import id.kasirvippro.android.models.network.NetworkRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class NetworkListInteractor(var output: NetworkListContract.InteractorOutput?) :
    NetworkListContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetDataAPI(context: Context, restModel: NetworkRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!).subscribeWith(object : DisposableObserver<List<Network>>() {

            override fun onNext(@NonNull response: List<Network>) {
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


    override fun callSearchAPI(context: Context, restModel: NetworkRestModel, search: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.search(key!!,search).subscribeWith(object : DisposableObserver<List<Network>>() {

            override fun onNext(@NonNull response: List<Network>) {
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