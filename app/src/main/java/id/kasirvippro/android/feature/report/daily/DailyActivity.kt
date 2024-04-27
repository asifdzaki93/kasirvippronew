package id.kasirvippro.android.feature.report.daily

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
import id.kasirvippro.android.feature.printerDaily.PrinterDailyActivity
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.report.ReportDaily
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_report_daily.*


class DailyActivity : BaseActivity<DailyPresenter, DailyContract.View>(), DailyContract.View, RangeDateDialog.Listener, BottomDialog.Listener {

    val adapter = DailyAdapter()
    private val storeDialog = BottomDialog.newInstance()


    override fun createPresenter(): DailyPresenter {
        return DailyPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_report_daily
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
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



    private fun setupToolbar() {
        supportActionBar?.apply {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_daily_report)
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
    override fun setData(data: ReportDaily) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false

        val info = data.info
        emptyData()
        info?.let {
            tv_name_store.text = it.name_store!!
            //tv_date.text = it.date!!
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                tv_total.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it.total!!)
            }else{
                tv_total.text = AppConstant.CURRENCY.getCurrencyData() + it.total!!
            }
        }

        val daily_report = data.daily_report
        daily_report?.let {
            setList(daily_report)
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

    override fun openPrinterPage(data:ReportDaily) {
        val intent = Intent(this, PrinterDailyActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivity(intent)
    }

    @SuppressLint("SetTextI18n")
    private fun emptyData(){
        tv_name_store.text = "Name Store"
        tv_total.text = "100.000"

        adapter.clearAdapter()
    }

    override fun setList(list: List<ReportDaily.Daily>?) {
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
