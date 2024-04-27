package id.kasirvippro.android.models.discount

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface DiscountRestInterface {

    @GET("discount/list.php")
    fun gets(
        @Query("key") key: String): Observable<List<Discount>>

    @FormUrlEncoded
    @POST("discount/insert.php")
    fun add(
        @Field("key") key: String,
        @Field("name_discount") nama: String,
        @Field("note") keterangan: String,
        @Field("type") jenis: String,
        @Field("nominal") nominal: String): Observable<Message>

    @FormUrlEncoded
    @POST("discount/insertsales.php")
    fun addPenjualan(
        @Field("key") key: String,
        @Field("name_discount") nama: String,
        @Field("note") keterangan: String,
        @Field("type") jenis: String,
        @Field("nominal") nominal: String): Observable<List<Discount>>

    @FormUrlEncoded
    @POST("discount/update.php")
    fun update(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("name_discount") nama: String,
        @Field("note") keterangan: String,
        @Field("type") jenis: String,
        @Field("nominal") nominal: String): Observable<Message>

    @GET("discount/delete.php")
    fun delete(
        @Query("key") key: String,
        @Query("id") id: String): Observable<Message>

}