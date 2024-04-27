package id.kasirvippro.android.models.divisi

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface DivisiRestInterface {

    @GET("divisi/list.php")
    fun getDivisi(
        @Query("key") key:String): Observable<List<Divisi>>

    @FormUrlEncoded
    @POST("divisi/insert.php")
    fun addDivisi(
        @Field("key") key: String,
        @Field("name_divisi") name_divisi: String): Observable<Message>

    @FormUrlEncoded
    @POST("divisi/update.php")
    fun updateDivisi(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("name_divisi") name_divisi: String): Observable<Message>

    @GET("divisi/delete.php")
    fun deleteDivisi(
        @Query("key") key:String,
        @Query("id") id:String): Observable<Message>
}