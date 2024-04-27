package id.kasirvippro.android.models.news

import android.content.Context
import id.kasirvippro.android.rest.RestClient
import id.kasirvippro.android.rest.RestModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRestModel(context: Context) : RestModel<NewsRestInterface>(context) {

    override fun createRestInterface(): NewsRestInterface {
        return RestClient.getInstance()!!.createInterface(NewsRestInterface::class.java)
    }

    fun getNews(key:String): Observable<List<News>> {
        return restInterface.getNews(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}