package id.kasirvippro.android.models.report

import android.content.Context
import id.kasirvippro.android.models.transaction.HistoryTransaction
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ReportRestModel(context: Context) : RestModel<ReportRestInterface>(context) {

    override fun createRestInterface(): ReportRestInterface {
        return RestClient.getInstance()!!.createInterface(ReportRestInterface::class.java)
    }

    fun transactions(key:String,awal:String,akhir:String): Observable<List<ReportTransaksi>> {
        return restInterface.transactions(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun productsales(key:String,awal:String,akhir:String): Observable<ReportProduct> {
            return restInterface.productsales(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchTransactions(key:String,search:String): Observable<List<ReportTransaksi>> {
        return restInterface.searchTransactions(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchTransactionsp(key:String,search:String): Observable<List<ReportProduct>> {
        return restInterface.searchTransactionsp(key,search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun sortTransactions(key:String,awal:String,akhir:String): Observable<List<ReportTransaksi>> {
        return restInterface.sortTransactions(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun labaRugi(key:String,awal:String,akhir:String): Observable<ReportLabaRugi> {
        return restInterface.labaRugi(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun mutasi(key:String,awal:String,akhir:String): Observable<ReportMutasi> {
        return restInterface.mutasi(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun stock(key:String,awal:String,akhir:String): Observable<List<ReportStock>> {
        return restInterface.stock(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchStock(key:String,cari:String,awal:String,akhir:String): Observable<List<ReportStock>> {
        return restInterface.searchStock(key,cari,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun sortStock(key:String,awal:String,akhir:String): Observable<List<ReportStock>> {
        return restInterface.sortStock(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun sortPriceStock(key:String,awal:String,akhir:String): Observable<List<ReportStock>> {
        return restInterface.sortPriceStock(key,awal,akhir)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun kulakan(key:String,awal:String,akhir:String,status:String): Observable<List<HistoryTransaction>> {
        return restInterface.kulakan(key,awal,akhir,status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchKulakan(key:String,id:String): Observable<List<Transaction>> {
        return restInterface.searchKulakan(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun penjualan(key:String,awal:String,akhir:String,id:String?): Observable<ReportPenjualan> {
        return restInterface.penjualan(key,awal,akhir,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun preorder(key:String,awal:String,akhir:String,status:String): Observable<List<HistoryPreOrder>> {
        return restInterface.preorder(key,awal,akhir,status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun rawmaterial(key:String,awal:String,akhir:String,status:String): Observable<List<HistoryRawMaterial>> {
        return restInterface.rawmaterial(key,awal,akhir,status)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchpreorder(key:String,id:String): Observable<List<ReportPreOrder>> {
        return restInterface.searchpreorder(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun searchrawmaterial(key:String,id:String): Observable<List<ReportRawMaterial>> {
        return restInterface.searchrawmaterial(key,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun daily(key:String,awal:String,akhir:String,id:String?): Observable<ReportDaily> {
        return restInterface.daily(key,awal,akhir,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}