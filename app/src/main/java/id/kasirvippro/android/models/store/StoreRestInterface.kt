package id.kasirvippro.android.models.store

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface StoreRestInterface {

    @GET("settings/detailstore.php")
    fun getStore(
        @Query("key") key:String): Observable<List<Store>>



    @Multipart
    @POST("settings/updatestore.php")
    fun updateStore(
        @Part("key") key: RequestBody,
        @Part("id") id: RequestBody,
        @Part("name_store") nama: RequestBody,
        @Part("email") email: RequestBody,
        @Part("nohp") hp: RequestBody,
        @Part("address") alamat: RequestBody,
        @Part("tax") pajak: RequestBody,
        @Part("service_charge") service: RequestBody,
        @Part("currency") currency: RequestBody,
        @Part("footer") footer: RequestBody,
        @Part("shift") shift: RequestBody,
        @Part img: MultipartBody.Part?): Observable<Message>

    @GET("settings/liststore.php")
    fun gets(
        @Query("key") key:String): Observable<List<Store>>

    @GET("settings/deletestore.php")
    fun delete(
        @Query("key") key: String,
        @Query("id") id: String): Observable<Message>

    @FormUrlEncoded
    @POST("settings/addstore.php")
    fun add(
        @Field("key") key: String,
        @Field("name_store") nama: String,
        @Field("email") email: String,
        @Field("nohp") hp: String,
        @Field("tax") pajak: String,
        @Field("service_charge") service: String,
        @Field("currency") currency: String,
        @Field("footer") footer: String,
        @Field("address") alamat: String): Observable<Message>
}