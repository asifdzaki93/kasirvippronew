package id.kasirvippro.android.models.table

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface TableRestInterface {

    @GET("table/list.php")
    fun getTable(
        @Query("key") key:String): Observable<List<Table>>

    @GET("table/listorder.php")
    fun getTableOrder(
        @Query("key") key:String): Observable<List<Table>>

    @GET("table/alltable.php")
    fun getAllTable(
        @Query("key") key:String): Observable<List<Table>>

    @FormUrlEncoded
    @POST("table/insert.php")
    fun add(
        @Field("key") key: String,
        @Field("name_table") table: String): Observable<Message>

    @FormUrlEncoded
    @POST("table/update.php")
    fun update(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("name_table") table: String): Observable<Message>

    @FormUrlEncoded
    @POST("table/movetable.php")
    fun moveTable(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("name_table") table: String,
        @Field("id_table") id_table: String): Observable<Message>

    @FormUrlEncoded
    @POST("table/jointable.php")
    fun joinTable(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("name_table") table: String,
        @Field("id_table") id_table: String): Observable<Message>

    @GET("table/delete.php")
    fun delete(
        @Query("key") key:String,
        @Query("id") id:String): Observable<Message>

}