package id.kasirvippro.android.models.category

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface CategoryRestInterface {

    @GET("category/list.php")
    fun getCategories(
        @Query("key") key:String): Observable<List<Category>>

    @FormUrlEncoded
    @POST("category/insert.php")
    fun addCategory(
        @Field("key") key: String,
        @Field("name_category") kategori: String): Observable<Message>


    @FormUrlEncoded
    @POST("category/insertproduct.php")
    fun addCategoryProduct(
        @Field("key") key: String,
        @Field("name_category") nama: String): Observable<List<Category>>

    @FormUrlEncoded
    @POST("category/update.php")
    fun updateCategory(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("name_category") kategori: String): Observable<Message>

    @GET("category/delete.php")
    fun deleteCategory(
        @Query("key") key:String,
        @Query("id") id:String): Observable<Message>

    @GET("category/search.php")
    fun searchCategory(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Category>>

}