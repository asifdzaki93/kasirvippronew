package id.kasirvippro.android.models.priceVariant

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PriceVariantRestModel(context: Context) : RestModel<PriceVariantRestInterface>(context) {

    override fun createRestInterface(): PriceVariantRestInterface {
        return RestClient.getInstance()!!.createInterface(PriceVariantRestInterface::class.java)
    }

    fun gets(key:String, id_product:String): Observable<List<PriceVariant>> {
        return restInterface.gets(key,id_product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,nominal:String,id_product: String,price: String): Observable<Message> {
        return restInterface.add(key, nominal,id_product,price)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,minimal:String,nominal: String): Observable<Message> {
        return restInterface.update(key,id,minimal,nominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}