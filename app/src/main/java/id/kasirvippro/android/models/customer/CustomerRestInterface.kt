package id.kasirvippro.android.models.customer

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface CustomerRestInterface {

    @GET("customer/list.php")
    fun gets(
        @Query("key") key:String): Observable<List<Customer>>

    @GET("customer/medical.php")
    fun getMedical(
        @Query("key") key:String,
        @Query("id_customer") id_customer:String): Observable<List<MedicalRecord>>

    @Multipart
    @POST("customer/insert.php")
    fun add(
        @Part("key") key: RequestBody,
        @Part("name_customer") nama: RequestBody,
        @Part("email") email: RequestBody,
        @Part("telephone") telpon: RequestBody,
        @Part("address") alamat: RequestBody,
        @Part("customercode") customercode: RequestBody): Observable<Message>

    @Multipart
    @POST("customer/update.php")
    fun update(
        @Part("key") key: RequestBody,
        @Part("id") id: RequestBody,
        @Part("name_customer") nama: RequestBody,
        @Part("email") email: RequestBody,
        @Part("telephone") telephone: RequestBody,
        @Part("address") telpon: RequestBody,
        @Part("customercode") customercode: RequestBody): Observable<Message>

    @GET("customer/delete.php")
    fun delete(
        @Query("key") key:String,
        @Query("id") id:String): Observable<Message>

    @GET("customer/search.php")
    fun search(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Customer>>

    @GET("customer/detail.php")
    fun detail(
        @Query("key") key:String,
        @Query("id_customer") id:String): Observable<Customer>

    @FormUrlEncoded
    @POST("customer/insertsales.php")
    fun addPenjualan(
        @Field("key") key: String,
        @Field("name_customer") nama: String,
        @Field("email") email: String,
        @Field("telephone") telpon: String,
        @Field("address") alamat: String): Observable<List<Customer>>

}