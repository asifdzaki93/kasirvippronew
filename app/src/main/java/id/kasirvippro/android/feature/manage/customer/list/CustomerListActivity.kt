package id.kasirvippro.android.feature.manage.customer.list

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manage.customer.add.AddCustomerActivity
import id.kasirvippro.android.feature.manage.customer.detail.CustomerDetailActivity
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_list_category.*
import kotlinx.android.synthetic.main.activity_list_customer.rv_list
import kotlinx.android.synthetic.main.activity_list_customer.sw_refresh

class CustomerListActivity : BaseActivity<CustomerListPresenter, CustomerListContract.View>(),
    CustomerListContract.View {

    val adapter = CustomerListAdapter()
    var list2 = arrayListOf<Customer>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_ADD = 101
    private val CODE_OPEN_EDIT = 102

    override fun createPresenter(): CustomerListPresenter {
        return CustomerListPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_list_customer
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

        adapter.callback = object : CustomerListAdapter.ItemClickCallback{
            override fun onClick(data: Customer) {
                openEditPage(data)
            }

            override fun onDelete(data: Customer,position :Int) {
                showLoadingDialog()
                getPresenter()?.deleteCustomer(data.id_customer!!,position,data.inc!!)
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
        searchView.queryHint = getString(R.string.lbl_search_customer)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener  {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.clearAdapter()
                sw_refresh.isRefreshing = true
                getPresenter()?.searchCustomer(newText)
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
            title = getString(R.string.lbl_manage_pelanggan_title)
            elevation = 0f
        }

    }

    override fun setData(list: List<Customer>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    public fun setList(){
        adapter.clearAdapter()
        adapter.setItems(list2)
        adapter.notifyDataSetChanged()
    }

    override fun setCustomers(list: List<Customer>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        list2 = list as ArrayList<Customer>
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
            toast(this,msg)
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
                    getPresenter()?.loadCustomers()
                    dataManager.clearCustomerAll()
                    return
                }else{
                    var allcustomer = dataManager.allCustomer()
                    var customerList = mutableListOf<Customer>()
                    for (item in allcustomer){
                        var customer = Customer();
                        customer.set(
                            item.id_customer,
                            item.name_customer,
                            item.telephone,
                            item.email,
                            item.address,
                            item.customercode,
                            item.increment
                        )
                        customerList.add(customer)
                    }
                    getPresenter()?.setCustomer(customerList);
                }
            }else{
                var allcustomer = dataManager.allCustomer()

                var customerList = mutableListOf<Customer>()
                for (item in allcustomer){
                    var customer = Customer();
                    customer.set(
                        item.id_customer,
                        item.name_customer,
                        item.telephone,
                        item.email,
                        item.address,
                        item.customercode,
                        item.increment
                    )
                    customerList.add(customer)
                }
                getPresenter()?.setCustomer(customerList);
            }
        }else{
            var allcustomer = dataManager.allCustomer()

            var customerList = mutableListOf<Customer>()
            for (item in allcustomer){
                var customer = Customer();
                customer.set(
                    item.id_customer,
                    item.name_customer,
                    item.telephone,
                    item.email,
                    item.address,
                    item.customercode,
                    item.increment
                )
                customerList.add(customer)
            }
            getPresenter()?.setCustomer(customerList);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            reloadData()
        }
    }

    override fun openAddPage() {
        val intent = Intent(this,AddCustomerActivity::class.java)
        startActivityForResult(intent,CODE_OPEN_ADD)
    }

    override fun openEditPage(data: Customer) {
        val intent = Intent(this,CustomerDetailActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

}
