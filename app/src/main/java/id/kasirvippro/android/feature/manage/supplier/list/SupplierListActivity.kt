package id.kasirvippro.android.feature.manage.supplier.list

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manage.supplier.add.AddSupplierActivity
import id.kasirvippro.android.feature.manage.supplier.detail.DetailActivity
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_list_supplier.btn_add
import kotlinx.android.synthetic.main.activity_list_supplier.rv_list
import kotlinx.android.synthetic.main.activity_list_supplier.sw_refresh

class SupplierListActivity : BaseActivity<SupplierListPresenter, SupplierListContract.View>(),
    SupplierListContract.View {

    val adapter = SupplierListAdapter()
    var list2 = arrayListOf<Supplier>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_ADD = 1001
    private val CODE_OPEN_EDIT = 1002

    override fun createPresenter(): SupplierListPresenter {
        return SupplierListPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_list_supplier
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    public fun hideLoading(){
        hideLoadingDialog()
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

        adapter.callback = object : SupplierListAdapter.ItemClickCallback{
            override fun onClick(data: Supplier) {
                openEditPage(data)
            }

            override fun onDelete(data: Supplier,position :Int) {
                showLoadingDialog()
                getPresenter()?.deleteSupplier(data.id_supplier!!,position,data.inc!!)
            }
        }


        btn_add.setOnClickListener {
            openAddPage()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_one, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.lbl_search_supplier)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener  {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.clearAdapter()
                sw_refresh.isRefreshing = true
                getPresenter()?.searchSupplier(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })


        return true
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
            title = getString(R.string.lbl_manage_supplier_title)
            elevation = 0f
        }

    }

    override fun setData(list: List<Supplier>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    override fun setSuppliers(list: List<Supplier>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        list2 = list as ArrayList<Supplier>
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

        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = this.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        adapter.clearAdapter()

        val dataManager = DataManager (this)
        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    getPresenter()?.loadSuppliers()
                    dataManager.clearSupplierAll()
                    return
                }else{
                    var allsupplier = dataManager.allSupplier()
                    var supplierList = mutableListOf<Supplier>()
                    for (item in allsupplier){
                        var supplier = Supplier();
                        supplier.set(item.id_supplier,item.name_supplier,
                            item.telephone, item.email,
                            item.address,
                            item.increment
                        )
                        supplierList.add(supplier)
                    }
                    getPresenter()?.setSupplier(supplierList);
                }
            }else{
                var allsupplier = dataManager.allSupplier()
                var supplierList = mutableListOf<Supplier>()
                for (item in allsupplier){
                    var supplier = Supplier();
                    supplier.set(item.id_supplier,item.name_supplier,
                        item.telephone, item.email,
                        item.address,
                        item.increment
                    )
                    supplierList.add(supplier)
                }
                getPresenter()?.setSupplier(supplierList);
            }
        }else{
            var allsupplier = dataManager.allSupplier()
            var supplierList = mutableListOf<Supplier>()
            for (item in allsupplier){
                var supplier = Supplier();
                supplier.set(item.id_supplier,item.name_supplier,
                    item.telephone, item.email,
                    item.address,
                    item.increment
                )
                supplierList.add(supplier)
            }
            getPresenter()?.setSupplier(supplierList);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            reloadData()
        }
    }

    public fun setList(){
        adapter.clearAdapter()
        adapter.setItems(list2)
        adapter.notifyDataSetChanged()
    }

    override fun openAddPage() {
        val intent = Intent(this,AddSupplierActivity::class.java)
        startActivityForResult(intent,CODE_OPEN_ADD)
    }

    override fun openEditPage(data: Supplier) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

     override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

}
