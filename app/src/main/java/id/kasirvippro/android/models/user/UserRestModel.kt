package id.kasirvippro.android.models.user

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import id.kasirvippro.android.utils.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRestModel(context: Context) : RestModel<UserRestInterface>(context) {

    override fun createRestInterface(): UserRestInterface {
        return RestClient.getInstance()!!.createInterface(UserRestInterface::class.java)
    }

    fun getProfile(key:String): Observable<List<User>> {
        return restInterface.getProfile(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateProfile(key:String,name:String,email:String,telpon:String,alamat:String,img:String?): Observable<Message> {
        return restInterface.updateProfile(
            Helper.createPartFromString(key),
            Helper.createPartFromString(name),
            Helper.createPartFromString(email),
            Helper.createPartFromString(telpon),
            Helper.createPartFromString(alamat),
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun changePassword(key:String,lama:String,baru:String): Observable<Message> {
        return restInterface.changePassword(key,lama,baru)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun login(user:String,password:String,latitude:Double,longitude:Double): Observable<List<Login>> {
        return restInterface.login(user,password,latitude,longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun register(toko:String,currency:String,name:String,email:String,telpon:String,password:String,alamat:String,referal:String,typestore:String,decimal:String): Observable<Message> {
        return restInterface.register(toko,currency,name,email,telpon,password,alamat,referal,typestore,decimal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun forgotPassword(email:String,phone:String): Observable<Message> {
        return restInterface.forgotPassword(email,phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}