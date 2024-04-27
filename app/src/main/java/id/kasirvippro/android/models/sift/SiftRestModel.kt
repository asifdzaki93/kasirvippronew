package id.kasirvippro.android.models.sift

import android.content.Context
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SiftRestModel(context: Context) : RestModel<SiftRestInterface>(context) {

    override fun createRestInterface(): SiftRestInterface {
        return RestClient.getInstance()!!.createInterface(SiftRestInterface::class.java)
    }

    fun getSift(key:String): Observable<List<Sift>> {
        return restInterface.getSift(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}