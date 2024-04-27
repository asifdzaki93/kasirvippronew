package id.kasirvippro.android.feature.hotNews.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.models.news.NewsRestModel


interface NewsListContract {

    interface View : BaseViewImpl {
        fun setNews(list:List<News>)
        fun setData(list:List<News>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openViewNewsPage(data:News)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadNews()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetNewsAPI(context: Context, restModel:NewsRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetNews(list:List<News>)
        fun onFailedAPI(code:Int,msg:String)
    }


}