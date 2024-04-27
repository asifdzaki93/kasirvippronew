package id.kasirvippro.android.models.closeShift

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CloseShiftRestModel(context: Context) : RestModel<CloseShiftRestInterface>(context) {

    override fun createRestInterface(): CloseShiftRestInterface {
        return RestClient.getInstance()!!.createInterface(CloseShiftRestInterface::class.java)
    }

    fun gets(key:String): Observable<List<CloseShift>> {
        return restInterface.gets(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
	
    fun update(key:String,id:String,cash_actual:String): Observable<Message> {
        return restInterface.update(key,id,cash_actual)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun dailyClosing(key:String,id:String): Observable<Message> {
        return restInterface.dailyClosing(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}