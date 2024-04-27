package id.kasirvippro.android.feature.report.keuangan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.NestedScrollView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.RangeDateDialog
import id.kasirvippro.android.models.report.ReportLabaRugi
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_report_keuangan.*

class KeuanganActivity : BaseActivity<KeuanganPresenter, KeuanganContract.View>(),
    KeuanganContract.View, RangeDateDialog.Listener {

    override fun createPresenter(): KeuanganPresenter {
        return KeuanganPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_report_keuangan
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
        reloadData()
    }

    private fun renderView() {
        sw_refresh.setOnRefreshListener {
            reloadData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_date_share_dl, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
            R.id.action_date -> openFilterDateDialog()
            R.id.action_share -> getPresenter()?.onCheckShare()
            R.id.action_download -> getPresenter()?.onCheckDownload()
        }
        return super.onOptionsItemSelected(item!!)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            title = "Finance"
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


    @SuppressLint("SetTextI18n")
    override fun setData(data: ReportLabaRugi.Keuangan?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false

        tv_gross.text = "0"
        tv_gross_labarugi.text = "0"
        tv_diskon.text = "0"
        tv_diskon_labarugi.text = "0"
        tv_pembatalan.text = "0"
        tv_pembatalan_labarugi.text = "0"
        tv_nett.text = "0"
        tv_nett_labarugi.text = "0"
        tv_pajak.text = "0"
        tv_admin.text = "0"
        tv_harga_pokok.text = "0"
        tv_total.text = "0"
        tv_laba.text = "0"
        tv_expenses.text = "0"

        data?.let {keuangan ->
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                tv_gross.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.gross_sales!!)
                tv_gross_labarugi.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.gross_sales)
                tv_diskon.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.discount!!)
                tv_diskon_labarugi.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.discount)
                tv_pembatalan.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.cancellation!!)
                tv_pembatalan_labarugi.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.cancellation)
                tv_nett.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.net_sales!!)
                tv_nett_labarugi.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.net_sales)
                tv_pajak.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.tax!!)
                tv_admin.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.admin!!)
                tv_harga_pokok.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.harga_pokok_penjualan!!)
                tv_total.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.total_income!!)
                tv_laba.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.gross_profit!!)
                tv_expenses.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.expenses!!)
                tv_return.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.salesreturn!!)
            }else{
                tv_gross.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.gross_sales!!
                tv_gross_labarugi.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.gross_sales
                tv_diskon.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.discount!!
                tv_diskon_labarugi.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.discount
                tv_pembatalan.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.cancellation!!
                tv_pembatalan_labarugi.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.cancellation
                tv_nett.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.net_sales!!
                tv_nett_labarugi.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.net_sales
                tv_pajak.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.tax!!
                tv_admin.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.admin!!
                tv_harga_pokok.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.harga_pokok_penjualan!!
                tv_total.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.total_income!!
                tv_laba.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.gross_profit!!
                tv_expenses.text = AppConstant.CURRENCY.getCurrencyData() + keuangan.expenses!!
                tv_return.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(keuangan.salesreturn!!)
            }
        }

    }

    override fun getParentLayout(): NestedScrollView {
        return ns_parent
    }

    override fun showErrorMessage(code: Int, msg: String) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> toast(this,msg)
        }

    }

    override fun reloadData() {
        sw_refresh.isRefreshing = true
        setData(null)
        getPresenter()?.loadData()
    }

    override fun openFilterDateDialog() {
        val dateDialog = RangeDateDialog.newInstance()
        dateDialog.setType(-1)
        dateDialog.setData(null,getPresenter()?.getToday()?.date,getPresenter()?.getFirstDate(),getPresenter()?.getLastDate())
        dateDialog.show(this.supportFragmentManager, RangeDateDialog.TAG)
    }

    override fun onDateRangeClicked(firstDate: CalendarDay?, lastDate: CalendarDay?, type: Int) {
        sw_refresh.isRefreshing = true
        setData(null)
        getPresenter()?.onChangeDate(firstDate,lastDate)
    }


}
