package id.kasirvippro.android.models.store

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import id.kasirvippro.android.utils.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StoreRestModel(context: Context) : RestModel<StoreRestInterface>(context) {

    override fun createRestInterface(): StoreRestInterface {
        return RestClient.getInstance()!!.createInterface(StoreRestInterface::class.java)
    }

    fun getStore(key:String): Observable<List<Store>> {
        return restInterface.getStore(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateStore(key:String,id:String,name:String,email:String,phone:String,alamat:String,pajak:String,service:String,currency:String,footer:String,shift:String,img:String?): Observable<Message> {
        return restInterface.updateStore(
            Helper.createPartFromString(key),
            Helper.createPartFromString(id),
            Helper.createPartFromString(name),
            Helper.createPartFromString(email),
            Helper.createPartFromString(phone),
            Helper.createPartFromString(alamat),
            Helper.createPartFromString(pajak),
            Helper.createPartFromString(service),
            Helper.createPartFromString(currency),
            Helper.createPartFromString(footer),
            Helper.createPartFromString(shift),
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun gets(key:String): Observable<List<Store>> {
        return restInterface.gets(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,name:String,email:String,telpon:String,alamat:String,pajak:String,service:String,currency:String,footer:String): Observable<Message> {
        return restInterface.add(key,name,email,telpon,pajak,service,alamat,currency,footer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}