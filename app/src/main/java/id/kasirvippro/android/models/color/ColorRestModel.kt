package id.kasirvippro.android.models.color

import android.content.Context
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ColorRestModel(context: Context) : RestModel<ColorRestInterface>(context) {

    override fun createRestInterface(): ColorRestInterface {
        return RestClient.getInstance()!!.createInterface(ColorRestInterface::class.java)
    }

    fun getColor(key:String): Observable<List<Color>> {
        return restInterface.getColor(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}