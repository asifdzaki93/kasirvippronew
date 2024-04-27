package id.kasirvippro.android.models.addOn

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface AddOnRestInterface {

    @GET("addon/list.php")
    fun gets(
        @Query("key") key: String,
        @Query("id_produk") id_produk: String): Observable<List<AddOn>>

    @FormUrlEncoded
    @POST("addon/insert.php")
    fun add(
        @Field("key") key: String,
        @Field("name_addon") nama: String,
        @Field("nominal") nominal: String): Observable<Message>

    @FormUrlEncoded
    @POST("addon/update.php")
    fun update(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("name_addon") nama: String,
        @Field("nominal") nominal: String): Observable<Message>

    @GET("addon/delete.php")
    fun delete(
        @Query("key") key: String,
        @Query("id") id: String): Observable<Message>

}