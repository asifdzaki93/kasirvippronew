package id.kasirvippro.android.feature.sell.paymentNonTunai

import android.content.Context
import android.content.Intent
import android.util.Log
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.AppConstant

class WebViewPresenter(val context: Context, val view: WebViewContract.View) : BasePresenter<WebViewContract.View>(),
    WebViewContract.Presenter, WebViewContract.InteractorOutput {

    private var invoice:String? = null
    private var url:String? = null

    override fun onViewCreated(intent: Intent) {
        url = intent.getStringExtra(AppConstant.Webview.URL)
        invoice = intent.getStringExtra(AppConstant.DATA)
        view.loadUrl(url!!)
    }

    override fun getInvoice(): String? {
        return invoice
    }

    override fun setUrl(data: String?) {
        url = data
        Log.d("url",url!!)
    }



}