package id.kasirvippro.android.models.table

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TableRestModel(context: Context) : RestModel<TableRestInterface>(context) {

    override fun createRestInterface(): TableRestInterface {
        return RestClient.getInstance()!!.createInterface(TableRestInterface::class.java)
    }

    fun getTable(key:String): Observable<List<Table>> {
        return restInterface.getTable(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTableOrder(key:String): Observable<List<Table>> {
        return restInterface.getTableOrder(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getAllTable(key:String): Observable<List<Table>> {
        return restInterface.getAllTable(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,name:String): Observable<Message> {
        return restInterface.add(key,name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,name:String): Observable<Message> {
        return restInterface.update(key,id,name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun moveTable(key:String,id:String,id_table:String,name:String): Observable<Message> {
        return restInterface.moveTable(key,id,id_table,name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun joinTable(key:String,id:String,id_table:String,name:String): Observable<Message> {
        return restInterface.joinTable(key,id,id_table,name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}