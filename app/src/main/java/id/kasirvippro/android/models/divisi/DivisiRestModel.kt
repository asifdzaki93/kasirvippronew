package id.kasirvippro.android.models.divisi

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DivisiRestModel(context: Context) : RestModel<DivisiRestInterface>(context) {

    override fun createRestInterface(): DivisiRestInterface {
        return RestClient.getInstance()!!.createInterface(DivisiRestInterface::class.java)
    }

    fun getDivisi(key:String): Observable<List<Divisi>> {
        return restInterface.getDivisi(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addDivisi(key:String,name_divisi:String): Observable<Message> {
        return restInterface.addDivisi(key,name_divisi)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateDivisi(key:String,id:String,name_divisi:String): Observable<Message> {
        return restInterface.updateDivisi(key,id,name_divisi)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteDivisi(key:String,id:String): Observable<Message> {
        return restInterface.deleteDivisi(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}