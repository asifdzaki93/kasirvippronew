package id.kasirvippro.android.models.recipe

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface RecipeRestInterface {

    @GET("recipe/list.php")
    fun gets(
        @Query("key") key: String,
        @Query("id_produk") id_produk: String): Observable<List<Recipe>>

    @FormUrlEncoded
    @POST("recipe/insert.php")
    fun add(
        @Field("key") key: String,
        @Field("stock") stock: String,
        @Field("id_raw_material") id_raw_material: String,
        @Field("id_product") id_product: String): Observable<Message>

    @FormUrlEncoded
    @POST("recipe/update.php")
    fun update(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("stock") stock: String): Observable<Message>

    @GET("recipe/delete.php")
    fun delete(
        @Query("key") key: String,
        @Query("id") id: String): Observable<Message>

}