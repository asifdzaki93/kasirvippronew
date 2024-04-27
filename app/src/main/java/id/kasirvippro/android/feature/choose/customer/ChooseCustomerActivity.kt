package id.kasirvippro.android.feature.choose.customer

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
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_choose_customer.*

class ChooseCustomerActivity : BaseActivity<ChooseCustomerPresenter, ChooseCustomerContract.View>(),
    ChooseCustomerContract.View {

    val adapter = ChooseCustomerAdapter()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun createPresenter(): ChooseCustomerPresenter {
        return ChooseCustomerPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_choose_customer
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
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

        adapter.callback = object : ChooseCustomerAdapter.ItemClickCallback{
            override fun onClick(data: Customer) {
                onSelected(data)
            }
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
                getPresenter()?.search(newText)
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
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item!!)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_choose_customer)
            elevation = 0f
        }

    }

    override fun setData(list: List<Customer>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
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
                    getPresenter()?.loadData()
                    dataManager.clearCustomerAll()
                }else{
                    var allcustomer = dataManager.allCustomer()

                    var customerList = mutableListOf<Customer>()
                    for (item in allcustomer){
                        var customer = Customer();
                        customer.set(item.id_customer,item.email,
                            item.name_customer,item.telephone,
                            item.address,item.customercode,
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
                    customer.set(item.id_customer,item.email,
                        item.name_customer,item.telephone,
                        item.address,item.customercode,
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
                customer.set(item.id_customer,item.email,
                    item.name_customer,item.telephone,
                    item.address,item.customercode,
                    item.increment
                )
                customerList.add(customer)
            }
            getPresenter()?.setCustomer(customerList);
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onSelected(data: Customer) {
        val newintent:Intent = intent
        newintent.putExtra(AppConstant.DATA,data)
        setResult(RESULT_OK,newintent)
        finish()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }


}
