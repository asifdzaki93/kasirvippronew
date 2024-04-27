package id.kasirvippro.android.models.ongkir

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OngkirRestModel(context: Context) : RestModel<OngkirRestInterface>(context) {

    override fun createRestInterface(): OngkirRestInterface {
        return RestClient.getInstance()!!.createInterface(OngkirRestInterface::class.java)
    }

    fun getOngkir(key:String): Observable<List<Ongkir>> {
        return restInterface.getOngkir(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addOngkir(key:String,name_ongkir:String,nominal:String): Observable<Message> {
        return restInterface.addOngkir(key,name_ongkir,nominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addOngkirProduct(key:String,name_ongkir:String,nominal:String): Observable<List<Ongkir>> {
        return restInterface.addOngkirProduct(key,name_ongkir, nominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateOngkir(key:String,id:String,name_ongkir:String,nominal:String): Observable<Message> {
        return restInterface.updateOngkir(key,id,name_ongkir,nominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteOngkir(key:String,id:String): Observable<Message> {
        return restInterface.deleteOngkir(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}