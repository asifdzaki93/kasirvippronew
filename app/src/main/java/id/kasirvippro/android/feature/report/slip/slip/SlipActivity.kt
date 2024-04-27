package id.kasirvippro.android.feature.report.slip.slip

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_slip.*


class SlipActivity : BaseActivity<SlipPresenter, SlipContract.View>(),
    SlipContract.View {

    override fun createPresenter(): SlipPresenter {
        return SlipPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_slip
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = ""
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_share_download, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
            R.id.action_share -> getPresenter()?.onCheckShare()
            R.id.action_download -> getPresenter()?.onCheckDownload()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun setStore(name: String?, address: String?, phone: String?) {
        tv_store.visibility = View.GONE
        name?.let {
            tv_store.visibility = View.VISIBLE
            tv_store.text = it
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setInfo(name: String, job: String, date: String) {
        tv_name.text = name
        if (job ==  "kasir"){
           val jobs = "Cashier"
            tv_job.text = "Position : ${jobs.toUpperCase()}"
        }else if (job ==  "other"){
           val jobs = "Accounting"
            tv_job.text = "Position : ${jobs.toUpperCase()}"
        }else{
           val jobs = job
            tv_job.text = "Position : ${jobs.toUpperCase()}"
        }

        tv_date.text = date
    }

    override fun setDetail(penjualan:String,gaji: String, komisi: String?, tunjangan: String?, potongan: String?,kehadiran:String?) {
        tv_penjualan.text = penjualan
        tv_gaji.text = gaji

        ll_komisi.visibility = View.GONE
        komisi?.let {
            ll_komisi.visibility = View.VISIBLE
            tv_komisi.text = it
        }

        ll_tunjangan.visibility = View.GONE
        tunjangan?.let {
            ll_tunjangan.visibility = View.VISIBLE
            tv_tunjangan.text = it
        }

        ll_potongan.visibility = View.GONE
        potongan?.let {
            ll_potongan.visibility = View.VISIBLE
            tv_potongan.text = it
        }

        tv_kehadiran.text = "0"
        kehadiran?.let {
            tv_kehadiran.text = it
        }
    }

    override fun setTotal(pendapatan: String, pengurang: String, total: String) {
        tv_total_pendapatan.text = pendapatan
        tv_total_pengurang.text = pengurang
        tv_take_home_pay.text = total
    }

    override fun getParentLayout(): NestedScrollView {
        return ns_parent
    }

    override fun showMessage(code: Int, msg: String?) {
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> toast(this,msg!!)
        }

    }

    override fun onPremiumPage(isPremium: Boolean) {
        if(isPremium){
            tv_powered_by.visibility = View.GONE
        }
        else{
            tv_powered_by.visibility = View.VISIBLE
        }
    }

}
