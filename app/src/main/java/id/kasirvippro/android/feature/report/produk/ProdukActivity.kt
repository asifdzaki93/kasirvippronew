package id.kasirvippro.android.feature.report.produk

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.RangeDateDialog
import id.kasirvippro.android.models.report.ReportProduct
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activityt_report_transaksi.rv_list
import kotlinx.android.synthetic.main.activityt_report_transaksi.sw_refresh
import kotlinx.android.synthetic.main.activityt_report_transaksi.tv_avg_nett
import kotlinx.android.synthetic.main.activityt_report_transaksi.tv_nett
import kotlinx.android.synthetic.main.activityt_report_transaksi.tv_transaksi

class ProdukActivity : BaseActivity<ProdukPresenter, ProdukContract.View>(),
    ProdukContract.View, RangeDateDialog.Listener {

    val adapter = ProdukAdapter()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun createPresenter(): ProdukPresenter {
        return ProdukPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activityt_report_transaksi
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView() {
        sw_refresh.setOnRefreshListener {
            scrollListener.resetState()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_date, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
            R.id.action_date -> openFilterDateDialog()
        }
        return super.onOptionsItemSelected(item!!)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_product_report)
            elevation = 0f
        }

    }

    override fun setData(data: ReportProduct) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
       // adapter.setItems(list)
        val penjualan = data.sales_report
        penjualan?.let {
            setList(penjualan)
        }
    }

    override fun setList(list: List<ReportProduct.Sales>?) {
        rv_list.visibility = View.VISIBLE
        adapter.clearAdapter()
        adapter.setItems(list)
    }

    @SuppressLint("SetTextI18n")
    override fun setInfo(labarugi: String, rata2: String, amount: String) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        hideLoadingDialog()
        sw_refresh.isRefreshing = false

            if(decimal=="No Decimal") {
                tv_nett.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(labarugi)
                tv_avg_nett.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(rata2)
                tv_transaksi.text = Helper.convertToCurrency(amount)
            }else{
                tv_nett.text = AppConstant.CURRENCY.getCurrencyData() + labarugi
                tv_avg_nett.text = AppConstant.CURRENCY.getCurrencyData() + rata2
                tv_transaksi.text = amount
            }
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun showErrorMessage(code: Int, msg: String) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        if(code == RestException.CODE_USER_NOT_FOUND){
            restartLoginActivity()
        }
        else{

            toast(this,msg)
        }

    }

    override fun showSuccessMessage(msg: String?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        msg?.let {
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
        }
        reloadData()

    }

    override fun reloadData() {
        sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        getPresenter()?.loadData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            reloadData()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun openFilterDateDialog() {
        val dateDialog = RangeDateDialog.newInstance()
        dateDialog.setType(-1)
        dateDialog.setData(null,getPresenter()?.getToday()?.date,getPresenter()?.getFirstDate(),getPresenter()?.getLastDate())
        dateDialog.show(this.supportFragmentManager, RangeDateDialog.TAG)
    }

    override fun onDateRangeClicked(firstDate: CalendarDay?, lastDate: CalendarDay?, type: Int) {
        sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        getPresenter()?.onChangeDate(firstDate,lastDate)
    }
}
