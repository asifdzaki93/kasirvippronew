package id.kasirvippro.android.feature.choose.addProduct

import id.kasirvippro.android.models.newProduct.Category
import id.kasirvippro.android.models.newProduct.ProductNew
import id.kasirvippro.android.models.newProduct.SubCategory

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("category/list.php")
    fun getCategory(@Query("key") key: String?): Call<Category>

    @GET("product/searchcategory.php")
    fun getProductByCategory(
        @Query("key") key: String?,
        @Query("id_category") id_category: String? = ""
    ): Call<ProductNew>

    @GET("product/search.php")
    fun chooseSearch(
        @Query("key") key: String,
        @Query("search") search: String
    ): Call<ProductNew>

    @GET("category/list.php")
    fun getSubCategory(
        @Query("key") key: String,
        @Query("id_category") id_category: String
    ): Call<SubCategory>

    @GET("product/searchcategory.php")
    fun getProductBySubCategory(
        @Query("key") key: String,
        @Query("id_category") id_category: String
    ): Call<ProductNew>

    @GET("category/nocategory.php")
    fun getNoCategory(@Query("key") key: String?): Call<Category>
}