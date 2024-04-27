package id.kasirvippro.android.feature.hutangpiutang.detailPiutang

import android.content.Context
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.models.hutangpiutang.DetailPiutang
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class DetailPiutangInteractor(var output: DetailPiutangContract.InteractorOutput?) : DetailPiutangContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetDetailCustomer(context: Context, restModel: CustomerRestModel, id:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.detail(key!!,id).subscribeWith(object : DisposableObserver<Customer>() {

            override fun onNext(@NonNull response: Customer) {
                output?.onSuccessGetDetailCustomer(response)
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

    override fun callGetHutang(context: Context, restModel: HutangPiutangRestModel, id:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getDetailPiutangCustomer(key!!,id).subscribeWith(object : DisposableObserver<DetailPiutang>() {

            override fun onNext(@NonNull response: DetailPiutang) {
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