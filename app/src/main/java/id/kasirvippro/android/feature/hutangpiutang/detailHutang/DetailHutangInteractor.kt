package id.kasirvippro.android.feature.hutangpiutang.detailHutang

import android.content.Context
import id.kasirvippro.android.models.hutangpiutang.DetailHutang
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class DetailHutangInteractor(var output: DetailHutangContract.InteractorOutput?) : DetailHutangContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetDetailSupplier(context: Context, restModel: SupplierRestModel, id:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.detail(key!!,id).subscribeWith(object : DisposableObserver<Supplier>() {

            override fun onNext(@NonNull response: Supplier) {
                output?.onSuccessGetDetailSupplier(response)
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

    override fun callGetHutang(context: Context, restModel: HutangPiutangRestModel,id:String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getDetailHutangSupplier(key!!,id).subscribeWith(object : DisposableObserver<DetailHutang>() {

            override fun onNext(@NonNull response: DetailHutang) {
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