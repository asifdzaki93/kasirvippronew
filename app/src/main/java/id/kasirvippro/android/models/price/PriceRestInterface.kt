package id.kasirvippro.android.models.price

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface PriceRestInterface {

    @GET("price/list.php")
    fun gets(
        @Query("key") key:String,
        @Query("nominal") nominal:Double): Observable<List<Price>>

}