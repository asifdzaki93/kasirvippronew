package id.kasirvippro.android.models.report

import id.kasirvippro.android.models.transaction.HistoryTransaction
import id.kasirvippro.android.models.transaction.Transaction
import io.reactivex.Observable
import retrofit2.http.*

interface ReportRestInterface {

    @GET("report/transaction.php")
    fun transactions(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String): Observable<List<ReportTransaksi>>

    @GET("report/productsales.php")
    fun productsales(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String): Observable<ReportProduct>

    @GET("report/searchtransaction.php")
    fun searchTransactions(
        @Query("key") key:String,
        @Query("search") search:String): Observable<List<ReportTransaksi>>

    @GET("report/searchtransactionproduct.php")
    fun searchTransactionsp(
        @Query("key") key:String,
        @Query("search") search:String): Observable<List<ReportProduct>>

    @GET("report/sorttransaksi.php")
    fun sortTransactions(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String): Observable<List<ReportTransaksi>>

    @GET("report/labarugi.php")
    fun labaRugi(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String): Observable<ReportLabaRugi>

    @GET("report/mutasikas.php")
    fun mutasi(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String): Observable<ReportMutasi>

    @GET("report/stokproduct.php")
    fun stock(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String): Observable<List<ReportStock>>

    @GET("report/searchstock.php")
    fun searchStock(
        @Query("key") key:String,
        @Query("search") search:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String): Observable<List<ReportStock>>

    @GET("report/sortstockproduct.php")
    fun sortStock(
        @Query("key") key:String,
       @Query("first_date") awal:String,
        @Query("last_date") akhir:String): Observable<List<ReportStock>>

    @GET("laporan/sorthargastok.php")
    fun sortPriceStock(
        @Query("key") key:String,
        @Query("tanggal_awal") awal:String,
        @Query("tanggal_akhir") akhir:String): Observable<List<ReportStock>>

    @GET("report/historikulakan.php")
    fun kulakan(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("status") status:String): Observable<List<HistoryTransaction>>

    @GET("report/carihistorikulakan.php")
    fun searchKulakan(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Transaction>>

    @GET("report/sales.php")
    fun penjualan(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("id_store") toko:String?): Observable<ReportPenjualan>

    @GET("report/preorder.php")
    fun preorder(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("status") status:String): Observable<List<HistoryPreOrder>>

    @GET("report/rawmaterial.php")
    fun rawmaterial(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("status") status:String): Observable<List<HistoryRawMaterial>>

    @GET("report/caripreorder.php")
    fun searchpreorder(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<ReportPreOrder>>

    @GET("report/carirawmaterial.php")
    fun searchrawmaterial(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<ReportRawMaterial>>


    @GET("report/daily.php")
    fun daily(
        @Query("key") key:String,
        @Query("first_date") awal:String,
        @Query("last_date") akhir:String,
        @Query("id_store") toko:String?): Observable<ReportDaily>
}