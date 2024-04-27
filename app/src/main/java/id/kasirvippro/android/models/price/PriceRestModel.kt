package id.kasirvippro.android.models.price

import android.content.Context
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PriceRestModel(context: Context) : RestModel<PriceRestInterface>(context) {

    override fun createRestInterface(): PriceRestInterface {
        return RestClient.getInstance()!!.createInterface(PriceRestInterface::class.java)
    }

    fun gets(key:String, nominal:Double): Observable<List<Price>> {
        return restInterface.gets(key,nominal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}