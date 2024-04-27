package id.kasirvippro.android.models.network

import io.reactivex.Observable
import retrofit2.http.*

interface NetworkRestInterface {

    @GET("network/list.php")
    fun getStaff(
        @Query("key") key:String): Observable<List<Network>>

    @GET("network/list.php")
    fun getSub(
        @Query("key") key:String): Observable<List<Network>>

    @GET("network/search.php")
    fun search(
        @Query("key") key:String,
        @Query("search") id:String): Observable<List<Network>>


}