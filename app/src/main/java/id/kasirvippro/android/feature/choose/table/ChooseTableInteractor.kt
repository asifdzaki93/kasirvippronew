package id.kasirvippro.android.feature.choose.table

import android.content.Context

import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.TableSQL
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ChooseTableInteractor(var output: ChooseTableContract.InteractorOutput?) : ChooseTableContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()


    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetDataAPI(context: Context, restModel: TableRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getTable(key!!).subscribeWith(object : DisposableObserver<List<Table>>() {

            override fun onNext(@NonNull response: List<Table>) {
                output?.onSuccessGetData(response)
                val dataManager = DataManager (context)
                dataManager.clearTableAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val table = TableSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_table.toString(),
                            item.name_table.toString(),
                        )
                        inc++
                        var result = dataManager.addTable(table)
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