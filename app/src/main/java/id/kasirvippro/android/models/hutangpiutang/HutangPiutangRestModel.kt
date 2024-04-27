package id.kasirvippro.android.models.hutangpiutang

import android.content.Context
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HutangPiutangRestModel(context: Context) : RestModel<HutangPiutangRestInterface>(context) {

    override fun createRestInterface(): HutangPiutangRestInterface {
        return RestClient.getInstance()!!.createInterface(HutangPiutangRestInterface::class.java)
    }

    fun getHutang(key:String): Observable<Hutang> {
        return restInterface.getHutang(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getPiutang(key:String): Observable<Piutang> {
        return restInterface.getPiutang(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getLastHutang(key:String): Observable<List<Hutang.Data>> {
        return restInterface.getLastHutang(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getLastPiutang(key:String): Observable<List<Piutang.Data>> {
        return restInterface.getLastPiutang(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSearchLastHutang(key:String,search:String): Observable<List<Hutang.Data>> {
        return restInterface.getSearchLastHutang(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSearchLastPiutang(key:String,search:String): Observable<List<Piutang.Data>> {
        return restInterface.getSearchLastPiutang(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getListHutangSupplier(key:String): Observable<List<Supplier>> {
        return restInterface.getListHutangSupplier(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getListPiutangCustomer(key:String): Observable<List<Customer>> {
        return restInterface.getListPiutangCustomer(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSearchHutangSupplier(key:String,search:String): Observable<List<Supplier>> {
        return restInterface.getSearchHutangSupplier(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSearchPiutangCustomer(key:String,search:String): Observable<List<Customer>> {
        return restInterface.getSearchPiutangCustomer(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailHutangSupplier(key:String,id:String): Observable<DetailHutang> {
        return restInterface.getDetailHutangSupplier(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDetailPiutangCustomer(key:String,id:String): Observable<DetailPiutang> {
        return restInterface.getDetailPiutangCustomer(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}