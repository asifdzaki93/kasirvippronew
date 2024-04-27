package id.kasirvippro.android.feature.hotNews.list;

import android.content.Context
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.models.news.NewsRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppSession
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class NewsListInteractor(var output: NewsListContract.InteractorOutput?) : NewsListContract.Interactor {

    private var disposable = CompositeDisposable()
    private val appSession = AppSession()

    override fun onDestroy() {
        disposable.clear()
    }

    override fun onRestartDisposable() {
        disposable.dispose()
        disposable = CompositeDisposable()
    }

    override fun callGetNewsAPI(context: Context, restModel: NewsRestModel) {
        val key = appSession.getToken(context)
        disposable.add(restModel.getNews(key!!).subscribeWith(object : DisposableObserver<List<News>>() {

            override fun onNext(@NonNull response: List<News>) {
                output?.onSuccessGetNews(response)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                var errorCode = 999
                var errorMessage = "There is an error"
                if (e is RestException) {
                    errorCode = e.errorCode
                    errorMessage = e.message ?: "There is an error"
                }
                else{
                    errorMessage = e.message.toString()
                }
                output?.onFailedAPI(errorCode,errorMessage)
            }

            override fun onComplete() {

            }
        }))
    }

}