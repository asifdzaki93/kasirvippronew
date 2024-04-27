package id.kasirvippro.android.models.staff

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import id.kasirvippro.android.utils.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StaffRestModel(context: Context) : RestModel<StaffRestInterface>(context) {

    override fun createRestInterface(): StaffRestInterface {
        return RestClient.getInstance()!!.createInterface(StaffRestInterface::class.java)
    }

    fun gets(key:String): Observable<List<Staff>> {
        return restInterface.getStaff(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getKurir(key:String): Observable<List<Staff>> {
        return restInterface.getKurir(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun search(key:String,search:String): Observable<List<Staff>> {
        return restInterface.search(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,name:String,email:String,telpon:String,alamat:String,gaji:String,
            komisi:String,tunjangan:String,potongan:String,toko:String,level:String,img:String?): Observable<Message> {
        return restInterface.add(
            Helper.createPartFromString(key),
            Helper.createPartFromString(name),
            Helper.createPartFromString(email),
            Helper.createPartFromString(telpon),
            Helper.createPartFromString(alamat),
            Helper.createPartFromString(gaji),
            Helper.createPartFromString(komisi),
            Helper.createPartFromString(tunjangan),
            Helper.createPartFromString(potongan),
            Helper.createPartFromString(toko),
            Helper.createPartFromString(level),
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,name:String,email:String,telpon:String,alamat:String,gaji:String,
               komisi:String,tunjangan:String,potongan:String,toko:String,level:String,img:String?): Observable<Message> {
        return restInterface.update(
            Helper.createPartFromString(key),
            Helper.createPartFromString(id),
            Helper.createPartFromString(name),
            Helper.createPartFromString(email),
            Helper.createPartFromString(telpon),
            Helper.createPartFromString(alamat),
            Helper.createPartFromString(gaji),
            Helper.createPartFromString(komisi),
            Helper.createPartFromString(tunjangan),
            Helper.createPartFromString(potongan),
            Helper.createPartFromString(toko),
            Helper.createPartFromString(level),
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}