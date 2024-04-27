package id.kasirvippro.android.feature.report.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manage.role.list.RoleListActivity
import id.kasirvippro.android.feature.manageStock.stockHistoryRawMaterial.list.StockHistoryListActivity
import id.kasirvippro.android.feature.report.cashflow.main.CashflowActivity
import id.kasirvippro.android.feature.report.daily.DailyActivity
import id.kasirvippro.android.feature.report.transaction.TransactionActivity
import id.kasirvippro.android.feature.report.keuangan.KeuanganActivity
import id.kasirvippro.android.feature.report.mutasi.MutasiActivity
import id.kasirvippro.android.feature.report.slip.chooseStaff.ChooseStaffActivity
import id.kasirvippro.android.feature.report.penjualan.PenjualanActivity
import id.kasirvippro.android.feature.report.preOrder.main.PreOrderActivity
import id.kasirvippro.android.feature.report.produk.ProdukActivity
import id.kasirvippro.android.feature.report.rawMaterial.main.RawMaterialActivity
import id.kasirvippro.android.feature.webview.WebViewActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant

import kotlinx.android.synthetic.main.fragment_report.*


class ReportActivity : BaseActivity<ReportPresenter, ReportContract.View>(),
    ReportContract.View {

    override fun createPresenter(): ReportPresenter {
        return ReportPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.fragment_report
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }


    private fun renderView(){

        btn_transaksi.setOnClickListener {
            openTransaction()
        }

        btn_profit.setOnClickListener {
            openProfit()
        }

        btn_product.setOnClickListener {
            openProductSell()
        }

        btn_rawmaterial.setOnClickListener {
            openRawpage()
        }

        btn_reportrawmaterial.setOnClickListener {
            openReportRawpage()
        }

        btn_cashflow.setOnClickListener {
            openCashFlow()
        }

        btn_mutasi.setOnClickListener {
            openMutasi()
        }

        btn_preorder.setOnClickListener {
            openPreOrder()
        }

        btn_payslip.setOnClickListener {
            getPresenter()?.onCheckSalary()
        }

        btn_sell.setOnClickListener {
            openSell()
        }

        btn_absensi.setOnClickListener {
            getPresenter()?.onCheckAbsensi()
        }

        btn_daily.setOnClickListener {
            openDaily()
        }

        btn_grosir.setOnClickListener {
            toast(this, "Fitur segera hadir")
        }
    }

    override fun setRole(name_role: String?, report_product: String?, report_sumary: String?, report_daily: String?, report_accounting: String?) {
        name_role?.let {
            if(it == "master"){
                btn_transaksi.visibility = View.VISIBLE
                btn_sell.visibility = View.VISIBLE
                btn_daily.visibility = View.VISIBLE
                btn_profit.visibility = View.VISIBLE
                btn_payslip.visibility = View.VISIBLE
                btn_absensi.visibility = View.VISIBLE
                btn_product.visibility = View.VISIBLE
            }else if(it == "kasir"){
                if(report_product=="YES") {
                    btn_transaksi.visibility = View.VISIBLE
                }else{
                    btn_transaksi.visibility = View.GONE
                }
                if(report_sumary=="YES") {
                    btn_sell.visibility = View.VISIBLE
                }else{
                    btn_sell.visibility = View.GONE
                }
                if(report_daily=="YES") {
                    btn_daily.visibility = View.VISIBLE
                }else{
                    btn_daily.visibility = View.GONE
                }
                if(report_accounting=="YES") {
                    btn_profit.visibility = View.VISIBLE
                }else{
                    btn_profit.visibility = View.GONE
                }

                btn_product.visibility = View.GONE
                btn_payslip.visibility = View.GONE
                btn_absensi.visibility = View.GONE

            }else if(it == "admin"){
                if(report_product=="YES") {
                    btn_transaksi.visibility = View.VISIBLE
                }else{
                    btn_transaksi.visibility = View.GONE
                }
                if(report_sumary=="YES") {
                    btn_sell.visibility = View.VISIBLE
                }else{
                    btn_sell.visibility = View.GONE
                }
                if(report_daily=="YES") {
                    btn_daily.visibility = View.VISIBLE
                }else{
                    btn_daily.visibility = View.GONE
                }
                if(report_accounting=="YES") {
                    btn_profit.visibility = View.VISIBLE
                }else{
                    btn_profit.visibility = View.GONE
                }
                btn_product.visibility = View.GONE
                btn_payslip.visibility = View.GONE
                btn_absensi.visibility = View.GONE

            }else if(it == "other"){
                if(report_product=="YES") {
                    btn_transaksi.visibility = View.VISIBLE
                }else{
                    btn_transaksi.visibility = View.GONE
                }
                if(report_sumary=="YES") {
                    btn_sell.visibility = View.VISIBLE
                }else{
                    btn_sell.visibility = View.GONE
                }
                if(report_daily=="YES") {
                    btn_daily.visibility = View.VISIBLE
                }else{
                    btn_daily.visibility = View.GONE
                }
                if(report_accounting=="YES") {
                    btn_profit.visibility = View.VISIBLE
                }else{
                    btn_profit.visibility = View.GONE
                }
                btn_product.visibility = View.GONE
                btn_payslip.visibility = View.GONE
                btn_absensi.visibility = View.GONE

            }else if(it == "manager"){
                if(report_product=="YES") {
                    btn_transaksi.visibility = View.VISIBLE
                }else{
                    btn_transaksi.visibility = View.GONE
                }
                if(report_sumary=="YES") {
                    btn_sell.visibility = View.VISIBLE
                }else{
                    btn_sell.visibility = View.GONE
                }
                if(report_daily=="YES") {
                    btn_daily.visibility = View.VISIBLE
                }else{
                    btn_daily.visibility = View.GONE
                }
                if(report_accounting=="YES") {
                    btn_profit.visibility = View.VISIBLE
                }else{
                    btn_profit.visibility = View.GONE
                }
                btn_product.visibility = View.GONE
                btn_payslip.visibility = View.GONE
                btn_absensi.visibility = View.GONE

            }
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

    override fun openTransaction() {
        startActivity(Intent(this, TransactionActivity::class.java))
    }

    override fun openProfit() {
        startActivity(Intent(this, KeuanganActivity::class.java))
    }

    override fun openMutasi() {
        startActivity(Intent(this, MutasiActivity::class.java))
    }

    override fun openPreOrder() {
        startActivity(Intent(this, PreOrderActivity::class.java))
    }

    override fun openSalary() {
        val intent = Intent(this, ChooseStaffActivity::class.java)
        intent.putExtra(AppConstant.CODE,AppConstant.Code.CODE_KARYAWAN_GAJI)
        startActivity(intent)
    }

    override fun openSell() {
        startActivity(Intent(this, PenjualanActivity::class.java))
    }


    override fun openProductSell() {
        startActivity(Intent(this, ProdukActivity::class.java))
    }


    override fun openRawpage() {
        startActivity(Intent(this, StockHistoryListActivity::class.java))
    }


    override fun openReportRawpage() {
        startActivity(Intent(this, RawMaterialActivity::class.java))
    }


    override fun openCashFlow() {
        startActivity(Intent(this, CashflowActivity::class.java))
    }

    override fun openDaily() {
        startActivity(Intent(this, DailyActivity::class.java))
    }

    override fun openAbsensi() {
        val intent = Intent(this, ChooseStaffActivity::class.java)
        intent.putExtra(AppConstant.CODE,AppConstant.Code.CODE_KARYAWAN_ABSENSI)
        startActivity(intent)
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_report)
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

    override fun openWebviewPage(title: String, url: String) {
        val browserIntent = Intent(this, WebViewActivity::class.java)
        browserIntent.putExtra(AppConstant.Webview.TITLE,title)
        browserIntent.putExtra(AppConstant.Webview.URL,url)
        startActivity(browserIntent)
    }



    /*override fun onMasterPage(isPremium: Boolean) {
        btn_transaksi.visibility = View.VISIBLE
        btn_sell.visibility = View.VISIBLE
        btn_daily.visibility = View.VISIBLE
        btn_profit.visibility = View.VISIBLE
        btn_payslip.visibility = View.VISIBLE
        btn_absensi.visibility = View.VISIBLE
    }

    override fun onAdminPage() {
        btn_transaksi.visibility = View.VISIBLE
        btn_sell.visibility = View.VISIBLE
        btn_daily.visibility = View.VISIBLE
        btn_profit.visibility = View.GONE
        btn_payslip.visibility = View.GONE
        btn_absensi.visibility = View.GONE

    }

    override fun onSalesPage() {
        btn_transaksi.visibility = View.VISIBLE
        btn_sell.visibility = View.VISIBLE
        btn_daily.visibility = View.VISIBLE
        btn_profit.visibility = View.GONE
        btn_payslip.visibility = View.GONE
        btn_absensi.visibility = View.GONE
    }*/

    override fun openRolePage() {
        startActivity(Intent(this, RoleListActivity::class.java))
    }

    override fun loadRole() {
        getPresenter()?.loadRole()
    }

    override fun onPremiumPage(isPremium: Boolean) {
        if(isPremium){
            btn_lock_gaji.visibility = View.GONE
            btn_lock_absensi.visibility = View.GONE
        }
        else{
            btn_lock_gaji.visibility = View.VISIBLE
            btn_lock_absensi.visibility = View.VISIBLE
        }
    }


}
