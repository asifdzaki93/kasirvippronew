package id.kasirvippro.android.models.kelolatoko

import android.content.Context
import id.kasirvippro.android.models.Message
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import id.kasirvippro.android.utils.Helper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class KelolatokoRestModel(context: Context) : RestModel<KelolatokoRestInterface>(context) {

    override fun createRestInterface(): KelolatokoRestInterface {
        return RestClient.getInstance()!!.createInterface(KelolatokoRestInterface::class.java)
    }

    fun gets(key:String): Observable<List<Kelolatoko>> {
        return restInterface.gets(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun update(key:String,id:String,nama_toko:String,alamat_toko:String,warna_toko:String,jam_operasional:String,linkfb:String,linkinstagram:String,nowa:String,subdomain:String,img:String?): Observable<Message> {
        return restInterface.update(
            Helper.createPartFromString(key),
            Helper.createPartFromString(id),
            Helper.createPartFromString(nama_toko),
            Helper.createPartFromString(alamat_toko),
            Helper.createPartFromString(warna_toko),
            Helper.createPartFromString(jam_operasional),
            Helper.createPartFromString(linkfb),
            Helper.createPartFromString(linkinstagram),
            Helper.createPartFromString(nowa),
            Helper.createPartFromString(subdomain),
            Helper.createPartFromFile(img,"img"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}