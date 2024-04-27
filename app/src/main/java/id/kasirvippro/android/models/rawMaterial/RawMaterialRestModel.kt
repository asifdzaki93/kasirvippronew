package id.kasirvippro.android.models.rawMaterial

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import id.kasirvippro.android.utils.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RawMaterialRestModel(context: Context) : RestModel<RawMaterialRestInterface>(context) {

    override fun createRestInterface(): RawMaterialRestInterface {
        return RestClient.getInstance()!!.createInterface(RawMaterialRestInterface::class.java)
    }

    fun gets(key:String): Observable<List<RawMaterial>> {
        return restInterface.gets(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getsStock(key:String): Observable<List<RawMaterial>> {
        return restInterface.getsStock(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun choose(key:String,check:String): Observable<List<RawMaterial>> {
        return restInterface.choose(key,check)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun chooseSearch(key:String,search:String,check:String): Observable<List<RawMaterial>> {
        return restInterface.chooseSearch(key,search,check)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,name:String,unit:String,jual:String,stok:String,
            desk:String): Observable<Message> {
        return restInterface.add(
            Helper.createPartFromString(key),
            Helper.createPartFromString(name),
            Helper.createPartFromString(unit),
            Helper.createPartFromString(jual),
            Helper.createPartFromString(stok),
            Helper.createPartFromString(desk))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,name:String,unit:String,jual:String,stok:String,
               desk:String): Observable<Message> {
        return restInterface.update(
            Helper.createPartFromString(key),
            Helper.createPartFromString(id),
            Helper.createPartFromString(name),
            Helper.createPartFromString(unit),
            Helper.createPartFromString(jual),
            Helper.createPartFromString(stok),
            Helper.createPartFromString(desk))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateStock(key:String,id:String,stok:String): Observable<Message> {
        return restInterface.updateStock(
            Helper.createPartFromString(key),
            Helper.createPartFromString(id),
            Helper.createPartFromString(stok))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun search(key:String,search:String): Observable<List<RawMaterial>> {
        return restInterface.search(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchStock(key:String,search:String): Observable<List<RawMaterial>> {
        return restInterface.searchStock(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}