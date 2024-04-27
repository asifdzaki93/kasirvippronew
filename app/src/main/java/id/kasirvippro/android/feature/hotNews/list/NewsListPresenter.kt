package id.kasirvippro.android.feature.hotNews.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.models.news.NewsRestModel

class NewsListPresenter(val context: Context, val view: NewsListContract.View) : BasePresenter<NewsListContract.View>(),
    NewsListContract.Presenter, NewsListContract.InteractorOutput {

    private var interactor: NewsListInteractor = NewsListInteractor(this)
    private var newsRestModel = NewsRestModel(context)

    override fun onViewCreated() {
                    loadNews()

    }

    override fun loadNews() {
        interactor.callGetNewsAPI(context,newsRestModel)
    }

    fun setNews(list: List<News>){
        view.setNews(list)
    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetNews(list: List<News>) {
        view.setData(list)
    }


    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}