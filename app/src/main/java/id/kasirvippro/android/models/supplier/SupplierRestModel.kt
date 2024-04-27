package id.kasirvippro.android.models.supplier

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SupplierRestModel(context: Context) : RestModel<SupplierRestInterface>(context) {

    override fun createRestInterface(): SupplierRestInterface {
        return RestClient.getInstance()!!.createInterface(SupplierRestInterface::class.java)
    }

    fun gets(key:String): Observable<List<Supplier>> {
        return restInterface.gets(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,name:String,email:String,telpon:String,alamat:String): Observable<Message> {
        return restInterface.add(key,name,email,telpon,alamat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,name:String,email:String,telpon:String,alamat:String): Observable<Message> {
        return restInterface.update(key,id,name,email,telpon,alamat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun search(key:String,search:String): Observable<List<Supplier>> {
        return restInterface.search(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun detail(key:String,id:String): Observable<Supplier> {
        return restInterface.detail(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}