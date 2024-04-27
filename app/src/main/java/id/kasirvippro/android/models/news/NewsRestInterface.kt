package id.kasirvippro.android.models.news

import io.reactivex.Observable
import retrofit2.http.*

interface NewsRestInterface {

    @GET("news/list.php")
    fun getNews(
        @Query("key") key:String): Observable<List<News>>



}