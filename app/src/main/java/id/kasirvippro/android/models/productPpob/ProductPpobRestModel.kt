package id.kasirvippro.android.models.productPpob

import android.content.Context
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductPpobRestModel(context: Context) : RestModel<ProductPpobRestInterface>(context) {

    override fun createRestInterface(): ProductPpobRestInterface {
        return RestClient.getInstance()!!.createInterface(ProductPpobRestInterface::class.java)
    }

    fun getProduct(key:String,jenis:String): Observable<List<ProductPpob>> {
        return restInterface.getProduct(key,jenis)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}