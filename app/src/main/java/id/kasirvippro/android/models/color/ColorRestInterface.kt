package id.kasirvippro.android.models.color

import io.reactivex.Observable
import retrofit2.http.*

interface ColorRestInterface {

    @GET("settings/detailcolor.php")
    fun getColor(
        @Query("key") key:String): Observable<List<Color>>
}