package id.kasirvippro.android.models.pulsaPpob

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface PulsaPpobRestInterface {

    @GET("ppob/api/prefix.php")
    fun searchPrefix(
        @Query("key") key:String,
        @Query("jenis") jenis:String,
        @Query("search") id:String): Observable<List<PulsaPpob>>

    @GET("ppob/api/produk.php")
    fun searchProduck(
        @Query("key") key:String,
        @Query("category") category:String,
        @Query("brand") brand:String,
        @Query("search") id:String): Observable<List<PulsaPpob>>

    @GET("ppob/api/cektagihan.php")
    fun cekTagihan(
        @Query("key") key:String,
        @Query("brand") brand:String,
        @Query("search") id:String): Observable<List<DetailPpob>>

    @GET("ppob/api/token.php")
    fun searchToken(
        @Query("key") key:String,
        @Query("jenis") jenis:String,
        @Query("search") id:String): Observable<List<PulsaPpob>>

    @GET("ppob/api/produkppob.php")
    fun getProduct(
        @Query("key") key:String,
        @Query("jenis") jenis:String): Observable<List<PulsaPpob>>


    @Multipart
    @POST("ppob/api/order.php")
    fun order(
        @Part("key") key: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("sku") sku: RequestBody,
        @Part("hargajual") hargajual: RequestBody,
        @Part("pin") pin: RequestBody,
        @Part("product_name") product_name: RequestBody): Observable<Message>

    @Multipart
    @POST("ppob/api/bayartagihan.php")
    fun bayar(
        @Part("key") key: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("sku") sku: RequestBody,
        @Part("hargajual") hargajual: RequestBody,
        @Part("pin") pin: RequestBody,
        @Part("ref_id") ref_id: RequestBody,
        @Part("hargaagent") hargaagent: RequestBody): Observable<Message>

    @Multipart
    @POST("ppob/api/cektoken.php")
    fun cekToken(
        @Part("key") key: RequestBody,
        @Part("phone") phone: RequestBody): Observable<Message>

}