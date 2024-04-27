package id.kasirvippro.android.models.product

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ProductRestInterface {

    @GET("product/list.php")
    fun gets(
        @Query("key") key:String,
        @Query("page") page:Int?): Observable<List<Product>>

    @GET("product/liststock.php")
    fun getsStock(
        @Query("key") key:String): Observable<List<Product>>

    @GET("product/listvariable.php")
    fun getsVariant(
        @Query("key") key:String,
        @Query("id_product") id_product:String): Observable<List<Product>>

    @GET("product/list.php")
    fun choose(
        @Query("key") key:String,
        @Query("trx") trx:String,
        @Query("have_stock") check:String,
        @Query("page") page:Int?): Observable<List<Product>>

    @GET("product/listvariable.php")
    fun chooseVariable(
        @Query("key") key:String,
        @Query("id_product") id_product:String,
        @Query("have_stock") check:String): Observable<List<Product>>

    @Multipart
    @POST("product/insert.php")
    fun add(
        @Part("key") key: RequestBody,
        @Part("name_product") nama: RequestBody,
        @Part("unit") unit: RequestBody,
        @Part("codeproduct") kode: RequestBody,
        @Part("id_category") idkategori: RequestBody,
        @Part("purchase_price") beli: RequestBody,
        @Part("selling_price") jual: RequestBody,
        @Part("stock") stok: RequestBody,
        @Part("minimalstock") minstok: RequestBody,
        @Part("description") deskripsi: RequestBody,
        @Part("online") online: RequestBody,
        @Part("have_stock") havestok: RequestBody,
        @Part("wholesale_price") grosir: RequestBody,
        @Part("tax") tax: RequestBody,
        @Part("alertstock") alertstock: RequestBody,
        @Part img:MultipartBody.Part?): Observable<Message>

    @Multipart
    @POST("product/insertvariant.php")
    fun addVariant(
        @Part("key") key: RequestBody,
        @Part("name_product") nama: RequestBody,
        @Part("codeproduct") kode: RequestBody,
        @Part("id_master") id_product: RequestBody,
        @Part("purchase_price") beli: RequestBody,
        @Part("selling_price") jual: RequestBody,
        @Part("stock") stok: RequestBody,
        @Part("minimalstock") minstok: RequestBody,
        @Part("description") deskripsi: RequestBody,
        @Part("online") online: RequestBody,
        @Part("have_stock") havestok: RequestBody,
        @Part("wholesale_price") grosir: RequestBody,
        @Part("tax") tax: RequestBody,
        @Part("alertstock") alertstock: RequestBody,
        @Part img:MultipartBody.Part?): Observable<Message>

    @Multipart
    @POST("product/update.php")
    fun update(
        @Part("key") key: RequestBody,
        @Part("id_product") id: RequestBody,
        @Part("name_product") nama: RequestBody,
        @Part("unit") unit: RequestBody,
        @Part("codeproduct") kode: RequestBody,
        @Part("id_category") idkategori: RequestBody,
        @Part("purchase_price") beli: RequestBody,
        @Part("selling_price") jual: RequestBody,
        @Part("stock") stok: RequestBody,
        @Part("minimalstock") minstok: RequestBody,
        @Part("description") deskripsi: RequestBody,
        @Part("online") online: RequestBody,
        @Part("have_stock") havestok: RequestBody,
        @Part("wholesale_price") grosir: RequestBody,
        @Part("tax") tax: RequestBody,
        @Part("alertstock") alertstock: RequestBody,
        @Part img:MultipartBody.Part?): Observable<Message>

    @Multipart
    @POST("product/updatestock.php")
    fun updateStock(
        @Part("key") key: RequestBody,
        @Part("id_product") id: RequestBody,
        @Part("stock") stok: RequestBody,
        @Part("reason") reason: RequestBody): Observable<Message>

    @GET("product/delete.php")
    fun delete(
        @Query("key") key:String,
        @Query("id") id:String): Observable<Message>

    @GET("product/search.php")
    fun search(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Product>>


    @GET("product/searchstock.php")
    fun searchStock(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Product>>

    @GET("product/searchvariant.php")
    fun searchVariant(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Product>>

    @GET("product/search.php")
    fun chooseSearch(
        @Query("key") key:String,
        @Query("search") id:String,
        @Query("have_stock")check:String): Observable<List<Product>>

    @GET("product/searchbarcode.php")
    fun searchByBarcode(
        @Query("key") key:String,
        @Query("codeproduct") id:String): Observable<List<Product>>

    @GET("product/sort.php")
    fun sort(
        @Query("key") key:String): Observable<List<Product>>



}