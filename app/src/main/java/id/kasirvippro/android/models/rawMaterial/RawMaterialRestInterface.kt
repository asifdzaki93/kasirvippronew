package id.kasirvippro.android.models.rawMaterial

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface RawMaterialRestInterface {

    @GET("rawmaterial/list.php")
    fun gets(
        @Query("key") key:String): Observable<List<RawMaterial>>

    @GET("rawmaterial/liststock.php")
    fun getsStock(
        @Query("key") key:String): Observable<List<RawMaterial>>

    @GET("rawmaterial/list.php")
    fun choose(
        @Query("key") key:String,
        @Query("have_stock") check:String): Observable<List<RawMaterial>>

    @Multipart
    @POST("rawmaterial/add.php")
    fun add(
        @Part("key") key: RequestBody,
        @Part("name") nama: RequestBody,
        @Part("unit") unit: RequestBody,
        @Part("price") jual: RequestBody,
        @Part("stock") stok: RequestBody,
        @Part("description") deskripsi: RequestBody): Observable<Message>

    @Multipart
    @POST("rawmaterial/update.php")
    fun update(
        @Part("key") key: RequestBody,
        @Part("id_raw_material") id: RequestBody,
        @Part("name") nama: RequestBody,
        @Part("unit") unit: RequestBody,
        @Part("price") jual: RequestBody,
        @Part("stock") stok: RequestBody,
        @Part("description") deskripsi: RequestBody): Observable<Message>

    @Multipart
    @POST("rawmaterial/updatestock.php")
    fun updateStock(
        @Part("key") key: RequestBody,
        @Part("id_raw_material") id: RequestBody,
        @Part("stock") stok: RequestBody): Observable<Message>

    @GET("rawmaterial/delete.php")
    fun delete(
        @Query("key") key:String,
        @Query("id") id:String): Observable<Message>

    @GET("rawmaterial/search.php")
    fun search(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<RawMaterial>>


    @GET("rawmaterial/searchstock.php")
    fun searchStock(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<RawMaterial>>


    @GET("rawmaterial/search.php")
    fun chooseSearch(
        @Query("key") key:String,
        @Query("search") id:String,
        @Query("have_stock")check:String): Observable<List<RawMaterial>>



}