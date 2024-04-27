package id.kasirvippro.android.models.initial

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class InitialRestModel(context: Context) : RestModel<InitialRestInterface>(context) {

    override fun createRestInterface(): InitialRestInterface {
        return RestClient.getInstance()!!.createInterface(InitialRestInterface::class.java)
    }

    fun getInitial(key:String): Observable<List<Initial>> {
        return restInterface.getInitial(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
	
    fun input(key:String,modal_awal:String,sift:String): Observable<Message> {
        return restInterface.input(key,modal_awal,sift)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}