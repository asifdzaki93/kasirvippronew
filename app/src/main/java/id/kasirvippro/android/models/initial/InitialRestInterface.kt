package id.kasirvippro.android.models.initial

import id.kasirvippro.android.models.Message
import io.reactivex.Observable
import retrofit2.http.*

interface InitialRestInterface {

    @GET("initial/detail.php")
    fun getInitial(
        @Query("key") key: String): Observable<List<Initial>>

    @FormUrlEncoded
    @POST("initial/inputinitial.php")
    fun input(
        @Field("key") key: String,
        @Field("modal_awal") modal_awal: String,
        @Field("sift") sift: String): Observable<Message>
}