package id.kasirvippro.android.models.cartRaw

import android.content.Context
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CartRawRestModel(context: Context) : RestModel<CartRawRestInterface>(context) {

    override fun createRestInterface(): CartRawRestInterface {
        return RestClient.getInstance()!!.createInterface(CartRawRestInterface::class.java)
    }

    fun add(key:String,name:String,buy:String,sell:String): Observable<List<RawMaterial>> {
        return restInterface.add(key,name,buy,sell)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addWithBarcode(key:String,name:String,barcode:String,buy:String,sell:String): Observable<List<RawMaterial>> {
        return restInterface.addWithBarcode(key,name,barcode,buy,sell)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,name:String,barcode:String,buy:String,sell:String,stok:String): Observable<List<RawMaterial>> {
        return restInterface.update(key,id,name,barcode,buy,sell,stok)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}