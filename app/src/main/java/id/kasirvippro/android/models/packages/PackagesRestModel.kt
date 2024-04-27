package id.kasirvippro.android.models.packages

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import id.kasirvippro.android.utils.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PackagesRestModel(context: Context) : RestModel<PackagesRestInterface>(context) {

    override fun createRestInterface(): PackagesRestInterface {
        return RestClient.getInstance()!!.createInterface(PackagesRestInterface::class.java)
    }

    fun getCategories(key:String): Observable<List<Packages>> {
        return restInterface.getCategories(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getProduct(key:String, sesi:String): Observable<List<Product>> {
        return restInterface.getProduct(key,sesi)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteCategory(key:String,id:String): Observable<Message> {
        return restInterface.deleteCategory(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteProduct(key:String,id:String,sesi:String): Observable<Message> {
        return restInterface.deleteProduct(key,id,sesi)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchCategory(key:String,search:String): Observable<List<Packages>> {
        return restInterface.searchCategory(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updatePackages(key:String,id:String,name:String,price:String,img:String?): Observable<Message> {
        return restInterface.updatePackages(
            Helper.createPartFromString(key),
            Helper.createPartFromString(id),
            Helper.createPartFromString(name),
            Helper.createPartFromString(price),
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}