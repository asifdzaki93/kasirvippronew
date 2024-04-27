package id.kasirvippro.android.models.productPpob

import io.reactivex.Observable
import retrofit2.http.*

interface ProductPpobRestInterface {

    @GET("ppob/api/produkppob.php")
    fun getProduct(
        @Query("key") key:String,
        @Query("jenis") jenis:String): Observable<List<ProductPpob>>



}