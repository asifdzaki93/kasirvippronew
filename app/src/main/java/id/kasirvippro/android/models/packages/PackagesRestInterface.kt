package id.kasirvippro.android.models.packages

import id.kasirvippro.android.models.Message
import id.kasirvippro.android.models.product.Product
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface PackagesRestInterface {

    @GET("packages/list.php")
    fun getCategories(
        @Query("key") key:String): Observable<List<Packages>>

    @GET("packages/listproduct.php")
    fun getProduct(
        @Query("key") key:String,
        @Query("sesi") sesi:String): Observable<List<Product>>

    @GET("packages/delete.php")
    fun deleteCategory(
        @Query("key") key:String,
        @Query("id") id:String): Observable<Message>

    @GET("packages/deleteitem.php")
    fun deleteProduct(
        @Query("key") key:String,
        @Query("id") id:String,
        @Query("sesi") sesi:String): Observable<Message>

    @GET("packages/search.php")
    fun searchCategory(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Packages>>

    @Multipart
    @POST("packages/update.php")
    fun updatePackages(
        @Part("key") key: RequestBody,
        @Part("id") id: RequestBody,
        @Part("name_product") name: RequestBody,
        @Part("price") price: RequestBody,
        @Part img: MultipartBody.Part?): Observable<Message>

}