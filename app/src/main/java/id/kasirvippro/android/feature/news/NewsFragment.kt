package id.kasirvippro.android.feature.news

import id.kasirvippro.android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.base.BaseFragment
import id.kasirvippro.android.feature.hotNews.view.ViewNewsActivity
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_list_store.*
import kotlinx.android.synthetic.main.activity_news.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class NewsFragment : BaseFragment<NewsPresenter, NewsContract.View>(), NewsContract.View {

    private lateinit var _view: View
    val adapter = NewsAdapter()
    var list2 = arrayListOf<News>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    companion object {


        @JvmStatic
        fun newInstance() =
            NewsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun createPresenter(): NewsPresenter {
        return NewsPresenter(activity as Context, this)
    }

    override fun onCreateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.activity_hot_news, container, false)
    }


    override fun initAction(view: View) {
        _view = view
        renderView()
        adapter.clearAdapter()
        getPresenter()?.loadNews()
    }

    override fun renderView() {

        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        _view.rv_list.layoutManager = layoutManager
        _view.rv_list.adapter = adapter

        adapter.callback = object : NewsAdapter.ItemClickCallback {
            override fun onClick(data: News) {
                openViewNewsPage(data)
            }
        }

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onFirstItemVisible(isFirstItem: Boolean) {
                sw_refresh.isEnabled = isFirstItem && adapter.itemCount > 0

            }

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {

            }
        }

        _view.sw_refresh.setOnRefreshListener {
            scrollListener.resetState()
            reloadData()
        }


    }

    override fun reloadData() {
        _view.sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        getPresenter()?.loadNews()
    }

    override fun showErrorMessage(code: Int, msg: String) {
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
        }

    }


    override fun setData(list: List<News>) {
        hideLoadingDialog()
        _view.sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    override fun openViewNewsPage(data: News) {
        val newintent = Intent(activity, ViewNewsActivity::class.java)
        newintent.putExtra(AppConstant.DATA,data)
        startActivity(newintent)
    }


    override fun showContentView() {
        _view.webview.visibility = View.VISIBLE
       // _view.ll_error.visibility = View.GONE
    }

    override fun showErrorView(err: String) {
        _view.webview.visibility = View.GONE
      //  _view.ll_error.visibility = View.VISIBLE
      //  _view.tv_error.text = err
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }




}
