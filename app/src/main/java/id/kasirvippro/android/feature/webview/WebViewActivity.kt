package id.kasirvippro.android.feature.webview

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
import androidx.annotation.RequiresApi

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
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val webviewSettings = webview.settings
        webviewSettings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    CookieManager.getInstance().removeAllCookies(null)
                }
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
                    val err = error.description  ?: "There is an error"
                    toast(this@WebViewActivity, err.toString())
                }
                else{
                    toast(this@WebViewActivity, "There is an error")
                }
                onBackPressed()
            }

        }
        webview.webChromeClient = object : WebChromeClient(){}
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun loadUrl(url: String) {
        val webSettings: WebSettings = webview.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.setSupportZoom(true)
        webSettings.setAllowContentAccess(true)
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false)
        webSettings.setSupportMultipleWindows(false)
        webSettings.defaultTextEncodingName = "utf-8"
        //webview.setWebViewClient(WebViewClient())
        webview.setWebViewClient(object : WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                if (request.url.toString().contains("external=true")) {
                    val intent = Intent(Intent.ACTION_VIEW, request.url)
                    view.context.startActivity(intent)
                    return true
                } else {
                    val intent = Intent(Intent.ACTION_VIEW, request.url)
                    view.context.startActivity(intent)
                    return true
                }
                return true
            }
        })
        webview.loadUrl(url)
    }

    private fun setupToolbar() {
        val mTitle = intent.getStringExtra(AppConstant.Webview.TITLE)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            elevation = 99f
            title = mTitle
        }
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }
}
