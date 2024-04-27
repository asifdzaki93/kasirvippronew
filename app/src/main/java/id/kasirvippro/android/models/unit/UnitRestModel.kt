package id.kasirvippro.android.models.unit

import android.content.Context
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UnitRestModel(context: Context) : RestModel<UnitRestInterface>(context) {

    override fun createRestInterface(): UnitRestInterface {
        return RestClient.getInstance()!!.createInterface(UnitRestInterface::class.java)
    }

    fun getUnit(key:String): Observable<List<Unit>> {
        return restInterface.getUnit(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}