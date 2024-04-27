package id.kasirvippro.android.feature.manageStock.stockHistoryRawMaterial.data

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.RangeDateDialog
import id.kasirvippro.android.models.transaction.DetailHistory
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_list_history_stok.rv_list
import kotlinx.android.synthetic.main.activity_list_history_stok.sw_refresh
import kotlinx.android.synthetic.main.activity_list_history_stok.*
import kotlinx.android.synthetic.main.activity_report_daily.*

class DataHistoryActivity : BaseActivity<DataHistoryPresenter, DataHistoryContract.View>(),
    DataHistoryContract.View, RangeDateDialog.Listener {

    val adapter = DataHistoryAdapter()
    var list2 = arrayListOf<DetailHistory>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun createPresenter(): DataHistoryPresenter {
        return DataHistoryPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_list_history_stok
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    public fun hideLoading(){
        hideLoadingDialog()
    }

    private fun renderView() {
        sw_refresh.isRefreshing = false
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

        adapter.callback = object : DataHistoryAdapter.ItemClickCallback{
            override fun onClick(data: DetailHistory) {
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_date_dl, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun setInfo(name:String?,date:String) {
        var text = date
        name?.let {
            text = "$it - $date"
        }
    }

    override fun getParentLayout(): NestedScrollView {
        return ns_parent
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.action_date -> openFilterDateDialog()
            R.id.action_download -> getPresenter()?.onCheckDownload()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.tracking_stock)
            elevation = 0f
        }

    }

    public fun setList(){
        adapter.clearAdapter()
        adapter.setItems(list2)
        adapter.notifyDataSetChanged()
    }

    override fun getIdProduct(): String? {
        val idproduct = getIntent().getStringExtra("id_product")
        return idproduct
    }

    override fun setProducts(list: List<DetailHistory>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        list2 = list as ArrayList<DetailHistory>
        adapter.setItems(list)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
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
        emptyData()
        getPresenter()?.loadProducts()
    }

    override fun openFilterDateDialog() {
        val dateDialog = RangeDateDialog.newInstance()
        dateDialog.setType(-1)
        dateDialog.setData(null,getPresenter()?.getToday()?.date,getPresenter()?.getFirstDate(),getPresenter()?.getLastDate())
        dateDialog.show(this.supportFragmentManager, RangeDateDialog.TAG)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onDateRangeClicked(firstDate: CalendarDay?, lastDate: CalendarDay?, type: Int) {
        sw_refresh.isRefreshing = true
        emptyData()
        getPresenter()?.onChangeDate(firstDate,lastDate)
    }

    @SuppressLint("SetTextI18n")
    private fun emptyData(){
        adapter.clearAdapter()
    }

}
