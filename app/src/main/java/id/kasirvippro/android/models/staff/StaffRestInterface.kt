package id.kasirvippro.android.models.staff

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface StaffRestInterface {

    @GET("settings/datastaff.php")
    fun getStaff(
        @Query("key") key:String): Observable<List<Staff>>

    @GET("settings/datastaff.php")
    fun getKurir(
        @Query("key") key:String): Observable<List<Staff>>

    @GET("settings/deletestaff.php")
    fun delete(
        @Query("key") key:String,
        @Query("phone_number") id:String): Observable<Message>

    @GET("settings/searchstaff.php")
    fun search(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Staff>>

    @Multipart
    @POST("settings/addstaff.php")
    fun add(
        @Part("key") key: RequestBody,
        @Part("full_name") nama: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone_number") telpon: RequestBody,
        @Part("address") alamat: RequestBody,
        @Part("salary_fixed") gaji: RequestBody,
        @Part("commission") komisi: RequestBody,
        @Part("allowance") tunjangan: RequestBody,
        @Part("piece") potongan: RequestBody,
        @Part("id_store") toko: RequestBody,
        @Part("level") level: RequestBody,
        @Part img:MultipartBody.Part?): Observable<Message>

    @Multipart
    @POST("settings/updatestaff.php")
    fun update(
        @Part("key") key: RequestBody,
        @Part("id") id: RequestBody,
        @Part("full_name") nama: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone_number") telpon: RequestBody,
        @Part("address") alamat: RequestBody,
        @Part("salary_fixed") gaji: RequestBody,
        @Part("commission") komisi: RequestBody,
        @Part("allowance") tunjangan: RequestBody,
        @Part("piece") potongan: RequestBody,
        @Part("id_store") toko: RequestBody,
        @Part("level") level: RequestBody,
        @Part img:MultipartBody.Part?): Observable<Message>


}