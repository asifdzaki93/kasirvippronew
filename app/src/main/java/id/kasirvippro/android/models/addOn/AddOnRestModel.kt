package id.kasirvippro.android.models.addOn

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddOnRestModel(context: Context) : RestModel<AddOnRestInterface>(context) {

    override fun createRestInterface(): AddOnRestInterface {
        return RestClient.getInstance()!!.createInterface(AddOnRestInterface::class.java)
    }

    fun gets(key:String, id_product:String): Observable<List<AddOn>> {
        return restInterface.gets(key,id_product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,nama:String,nominal:String): Observable<Message> {
        return restInterface.add(key,nama, nominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,nama:String,nominal:String): Observable<Message> {
        return restInterface.update(key,id,nama,nominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}