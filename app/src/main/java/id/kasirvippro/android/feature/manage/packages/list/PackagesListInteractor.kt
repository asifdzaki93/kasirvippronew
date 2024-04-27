package id.kasirvippro.android.feature.manage.packages.list;

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.packages.Packages
import id.kasirvippro.android.models.packages.PackagesRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class PackagesListInteractor(var output: PackagesListContract.InteractorOutput?) : PackagesListContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetCategoriesAPI(context: Context, restModel: PackagesRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getCategories(key!!).subscribeWith(object : DisposableObserver<List<Packages>>() {

            override fun onNext(@NonNull response: List<Packages>) {
                output?.onSuccessGetCategories(response)
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

    override fun callDeleteCategoryAPI(context: Context, restModel: PackagesRestModel, id: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.deleteCategory(key!!,id).subscribeWith(object : DisposableObserver<Message>() {

            override fun onNext(@NonNull response: Message) {
                output?.onSuccessDeleteCategory(response.message)
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

    override fun callSearchCategoryAPI(context: Context, restModel: PackagesRestModel, search: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.searchCategory(key!!,search).subscribeWith(object : DisposableObserver<List<Packages>>() {

            override fun onNext(@NonNull response: List<Packages>) {
                output?.onSuccessGetCategories(response)
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