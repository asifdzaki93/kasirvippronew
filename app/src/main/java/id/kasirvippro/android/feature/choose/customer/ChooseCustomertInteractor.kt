package id.kasirvippro.android.feature.choose.customer;

import android.content.Context
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.CustomerSQL
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class ChooseCustomertInteractor(var output: ChooseCustomerContract.InteractorOutput?) : ChooseCustomerContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetDataAPI(context: Context, restModel: CustomerRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.gets(key!!).subscribeWith(object : DisposableObserver<List<Customer>>() {

            override fun onNext(@NonNull response: List<Customer>) {
                output?.onSuccessGetData(response)
                val dataManager = DataManager (context)
                dataManager.clearCustomerAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val customer = CustomerSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_customer.toString(),
                            item.name_customer.toString(),
                            item.telephone.toString(),
                            item.email.toString(),
                            item.address.toString(),
                            item.customercode.toString(),
                        )
                        inc++
                        var result = dataManager.addCustomer(customer)
                    }
                }
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

    override fun callSearchAPI(context: Context, restModel: CustomerRestModel, search: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.search(key!!,search).subscribeWith(object : DisposableObserver<List<Customer>>() {

            override fun onNext(@NonNull response: List<Customer>) {
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