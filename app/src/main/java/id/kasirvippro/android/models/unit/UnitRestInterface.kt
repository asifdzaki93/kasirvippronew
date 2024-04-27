package id.kasirvippro.android.models.unit

import io.reactivex.Observable
import retrofit2.http.*

interface UnitRestInterface {

    @GET("unit/list.php")
    fun getUnit(
        @Query("key") key:String): Observable<List<Unit>>
}