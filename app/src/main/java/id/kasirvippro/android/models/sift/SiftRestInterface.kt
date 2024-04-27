package id.kasirvippro.android.models.sift

import io.reactivex.Observable
import retrofit2.http.*

interface SiftRestInterface {

    @GET("initial/sift.php")
    fun getSift(
        @Query("key") key:String): Observable<List<Sift>>
}