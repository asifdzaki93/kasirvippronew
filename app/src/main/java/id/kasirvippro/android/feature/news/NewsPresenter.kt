package id.kasirvippro.android.feature.news

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.models.news.NewsRestModel

class NewsPresenter(val context: Context, val view: NewsContract.View) : BasePresenter<NewsContract.View>(),
    NewsContract.Presenter, NewsContract.InteractorOutput {

    private var interactor: NewsInteractor = NewsInteractor(this)
    private var newsrestModel = NewsRestModel(context)

    override fun onViewCreated() {
        loadNews()
    }
    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadNews() {
        interactor.callGetNewsAPI(context,newsrestModel)
    }


    override fun onSuccessGetNews(list: List<News>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}