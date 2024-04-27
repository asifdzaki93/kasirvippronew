package id.kasirvippro.android.models.discount

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DiscountRestModel(context: Context) : RestModel<DiscountRestInterface>(context) {

    override fun createRestInterface(): DiscountRestInterface {
        return RestClient.getInstance()!!.createInterface(DiscountRestInterface::class.java)
    }

    fun gets(key:String): Observable<List<Discount>> {
        return restInterface.gets(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,nama:String,keterangan:String,jenis:String,nominal:String): Observable<Message> {
        return restInterface.add(key,nama,keterangan, jenis, nominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addPenjualan(key:String,nama:String,keterangan:String,jenis:String,nominal:String): Observable<List<Discount>> {
        return restInterface.addPenjualan(key,nama,keterangan, jenis, nominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,nama:String,keterangan:String,jenis:String,nominal:String): Observable<Message> {
        return restInterface.update(key,id,nama,keterangan, jenis, nominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}