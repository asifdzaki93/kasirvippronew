package id.kasirvippro.android.feature.pulsaPpob.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.pulsaPpob.dataPpob.DataPpobActivity
import id.kasirvippro.android.feature.pulsaPpob.productPpob.ProductPpobActivity
import id.kasirvippro.android.feature.pulsaPpob.paketData.PaketDataActivity
import id.kasirvippro.android.feature.pulsaPpob.pulsa.PulsaActivity
import id.kasirvippro.android.feature.pulsaPpob.tokenPln.TokenPlnActivity
import id.kasirvippro.android.feature.pulsaPpob.smsTelpon.SmsTelponActivity
import id.kasirvippro.android.feature.webview.WebViewActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_list_store.*
import kotlinx.android.synthetic.main.activity_qrcode.*

import kotlinx.android.synthetic.main.fragment_pulsappob.*


class PulsaPpobActivity : BaseActivity<PulsaPpobPresenter, PulsaPpobContract.View>(),
    PulsaPpobContract.View {
    private val CODE_OPEN_EDIT = 102
    override fun createPresenter(): PulsaPpobPresenter {
        return PulsaPpobPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_pulsappob
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }


    private fun renderView(){

        btn_pulsa.setOnClickListener {
            openPulsa()
        }
        btn_smstelpon.setOnClickListener {
            openSmsTelpon()
        }

        btn_paketdata.setOnClickListener {
            openPaketData()
        }

        btn_plntoken.setOnClickListener {
            openTokenPln()
        }
        btn_emoney.setOnClickListener {
            openEmoney()
        }

        btn_voucher.setOnClickListener {
            openVoucher()
        }

        btn_game.setOnClickListener {
            openGame()
        }
        btn_multipayment.setOnClickListener {
            openMultifinance()
        }
        btn_telpon.setOnClickListener {
            openPascabayar()
        }

        btn_internet.setOnClickListener {
            openInternet()
        }
        btn_pln.setOnClickListener {
            openPln()
        }
        btn_bpjs.setOnClickListener {
            openBpjs()
        }

        btn_topup.setOnClickListener {
           // openWebviewPage("Topup",AppConstant.URL.TOPUP)
            openWebviewPage("TOPUP",getPresenter()?.getPremiumUrl()!!)
        }

    }

    override fun openPulsa() {
        startActivity(Intent(this, PulsaActivity::class.java))
    }

    override fun openSmsTelpon() {
        startActivity(Intent(this, SmsTelponActivity::class.java))
    }
    override fun openPaketData() {
        startActivity(Intent(this, PaketDataActivity::class.java))
    }

    override fun openTokenPln() {
        startActivity(Intent(this, TokenPlnActivity::class.java))
    }

    override fun openEmoney() {
        val jenis = "E-Money"
        val intent = Intent(this, ProductPpobActivity::class.java)
        intent.putExtra(AppConstant.DATA,jenis)
        intent.putExtra("jenis", jenis);
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }
    override fun openVoucher() {
        val jenis = "Voucher"
        val intent = Intent(this, ProductPpobActivity::class.java)
        intent.putExtra(AppConstant.DATA,jenis)
        intent.putExtra("jenis", jenis);
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }
    override fun openGame() {
        val jenis = "Games"
        val intent = Intent(this, ProductPpobActivity::class.java)
        intent.putExtra(AppConstant.DATA,jenis)
        intent.putExtra("jenis", jenis);
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun openMultifinance() {
        val jenis = "Multifinance"
        val intent = Intent(this, DataPpobActivity::class.java)
        intent.putExtra(AppConstant.DATA,jenis)
        intent.putExtra("jenis", jenis);
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }
    override fun openPln() {
        val jenis = "PLN"
        val intent = Intent(this, DataPpobActivity::class.java)
        intent.putExtra(AppConstant.DATA,jenis)
        intent.putExtra("jenis", jenis);
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun openBpjs() {
        val jenis = "BPJS"
        val intent = Intent(this, DataPpobActivity::class.java)
        intent.putExtra(AppConstant.DATA,jenis)
        intent.putExtra("jenis", jenis);
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }


    override fun openInternet() {
        val jenis = "Internet"
        val intent = Intent(this, DataPpobActivity::class.java)
        intent.putExtra(AppConstant.DATA,jenis)
        intent.putExtra("jenis", jenis);
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }


    override fun openPascabayar() {
        val jenis = "Pascabayar"
        val intent = Intent(this, DataPpobActivity::class.java)
        intent.putExtra(AppConstant.DATA,jenis)
        intent.putExtra("jenis", jenis);
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.tab_pulsappob)
            elevation = 0f
            setBackgroundDrawable(ContextCompat.getDrawable(this@PulsaPpobActivity,R.drawable.gradient_blue))
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    @SuppressLint("SetTextI18n")
    override fun setProfile(saldo: String?) {

        tv_saldo.text = ""
        saldo?.let {
            tv_saldo.text = "\uD83D\uDCB0 Saldo Rp " + it
        }
    }

    override fun showMessage(code: Int, msg: String?) {
        hideLoadingDialog()
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                msg?.let {
                    toast(this,it)}
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun openWebviewPage(title: String, url: String) {
        val browserIntent = Intent(this, WebViewActivity::class.java)
        browserIntent.putExtra(AppConstant.Webview.TITLE,title)
        browserIntent.putExtra(AppConstant.Webview.URL,url)
        startActivity(browserIntent)
    }

    override fun onPremiumPage(isPremium: Boolean) {

    }

    override fun loadProfile() {
        getPresenter()?.loadProfile()
    }



}
