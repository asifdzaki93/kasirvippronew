package id.kasirvippro.android.feature.report.mutasi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.RangeDateDialog
import id.kasirvippro.android.models.report.ReportMutasi
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_report_mutasi.*

class MutasiActivity : BaseActivity<MutasiPresenter, MutasiContract.View>(),
    MutasiContract.View, RangeDateDialog.Listener {

    val adapter = MutasiAdapter()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun createPresenter(): MutasiPresenter {
        return MutasiPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_report_mutasi
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
        reloadData()
    }

    private fun renderView() {
        ll_search.setOnClickListener {
            openFilterDateDialog()
        }

        sw_refresh.setOnRefreshListener {
            reloadData()
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onFirstItemVisible(isFirstItem: Boolean) {
                sw_refresh.isEnabled = isFirstItem && adapter.itemCount > 0
            }

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {

            }
        }
        rv_list.addOnScrollListener(scrollListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            title = "Mutasi Kas"
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
    override fun setData(data: ReportMutasi) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false

        val info = data.info
        info?.let {
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                tv_nett.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it.penjualan_bersih!!)
                tv_transaksi.text = Helper.convertToCurrency(it.jumlah_transaksi!!)
            }else{
                tv_nett.text = AppConstant.CURRENCY.getCurrencyData() + it.penjualan_bersih!!
                tv_transaksi.text = it.jumlah_transaksi!!
            }

        }

        val transaksi = data.transaksi
        transaksi?.let {
            if(it.isNotEmpty()){
                rv_list.visibility = View.VISIBLE
                tv_error.visibility = View.GONE
                adapter.clearAdapter()
                adapter.setItems(it)
            }
            else{
                showErrorMessage(999,"Data not found")
            }
        }
    }

    override fun showErrorMessage(code: Int, msg: String) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                rv_list.visibility = View.GONE
                tv_error.visibility = View.VISIBLE
                tv_error.text = msg
            }
        }

    }

    @SuppressLint("SetTextI18n")
    override fun reloadData() {
        sw_refresh.isRefreshing = true
        tv_nett.text = "0"
        tv_transaksi.text = "0"
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
        tv_nett.text = "0"
        tv_transaksi.text = "0"
        getPresenter()?.onChangeDate(firstDate,lastDate)
        setDate(firstDate?.date.toString(),lastDate?.date.toString())
    }

    override fun setDate(firstDate: String, lastDate: String) {
        val date1 = Helper.getDateFormat(this,firstDate,"yyyy-MM-dd","dd MMMM yyyy")
        val date2 = Helper.getDateFormat(this,lastDate,"yyyy-MM-dd","dd MMMM yyyy")
        var date = date1
        if(date1 != date2){
            date = "$date1 - $date2"
        }
        et_search.text = date
    }

}
