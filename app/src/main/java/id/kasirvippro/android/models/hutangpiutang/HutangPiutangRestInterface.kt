package id.kasirvippro.android.models.hutangpiutang

import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.supplier.Supplier
import io.reactivex.Observable
import retrofit2.http.*

interface HutangPiutangRestInterface {

    @GET("supplier/datadebt.php")
    fun getHutang(
        @Query("key") key:String): Observable<Hutang>

    @GET("customer/customerdebt.php")
    fun getPiutang(
        @Query("key") key:String): Observable<Piutang>

    @GET("supplier/seedebt.php")
    fun getLastHutang(
        @Query("key") key:String): Observable<List<Hutang.Data>>

    @GET("customer/seedebt.php")
    fun getLastPiutang(
        @Query("key") key:String): Observable<List<Piutang.Data>>

    @GET("supplier/searchseedebt.php")
    fun getSearchLastHutang(
        @Query("key") key:String,
        @Query("search") search:String): Observable<List<Hutang.Data>>

    @GET("customer/searchseedebt.php")
    fun getSearchLastPiutang(
        @Query("key") key:String,
        @Query("search") search:String): Observable<List<Piutang.Data>>

    @GET("supplier/listdebt.php")
    fun getListHutangSupplier(
        @Query("key") key:String): Observable<List<Supplier>>

    @GET("customer/listdebt.php")
    fun getListPiutangCustomer(
        @Query("key") key:String): Observable<List<Customer>>

    @GET("supplier/searchebt.php")
    fun getSearchHutangSupplier(
        @Query("key") key:String,
        @Query("search") search:String): Observable<List<Supplier>>

    @GET("customer/searchdebt.php")
    fun getSearchPiutangCustomer(
        @Query("key") key:String,
        @Query("search") search:String): Observable<List<Customer>>

    @GET("supplier/detaildebtpersupplier.php")
    fun getDetailHutangSupplier(
        @Query("key") key:String,
        @Query("id_supplier") id:String): Observable<DetailHutang>

    @GET("customer/detailcustomerdebt.php")
    fun getDetailPiutangCustomer(
        @Query("key") key:String,
        @Query("id_customer") id:String): Observable<DetailPiutang>



}