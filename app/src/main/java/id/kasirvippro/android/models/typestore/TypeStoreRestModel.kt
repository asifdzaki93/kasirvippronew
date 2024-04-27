package id.kasirvippro.android.models.typestore

import android.content.Context
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TypeStoreRestModel(context: Context) : RestModel<TypeStoreRestInterface>(context) {

    override fun createRestInterface(): TypeStoreRestInterface {
        return RestClient.getInstance()!!.createInterface(TypeStoreRestInterface::class.java)
    }

    fun geTypestore(key: String): Observable<List<TypeStore>> {
        return restInterface.geTypestore()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}