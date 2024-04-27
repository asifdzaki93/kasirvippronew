package id.kasirvippro.android.feature.sell.paymentNonTunai

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.webkit.*
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_web_view.*
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import id.kasirvippro.android.feature.transaction.detail.DetailActivity

class WebViewActivity : BaseActivity<WebViewPresenter, WebViewContract.View>(), WebViewContract.View {

    override fun createPresenter(): WebViewPresenter {
        return WebViewPresenter(this, this)
    }

    override fun createLayout(): Int = R.layout.activity_web_view

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    override fun renderView() {
        setupWebView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item!!)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val webviewSettings = webview.settings
        webviewSettings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }

//            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
//                // ignore ssl error
//                if (handler != null) {
//                    handler.proceed()
//                } else {
//                    super.onReceivedSslError(view, null, error)
//
//                }
//            }
            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                //Your code to do
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    toast(this@WebViewActivity, error.description as String)
                }
                else{
                    toast(this@WebViewActivity, "There is an error")
                }
                onBackPressed()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                getPresenter()?.setUrl(url)
            }

        }
        webview.webChromeClient = object : WebChromeClient(){}
    }

    override fun loadUrl(url: String) {
        webview.loadUrl(url)
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            elevation = 99f
            title = "Pembayaran Non Tunai"
        }
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun openSuccessPage() {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(AppConstant.CODE, AppConstant.Code.CODE_TRANSACTION_CUSTOMER)
        intent.putExtra(AppConstant.DATA,getPresenter()?.getInvoice())
        intent.putExtra(AppConstant.MAIN,true)
        startActivity(intent)
    }

    override fun onBackPressed() {
        restartMainActivity()
    }

    override fun onClose() {
        finish()
    }
}
