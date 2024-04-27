package id.kasirvippro.android.models.customer

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import id.kasirvippro.android.utils.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CustomerRestModel(context: Context) : RestModel<CustomerRestInterface>(context) {

    override fun createRestInterface(): CustomerRestInterface {
        return RestClient.getInstance()!!.createInterface(CustomerRestInterface::class.java)
    }

    fun gets(key:String): Observable<List<Customer>> {
        return restInterface.gets(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


    fun getMedical(key:String, id_customer:String): Observable<List<MedicalRecord>> {
        return restInterface.getMedical(key,id_customer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun add(key:String,name:String,email:String,telpon:String,alamat:String,customercode:String): Observable<Message> {
        return restInterface.add(
            Helper.createPartFromString(key),
            Helper.createPartFromString(name),
            Helper.createPartFromString(email),
            Helper.createPartFromString(telpon),
            Helper.createPartFromString(alamat),
            Helper.createPartFromString(customercode))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,name:String,email:String,telpon:String,alamat:String,customercode:String): Observable<Message> {
        return restInterface.update(
            Helper.createPartFromString(key),
            Helper.createPartFromString(id),
            Helper.createPartFromString(name),
            Helper.createPartFromString(email),
            Helper.createPartFromString(telpon),
            Helper.createPartFromString(alamat),
            Helper.createPartFromString(customercode))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun delete(key:String,id:String): Observable<Message> {
        return restInterface.delete(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun search(key:String,search:String): Observable<List<Customer>> {
        return restInterface.search(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun detail(key:String,id:String): Observable<Customer> {
        return restInterface.detail(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun addPenjualan(key:String,name:String,email:String,telpon:String,alamat:String): Observable<List<Customer>> {
        return restInterface.addPenjualan(key,name,email,telpon,alamat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}