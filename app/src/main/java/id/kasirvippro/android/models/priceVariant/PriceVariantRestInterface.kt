package id.kasirvippro.android.models.priceVariant

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface PriceVariantRestInterface {

    @GET("pricevariant/list.php")
    fun gets(
        @Query("key") key: String,
        @Query("id_produk") id_produk: String): Observable<List<PriceVariant>>

    @FormUrlEncoded
    @POST("pricevariant/insert.php")
    fun add(
        @Field("key") key: String,
        @Field("minimal") minimal: String,
        @Field("price") price: String,
        @Field("id_product") id_product: String): Observable<Message>

    @FormUrlEncoded
    @POST("pricevariant/update.php")
    fun update(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("minimal") minimal: String,
        @Field("nominal") nominal: String): Observable<Message>

    @GET("pricevariant/delete.php")
    fun delete(
        @Query("key") key: String,
        @Query("id") id: String): Observable<Message>

}