package id.kasirvippro.android.feature.choose.typestore;

import android.content.Context
import id.kasirvippro.android.models.typestore.TypeStore
import id.kasirvippro.android.models.typestore.TypeStoreRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ChooseTypestoreInteractor(var output: ChooseTypestoreContract.InteractorOutput?) : ChooseTypestoreContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetDataAPI(context: Context, restModel: TypeStoreRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.geTypestore(key!!).subscribeWith(object : DisposableObserver<List<TypeStore>>() {

            override fun onNext(@NonNull response: List<TypeStore>) {
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