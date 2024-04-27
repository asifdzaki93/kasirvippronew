package id.kasirvippro.android.feature.manage.category.list;

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.CategorySQL
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class CategoryListInteractor(var output: CategoryListContract.InteractorOutput?) : CategoryListContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetCategoriesAPI(context: Context, restModel: CategoryRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getCategories(key!!).subscribeWith(object : DisposableObserver<List<Category>>() {

            override fun onNext(@NonNull response: List<Category>) {
                output?.onSuccessGetCategories(response)
                val dataManager = DataManager (context)
                dataManager.clearCategoryAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val category = CategorySQL(
                            inc.toString(),
                            key.toString(),
                            item.id_category.toString(),
                            item.name_category.toString(),
                        )
                        inc++
                        var result = dataManager.addCategory(category)
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

    override fun callDeleteCategoryAPI(context: Context, restModel: CategoryRestModel, id: String) {
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

    override fun callSearchCategoryAPI(context: Context, restModel: CategoryRestModel, search: String) {
        val key = appSession.getToken(context)
        disposable.add(restModel.searchCategory(key!!,search).subscribeWith(object : DisposableObserver<List<Category>>() {

            override fun onNext(@NonNull response: List<Category>) {
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