package id.kasirvippro.android.models.pulsaPpob

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import id.kasirvippro.android.utils.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PulsaPpobRestModel(context: Context) : RestModel<PulsaPpobRestInterface>(context) {

    override fun createRestInterface(): PulsaPpobRestInterface {
        return RestClient.getInstance()!!.createInterface(PulsaPpobRestInterface::class.java)
    }


    fun searchPrefix(key:String,jenis:String,search:String): Observable<List<PulsaPpob>> {
        return restInterface.searchPrefix(key,jenis,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchProduck(key:String,category:String,brand:String,search:String): Observable<List<PulsaPpob>> {
        return restInterface.searchProduck(key,category,brand,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun cekTagihan(key:String,brand:String,search:String): Observable<List<DetailPpob>> {
        return restInterface.cekTagihan(key,brand,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchToken(key:String,jenis:String,search:String): Observable<List<PulsaPpob>> {
        return restInterface.searchToken(key,jenis,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun getProduct(key:String,jenis:String): Observable<List<PulsaPpob>> {
        return restInterface.getProduct(key,jenis)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun order(key:String,phone:String,sku:String,hargajual:String,pin:String,product_name:String): Observable<Message> {
        return restInterface.order(
            Helper.createPartFromString(key),
            Helper.createPartFromString(phone),
            Helper.createPartFromString(sku),
            Helper.createPartFromString(hargajual),
            Helper.createPartFromString(pin),
            Helper.createPartFromString(product_name))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun bayar(key:String,phone:String,sku:String,hargajual:String,pin:String,ref_id:String,hargaagent:String): Observable<Message> {
        return restInterface.bayar(
            Helper.createPartFromString(key),
            Helper.createPartFromString(phone),
            Helper.createPartFromString(sku),
            Helper.createPartFromString(hargajual),
            Helper.createPartFromString(pin),
            Helper.createPartFromString(ref_id),
            Helper.createPartFromString(hargaagent))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun cekToken(key:String,phone:String): Observable<Message> {
        return restInterface.cekToken(
            Helper.createPartFromString(key),
            Helper.createPartFromString(phone))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}