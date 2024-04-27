package id.kasirvippro.android.models.closeShift

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface CloseShiftRestInterface {

    @GET("initial/detail.php")
    fun gets(
        @Query("key") key: String): Observable<List<CloseShift>>

    @FormUrlEncoded
    @POST("initial/update.php")
    fun update(
        @Field("key") key: String,
        @Field("id") id: String,
        @Field("cash_actual") cash_actual: String): Observable<Message>

    @GET("initial/tutupharian.php")
    fun dailyClosing(
        @Query("key") key:String,
        @Query("id") id:String): Observable<Message>
}