package id.kasirvippro.android.feature.report.penjualan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.BottomDialog
import id.kasirvippro.android.feature.dialog.RangeDateDialog
import id.kasirvippro.android.feature.printerSumary.PrinterSumaryActivity
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.report.ReportPenjualan
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant

import kotlinx.android.synthetic.main.activity_report_penjualan.*
import kotlinx.android.synthetic.main.activity_report_penjualan.sw_refresh
import kotlinx.android.synthetic.main.activity_report_penjualan.tv_nett

class PenjualanActivity : BaseActivity<PenjualanPresenter, PenjualanContract.View>(),
    PenjualanContract.View, RangeDateDialog.Listener, BottomDialog.Listener {

    val adapter = PenjualanAdapter()
    private val storeDialog = BottomDialog.newInstance()


    override fun createPresenter(): PenjualanPresenter {
        return PenjualanPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_report_penjualan
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
        reloadData()
    }

    private fun renderView() {
        ll_search.setOnClickListener {
            getPresenter()?.onCheckStore()
        }

        sw_refresh.setOnRefreshListener {
            reloadData()
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_date_print_dl, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
            R.id.action_date -> openFilterDateDialog()
            R.id.action_print -> getPresenter()?.onCheckBluetooth()
            R.id.action_download -> getPresenter()?.onCheckDownload()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun openPrinterPage(data: ReportPenjualan) {
        val intent = Intent(this, PrinterSumaryActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivity(intent)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            title = "Sales"
            elevation = 0f
        }
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun getParentLayout(): NestedScrollView {
        return ns_parent
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    @SuppressLint("SetTextI18n")
    override fun setData(data: ReportPenjualan) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        hideLoadingDialog()
        sw_refresh.isRefreshing = false

        val info = data.info
        emptyData()
        info?.let {
            if(decimal=="No Decimal") {
                tv_nett.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it.total_sales!!)
                tv_avg_nett.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it.average!!)
                tv_transaksi.text = Helper.convertToCurrency(it.number_transaction!!)
            }else{
                tv_nett.text = AppConstant.CURRENCY.getCurrencyData() + it.total_sales!!
                tv_avg_nett.text = AppConstant.CURRENCY.getCurrencyData() + it.average!!
                tv_transaksi.text = it.number_transaction!!
            }

        }

        val penjualan = data.sales_report
        penjualan?.let {
            setList(penjualan)
        }

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
        emptyData()
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
        emptyData()
        getPresenter()?.onChangeDate(firstDate,lastDate)
    }

    @SuppressLint("SetTextI18n")
    private fun emptyData(){
        tv_nett.text = "0"
        tv_avg_nett.text = "0"
        tv_transaksi.text = "0"

        adapter.clearAdapter()
    }

    override fun setList(list: List<ReportPenjualan.Penjualan>?) {
        rv_list.visibility = View.VISIBLE
        tv_error.visibility = View.GONE
        adapter.clearAdapter()
        adapter.setItems(list)
    }

    override fun setStoreName(value: String) {
        et_search.text = value
    }

    override fun openStores(title: String, list: List<DialogModel>, selected: DialogModel?) {
        hideLoadingDialog()
        if (storeDialog.dialog != null && storeDialog.dialog!!.isShowing) {

        } else {
            storeDialog.setData(title,1, list,selected)
            storeDialog.show(supportFragmentManager, "storeDialog")
        }
    }

    override fun onItemClicked(data: DialogModel, type: Int) {
        sw_refresh.isRefreshing = true
        emptyData()
        getPresenter()?.setSelectedStore(data)
    }

}
