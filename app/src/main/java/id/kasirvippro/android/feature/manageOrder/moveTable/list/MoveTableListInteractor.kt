package id.kasirvippro.android.feature.manageOrder.moveTable.list;

import android.content.Context
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class MoveTableListInteractor(var output: MoveTableListContract.InteractorOutput?) : MoveTableListContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetTableAPI(context: Context, restModel: TableRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getTableOrder(key!!).subscribeWith(object : DisposableObserver<List<Table>>() {

            override fun onNext(@NonNull response: List<Table>) {
                output?.onSuccessGetTable(response)
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