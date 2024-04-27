package id.kasirvippro.android.models.role

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoleRestModel(context: Context) : RestModel<RoleRestInterface>(context) {

    override fun createRestInterface(): RoleRestInterface {
        return RestClient.getInstance()!!.createInterface(RoleRestInterface::class.java)
    }

    fun getRole(key:String): Observable<List<Role>> {
        return restInterface.getRole(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRoleUser(key:String): Observable<List<Role>> {
        return restInterface.getRoleUser(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getDetailRole(key:String,id:String): Observable<List<Role>> {
        return restInterface.getDetailRole(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateRole(key:String,value:String,id:String,name:String): Observable<Message> {
        return restInterface.updateRole(key,value,id, name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}