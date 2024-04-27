package id.kasirvippro.android.feature.hotNews.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.models.news.NewsRestModel
import id.kasirvippro.android.utils.AppConstant

class ViewNewsPresenter(val context: Context, val view: ViewNewsContract.View) : BasePresenter<ViewNewsContract.View>(),
    ViewNewsContract.Presenter, ViewNewsContract.InteractorOutput {


    private var interactor: ViewNewsInteractor = ViewNewsInteractor(this)
    private var categoryRestModel = NewsRestModel(context)
    private var data:News? = null


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as News
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }
        data?.let {
            view.setNews(it.title,it.detail,it.img)
        }


    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

}