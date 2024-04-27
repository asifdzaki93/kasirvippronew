package id.kasirvippro.android.feature.addOn.dataMedical

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.transaction.detail.DetailActivity
import id.kasirvippro.android.models.customer.MedicalRecord
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_list_history_stok.*

class DataHistoryActivity : BaseActivity<DataHistoryPresenter, DataHistoryContract.View>(),
    DataHistoryContract.View {

    val adapter = DataHistoryAdapter()
    var list2 = arrayListOf<MedicalRecord>()
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
            override fun onClick(data: MedicalRecord) {
                openDetail(data.no_invoice!!)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }

    override fun setInfo(name:String?,date:String) {
        var text = date
        name?.let {
            text = "$it - $date"
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_medical_record)
            elevation = 0f
        }

    }

    public fun setList(){
        adapter.clearAdapter()
        adapter.setItems(list2)
        adapter.notifyDataSetChanged()
    }

    override fun getIdProduct(): String? {
        val idproduct = getIntent().getStringExtra("id_customer")
        return idproduct
    }

    override fun setProducts(list: List<MedicalRecord>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        list2 = list as ArrayList<MedicalRecord>
        adapter.setItems(list)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }



    override fun openDetail(id: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
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
        adapter.clearAdapter()
        getPresenter()?.loadProducts()
    }
    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

}
