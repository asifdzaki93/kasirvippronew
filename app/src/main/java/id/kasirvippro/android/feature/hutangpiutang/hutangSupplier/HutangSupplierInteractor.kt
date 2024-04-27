package id.kasirvippro.android.feature.hutangpiutang.hutangSupplier

import android.content.Context
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class HutangSupplierInteractor(var output: HutangSupplierContract.InteractorOutput?) :
    HutangSupplierContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetHutangAPI(context: Context, restModel: HutangPiutangRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getListHutangSupplier(key!!).subscribeWith(object : DisposableObserver<List<Supplier>>() {

            override fun onNext(@NonNull response: List<Supplier>) {
                output?.onSuccessGetHutang(response)
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

    override fun callSearchHutangAPI(context: Context, restModel: HutangPiutangRestModel, search: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getSearchHutangSupplier(key!!,search).subscribeWith(object : DisposableObserver<List<Supplier>>() {

            override fun onNext(@NonNull response: List<Supplier>) {
                output?.onSuccessGetHutang(response)
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