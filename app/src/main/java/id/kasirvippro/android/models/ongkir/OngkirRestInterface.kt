package id.kasirvippro.android.models.ongkir

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface OngkirRestInterface {

    @GET("ongkir/list.php")
    fun getOngkir(
        @Query("key") key:String): Observable<List<Ongkir>>

    @FormUrlEncoded
    @POST("ongkir/insert.php")
    fun addOngkir(
        @Field("key") key: String,
        @Field("name_ongkir") name_ongkir: String,
        @Field("nominal") nominal: String): Observable<Message>


    @FormUrlEncoded
    @POST("ongkir/insertproduct.php")
    fun addOngkirProduct(
        @Field("key") key: String,
        @Field("name_ongkir") name_ongkir: String,
        @Field("nominal") nominal: String): Observable<List<Ongkir>>

    @FormUrlEncoded
    @POST("ongkir/update.php")
    fun updateOngkir(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("name_ongkir") name_ongkir: String,
        @Field("nominal") nominal: String): Observable<Message>

    @GET("ongkir/delete.php")
    fun deleteOngkir(
        @Query("key") key:String,
        @Query("id") id:String): Observable<Message>
}