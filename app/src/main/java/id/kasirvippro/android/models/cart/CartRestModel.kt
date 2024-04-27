package id.kasirvippro.android.models.cart

import android.content.Context
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CartRestModel(context: Context) : RestModel<CartRestInterface>(context) {

    override fun createRestInterface(): CartRestInterface {
        return RestClient.getInstance()!!.createInterface(CartRestInterface::class.java)
    }

    fun add(key:String,name:String,buy:String,sell:String): Observable<List<Product>> {
        return restInterface.add(key,name,buy,sell)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addWithBarcode(key:String,name:String,barcode:String,buy:String,sell:String): Observable<List<Product>> {
        return restInterface.addWithBarcode(key,name,barcode,buy,sell)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,name:String,barcode:String,buy:String,sell:String,stok:String): Observable<List<Product>> {
        return restInterface.update(key,id,name,barcode,buy,sell,stok)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}