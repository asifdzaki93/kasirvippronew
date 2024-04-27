package id.kasirvippro.android.models.network

import android.content.Context
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NetworkRestModel(context: Context) : RestModel<NetworkRestInterface>(context) {

    override fun createRestInterface(): NetworkRestInterface {
        return RestClient.getInstance()!!.createInterface(NetworkRestInterface::class.java)
    }

    fun gets(key:String): Observable<List<Network>> {
        return restInterface.getStaff(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getsub(key:String): Observable<List<Network>> {
        return restInterface.getSub(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun search(key:String,search:String): Observable<List<Network>> {
        return restInterface.search(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}