package id.kasirvippro.android.models.slip

import io.reactivex.Observable
import retrofit2.http.*

interface SlipRestInterface {

    @GET("report/salary_slip.php")
    fun getPaySlip(
        @Query("key") key:String,
        @Query("start_date") awal:String,
        @Query("finish_date") akhir:String): Observable<List<Slip>>

    @GET("attendance/detail.php")
    fun getAbsent(
        @Query("key") key:String,
        @Query("start_date") awal:String,
        @Query("finish_date") akhir:String): Observable<List<Absent>>
}