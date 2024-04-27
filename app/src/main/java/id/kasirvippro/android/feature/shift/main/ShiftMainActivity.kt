package id.kasirvippro.android.feature.shift.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.shift.closingShift.list.ClosingShiftListActivity
import id.kasirvippro.android.feature.webview.WebViewActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant


import kotlinx.android.synthetic.main.fragment_shift.*


class ShiftMainActivity : BaseActivity<ShiftMainPresenter, ShiftMainContract.View>(),
    ShiftMainContract.View {

    override fun createPresenter(): ShiftMainPresenter {
        return ShiftMainPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_shift
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){

        btn_manage_sift.setOnClickListener {
            openInitialShiftPage()
        }
        btn_daily_close.setOnClickListener {
            openDailyClosePage()
        }
    }

    override fun loadProfile() {
        getPresenter()?.loadProfile()
    }


    override fun openInitialShiftPage() {
        startActivity(Intent(this, ClosingShiftListActivity::class.java))
    }

    override fun openDailyClosePage() {
    }



    override fun openWebviewPage(title:String,url: String) {
        val browserIntent = Intent(this, WebViewActivity::class.java)
        browserIntent.putExtra(AppConstant.Webview.TITLE,title)
        browserIntent.putExtra(AppConstant.Webview.URL,url)
        startActivity(browserIntent)
    }



    override fun onMasterPage(isPremium: Boolean) {
        btn_manage_sift.visibility = View.VISIBLE
        btn_daily_close.visibility = View.VISIBLE

    }

    override fun onAdminPage() {
        btn_manage_sift.visibility = View.VISIBLE
        btn_daily_close.visibility = View.VISIBLE
    }

    override fun onSalesPage() {
        btn_manage_sift.visibility = View.VISIBLE
        btn_daily_close.visibility = View.VISIBLE
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
            android.R.id.home -> restartMainActivity()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }
}

