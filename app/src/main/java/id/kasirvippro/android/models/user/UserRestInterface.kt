package id.kasirvippro.android.models.user

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface UserRestInterface {

    @GET("profile/dataaccount.php")
    fun getProfile(
        @Query("key") key:String): Observable<List<User>>

    @Multipart
    @POST("settings/updateaccount.php")
    fun updateProfile(
        @Part("key") key: RequestBody,
        @Part("full_name") nama: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone_number") telpon: RequestBody,
        @Part("address") alamat: RequestBody,
        @Part img: MultipartBody.Part?): Observable<Message>

    @FormUrlEncoded
    @POST("settings/updatepassword.php")
    fun changePassword(
        @Field("key") key: String,
        @Field("old_password") lama: String,
        @Field("new_password") baru: String): Observable<Message>

    @FormUrlEncoded
    @POST("profile/login.php")
    fun login(
        @Field("user") phone: String,
        @Field("password") pwd: String,
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double): Observable<List<Login>>

    @FormUrlEncoded
    @POST("profile/signup.php")
    fun register(
        @Field("name_store") toko: String,
        @Field("currency") currency: String,
        @Field("full_name") nama: String,
        @Field("email") email: String,
        @Field("phone_number") telpon: String,
        @Field("password") password: String,
        @Field("address") alamat: String,
        @Field("referal") referal: String,
        @Field("typestore") typestore: String,
        @Field("decimal") decimal: String): Observable<Message>


    @FormUrlEncoded
    @POST("profile/forgetpassword.php")
    fun forgotPassword(
        @Field("email") email: String,
        @Field("user") user: String): Observable<Message>



}