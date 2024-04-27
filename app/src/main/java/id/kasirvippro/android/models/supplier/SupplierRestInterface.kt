package id.kasirvippro.android.models.supplier

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface SupplierRestInterface {

    @GET("supplier/list.php")
    fun gets(
        @Query("key") key:String): Observable<List<Supplier>>

    @FormUrlEncoded
    @POST("supplier/insert.php")
    fun add(
        @Field("key") key: String,
        @Field("name_supplier") name: String,
        @Field("email") email: String,
        @Field("telephone") telpon: String,
        @Field("address") alamat: String): Observable<Message>

    @FormUrlEncoded
    @POST("supplier/update.php")
    fun update(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("name_supplier") name: String,
        @Field("email") email: String,
        @Field("telephone") telpon: String,
        @Field("address") alamat: String): Observable<Message>

    @GET("supplier/delete.php")
    fun delete(
        @Query("key") key:String,
        @Query("id") id:String): Observable<Message>

    @GET("supplier/search.php")
    fun search(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Supplier>>

    @GET("supplier/detail.php")
    fun detail(
        @Query("key") key:String,
        @Query("id_supplier") id:String): Observable<Supplier>

}