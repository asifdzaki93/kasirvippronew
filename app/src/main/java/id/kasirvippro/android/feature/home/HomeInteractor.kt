package id.kasirvippro.android.feature.home

import android.content.Context
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.models.news.NewsRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.models.transaction.NonTunai
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.CategorySQL
import id.kasirvippro.android.sqlite.Model.LinkSQL
import id.kasirvippro.android.sqlite.Model.StoreSQL
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class HomeInteractor(var output: HomeContract.InteractorOutput?) : HomeContract.Interactor {

    private var appSession = AppSession()
    private var disposable = CompositeDisposable()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun getUserLevel(context: Context):String? {
        return appSession.getLevel(context)
    }

    override fun getUserPackage(context: Context):String? {
        return appSession.getPackage(context)
    }

    override fun callGetNewsAPI(context: Context, restModel: NewsRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getNews(key!!).subscribeWith(object : DisposableObserver<List<News>>() {

            override fun onNext(@NonNull response: List<News>) {
                output?.onSuccessGetNews(response)
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

    override fun callGetStoreAPI(context: Context, restModel: StoreRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getStore(key!!).subscribeWith(object : DisposableObserver<List<Store>>() {

            override fun onNext(@NonNull response: List<Store>) {
                output?.onSuccessGetStore(response)
                val dataManager = DataManager (context)
                dataManager.clearStoreAll()
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val store = StoreSQL(
                            inc.toString(),
                            item.id_store.toString(),
                            item.type.toString(),
                            item.name_store.toString(),
                            item.nohp.toString(),
                            item.address.toString(),
                            item.email.toString(),
                            item.omset.toString(),
                            item.transaksi.toString(),
                            item.order.toString(),
                            item.tax.toString(),
                            item.service_charge.toString(),
                            item.number_store.toString(),
                            item.level.toString(),
                            item.footer.toString(),
                            item.photo.toString(),
                        )
                        inc++
                        var result = dataManager.addStore(store)
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

    override fun callGetCategoriesAPI(context: Context, restModel: CategoryRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getCategories(key!!).subscribeWith(object : DisposableObserver<List<Category>>() {

            override fun onNext(@NonNull response: List<Category>) {
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


    override fun callGetlinkAPI(context: Context, restModel: TransactionRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getNonTunai(key!!).subscribeWith(object : DisposableObserver<List<NonTunai>>() {

            override fun onNext(@NonNull response: List<NonTunai>) {
                val dataManager = DataManager (context)
                var inc = 1
                if(response.size > 0){
                    for (item in response){
                        val link = LinkSQL(
                            inc.toString(),
                            key.toString(),
                            item.id_link.toString(),
                            item.name_link.toString(),
                            item.img.toString(),
                        )
                        inc++
                        var result = dataManager.addLink(link)
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