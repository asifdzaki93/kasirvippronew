package id.kasirvippro.android.models.slip

import android.content.Context
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SlipRestModel(context: Context) : RestModel<SlipRestInterface>(context) {

    override fun createRestInterface(): SlipRestInterface {
        return RestClient.getInstance()!!.createInterface(SlipRestInterface::class.java)
    }

    fun getPaySlip(key:String,awal:String,akhir:String): Observable<List<Slip>> {
        return restInterface.getPaySlip(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAbsent(key:String,awal:String,akhir:String): Observable<List<Absent>> {
        return restInterface.getAbsent(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}