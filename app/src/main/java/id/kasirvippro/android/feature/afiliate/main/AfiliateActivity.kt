package id.kasirvippro.android.feature.afiliate.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.afiliate.detailAfiliate.DetailAfiliateActivity
import id.kasirvippro.android.feature.afiliate.list.NetworkListActivity
import id.kasirvippro.android.feature.afiliate.mains.ManageAfiliateActivity
import id.kasirvippro.android.feature.webview.WebViewActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant


import kotlinx.android.synthetic.main.fragment_afiliate.*


class AfiliateActivity : BaseActivity<AfiliatePresenter, AfiliateContract.View>(),
    AfiliateContract.View {

    override fun createPresenter(): AfiliatePresenter {
        return AfiliatePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_afiliate
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){


        btn_afiliasi.setOnClickListener {
            openAfiliatePage()
        }

        btn_network.setOnClickListener {
            openNetworkPage()
        }
        btn_commition.setOnClickListener {
            openCommisionPage()
        }


    }

    override fun loadProfile() {
        getPresenter()?.loadProfile()
    }


    override fun openAfiliatePage() {
        startActivity(Intent(this, DetailAfiliateActivity::class.java))
    }

    override fun openNetworkPage() {
        startActivity(Intent(this, NetworkListActivity::class.java))
    }

    override fun openCommisionPage() {
        startActivity(Intent(this, ManageAfiliateActivity::class.java))
    }



    override fun openWebviewPage(title:String,url: String) {
        val browserIntent = Intent(this, WebViewActivity::class.java)
        browserIntent.putExtra(AppConstant.Webview.TITLE,title)
        browserIntent.putExtra(AppConstant.Webview.URL,url)
        startActivity(browserIntent)
    }



    override fun onMasterPage(isPremium: Boolean) {
        btn_afiliasi.visibility = View.VISIBLE

    }

    override fun onAdminPage() {
        btn_afiliasi.visibility = View.VISIBLE
    }

    override fun onSalesPage() {
        btn_afiliasi.visibility = View.VISIBLE
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
            title = "Affiliate"
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

