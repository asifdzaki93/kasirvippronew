package id.kasirvippro.android.feature.webview

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.AppConstant

class WebViewPresenter(val context: Context, val view: WebViewContract.View) : BasePresenter<WebViewContract.View>(),
    WebViewContract.Presenter, WebViewContract.InteractorOutput {

    override fun onViewCreated(intent: Intent) {
        val url = intent.getStringExtra(AppConstant.Webview.URL)
        //Log.d("getPremiumUrl",url!!)
        view.loadUrl(url!!)
    }

    override fun setActionWhenBtnIsClicked() {

    }
}