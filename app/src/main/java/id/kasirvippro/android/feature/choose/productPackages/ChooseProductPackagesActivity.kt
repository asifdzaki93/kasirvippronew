package id.kasirvippro.android.feature.choose.productPackages

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.newProduct.ActivityCategory
import id.kasirvippro.android.feature.dialog.BottomProductDialog
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_choose_product.*
import kotlinx.android.synthetic.main.activity_choose_product_purchase.*
import kotlinx.android.synthetic.main.activity_choose_product_purchase.rv_list
import kotlinx.android.synthetic.main.activity_choose_product_purchase.sw_refresh

class ChooseProductPackagesActivity : BaseActivity<ChooseProductPackagesPresenter, ChooseProductPackagesContract.View>(),
    ChooseProductPackagesContract.View, BottomProductDialog.Listener {

    val adapter = ChooseProductPackagesAdapter()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_ADD_PRODUCT = 1003
    private var recyclerVisiblePosition = 0
    private val productDialog = BottomProductDialog.newInstance()

    override fun createPresenter(): ChooseProductPackagesPresenter {
        return ChooseProductPackagesPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_choose_product_purchase
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
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
                val pages = page+1
                Log.d("Tambah",page.toString())
                Handler().postDelayed(Runnable { getPresenter()?.loadProducts(pages) }, 1000)
            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    //Handler().postDelayed(Runnable { getNewPosition(recyclerView) }, 1000)
                    Log.d("Handle", recyclerView.toString())
                }

            }
        }
        rv_list.addOnScrollListener(scrollListener)

        adapter.callback = object : ChooseProductPackagesAdapter.ItemClickCallback{
            override fun onClick(data: Product) {
                showLoadingDialog()
                if(data.id_master=="0"){
                    onSelected(data)
                }else {
                    getPresenter()?.onCheckVariable(data.id_product!!)
                }
            }
        }
    }

    private fun getNewPosition(@NonNull recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
        if (layoutManager != null) {
            recyclerVisiblePosition = layoutManager.findLastVisibleItemPosition()
            val totalItem: Int = layoutManager.getItemCount()
            val lastVisibleItem: Int = layoutManager.findLastVisibleItemPosition()
            val page = recyclerVisiblePosition/19


            if (lastVisibleItem == totalItem - 1) {
                sw_refresh.isRefreshing = true
                getPresenter()?.loadProducts(page)
                Log.d("TAG", "loadNextPage: $page $totalItem $lastVisibleItem")
            }
        }
    }

    override fun openProducts(title: String, list: List<Product>, selectedp: Product?) {
        hideLoadingDialog()
        if (productDialog.dialog != null && productDialog.dialog!!.isShowing) {

        } else {
            productDialog.setData(title, 1, list, selectedp)
            productDialog.show(supportFragmentManager, "product")
        }
    }

    override fun onItemClicked(data: Product, type: Int) {
        getPresenter()?.setSelectedProduct(data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_one, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.lbl_search_product)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener  {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.clearAdapter()
                sw_refresh.isRefreshing = true
                getPresenter()?.searchProduct(newText)
                return true
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
            R.id.action_category -> { startActivity(Intent(this, ActivityCategory::class.java)) }
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun showMessage(code: Int, msg: String?) {
        hideLoadingDialog()
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                msg?.let {
                    toast(this,it)}
            }

        }

    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_choose_product)
            elevation = 0f
        }

    }

    override fun setProducts(list: List<Product>) {
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
        adapter.clearAdapter()
        getPresenter()?.loadProducts(1)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onSelected(data: Product) {
        val newintent:Intent = intent
        newintent.putExtra(AppConstant.DATA,data)
        setResult(RESULT_OK,newintent)
        finish()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    override fun checkStockProducts(isCheck: Boolean) {
        adapter.setCheckStok(isCheck)
    }


}
