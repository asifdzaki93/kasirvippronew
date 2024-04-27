package id.kasirvippro.android.feature.etalase.main

import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.etalase.kelolatoko.list.KelolatokoListActivity
import id.kasirvippro.android.feature.qrCode.QrCodeActivity
import id.kasirvippro.android.feature.webview.WebViewActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant


import kotlinx.android.synthetic.main.fragment_etalase.*


class EtalaseActivity : BaseActivity<EtalasePresenter, EtalaseContract.View>(),
    EtalaseContract.View {

    override fun createPresenter(): EtalasePresenter {
        return EtalasePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_etalase
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){


        btn_kelolatoko.setOnClickListener {
            openKelolatokoPage()
        }

        btn_qrcode.setOnClickListener {
            openQrCodePage()
        }


    }

    override fun loadProfile() {
        getPresenter()?.loadProfile()
    }


    override fun openKelolatokoPage() {
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = this.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
        startActivity(Intent(this, KelolatokoListActivity::class.java))
                }else{
                    Toast.makeText(this,"Your connection is not available", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Your connection is not available", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Your connection is not available", Toast.LENGTH_SHORT).show()
        }
    }



    override fun openQrCodePage() {
        startActivity(Intent(this, QrCodeActivity::class.java))
    }



    override fun openWebviewPage(title:String,url: String) {
        val browserIntent = Intent(this, WebViewActivity::class.java)
        browserIntent.putExtra(AppConstant.Webview.TITLE,title)
        browserIntent.putExtra(AppConstant.Webview.URL,url)
        startActivity(browserIntent)
    }



    override fun onMasterPage(isPremium: Boolean) {
        btn_kelolatoko.visibility = View.VISIBLE

    }

    override fun onAdminPage() {
        btn_kelolatoko.visibility = View.GONE
    }

    override fun onSalesPage() {
        btn_kelolatoko.visibility = View.GONE
    }

    override fun showErrorMessage(code: Int, msg: String) {
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> toast(this,msg)
        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Online Store"
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }
}

