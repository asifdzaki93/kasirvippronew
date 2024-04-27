package id.kasirvippro.android.feature.manage.product.list

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
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
import id.kasirvippro.android.feature.manage.product.add.AddProductActivity
import id.kasirvippro.android.feature.manage.product.edit.EditProductActivity
import id.kasirvippro.android.feature.scan.ScanCodeActivity
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_list_product.btn_add
import kotlinx.android.synthetic.main.activity_list_product.rv_list
import kotlinx.android.synthetic.main.activity_list_product.sw_refresh
import kotlin.collections.mutableListOf

class ProductListActivity : BaseActivity<ProductListPresenter, ProductListContract.View>(),
    ProductListContract.View {

    val adapter = ProductListAdapter()
    var list2 = arrayListOf<Product>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_ADD = 1001
    private val CODE_OPEN_EDIT = 1002
    private val CODE_OPEN_SCAN = 1003
    private var recyclerVisiblePosition = 0

    override fun createPresenter(): ProductListPresenter {
        return ProductListPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_list_product
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
                val pages = page+1
                Log.d("Tambah",page.toString())
                Handler().postDelayed(Runnable { getPresenter()?.loadProducts(pages) }, 1000)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                //    Handler().postDelayed(Runnable { getNewPosition(recyclerView) }, 1000)
                }

            }
        }
        rv_list.addOnScrollListener(scrollListener)

        adapter.callback = object : ProductListAdapter.ItemClickCallback{
            override fun onClick(data: Product) {
                openEditPage(data)
            }

            override fun onDelete(data: Product,position :Int) {
                showLoadingDialog()
                getPresenter()?.deleteProduct(data.id_product!!,position,data.inc!!)
            }
        }

        btn_add.setOnClickListener {
            openAddPage()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_scan, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.lbl_search_product)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener  {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.clearAdapter()
                sw_refresh.isRefreshing = true
                getPresenter()?.searchProduct(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.action_scan -> getPresenter()?.onCheckScan()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_manage_produk_title)
            elevation = 0f
        }

    }

    override fun setData(list: List<Product>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    public fun setList(){
        adapter.clearAdapter()
        adapter.setItems(list2)
        adapter.notifyDataSetChanged()
    }

    override fun setProducts(list: List<Product>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        list2 = list as ArrayList<Product>
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
                    getPresenter()?.loadProducts(1)

                    dataManager.clearCategoryAll()
                    dataManager.clearProductAll()
                    return
                }else{
                    var allproduct = dataManager.allProduct()

                    var productList = mutableListOf<Product>()
                    for (item in allproduct){
                        var product = Product();
                        product.set(item.id_product,item.nama,item.unit,
                            item.img,item.kode,
                            item.idkategori,item.namakategori,
                            item.beli, item.jual,
                            item.stok, item.minstok,
                            item.deskripsi, item.online,
                            item.havestok, item.grosir,
                            item.increment,item.alertstock
                        )
                        productList.add(product)
                    }
                    getPresenter()?.setProduct(productList);
                }
            }else{
                var allproduct = dataManager.allProduct()

                var productList = mutableListOf<Product>()
                for (item in allproduct){
                    var product = Product();
                    product.set(item.id_product,item.nama,item.unit,
                        item.img,item.kode,
                        item.idkategori,item.namakategori,
                        item.beli, item.jual,
                        item.stok, item.minstok,
                        item.deskripsi, item.online,
                        item.havestok, item.grosir,
                        item.increment,item.alertstock
                    )
                    productList.add(product)
                }
                getPresenter()?.setProduct(productList);
            }
        }else{
            var allproduct = dataManager.allProduct()

            var productList = mutableListOf<Product>()
            for (item in allproduct){
                var product = Product();
                product.set(item.id_product,item.nama,item.unit,
                    item.img,item.kode,
                    item.idkategori,item.namakategori,
                    item.beli, item.jual,
                    item.stok, item.minstok,
                    item.deskripsi, item.online,
                    item.havestok, item.grosir,
                    item.increment,item.alertstock
                )
                productList.add(product)
            }
            getPresenter()?.setProduct(productList);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CODE_OPEN_SCAN){
            if(resultCode == Activity.RESULT_OK){
                val text = data?.getStringExtra(AppConstant.DATA)
                text?.let {
                    showLoadingDialog()
                    getPresenter()?.searchByBarcode(it)
                }
            }
        }
        else if (requestCode == CODE_OPEN_ADD){
            if(resultCode == Activity.RESULT_OK){
                if(data == null){
                    reloadData()
                    return
                }
                if(data.getSerializableExtra(AppConstant.DATA) == null){
                    reloadData()
                    return
                }
                val product = data.getSerializableExtra(AppConstant.DATA) as Product
                if(product == null){
                    reloadData()
                }
                else{
                    openEditPage(product)
                }
            }
        }

        else if (requestCode == CODE_OPEN_ADD || requestCode == CODE_OPEN_EDIT){
            if(resultCode == Activity.RESULT_OK){
                reloadData()
            }
        }

    }

    override fun openAddPage() {
        val intent = Intent(this,AddProductActivity::class.java)
        startActivityForResult(intent,CODE_OPEN_ADD)
    }

    override fun openEditPage(data: Product) {
        hideLoadingDialog()
        val intent = Intent(this,EditProductActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun openScanPage() {
        val intent = Intent(this,ScanCodeActivity::class.java)
        startActivityForResult(intent,CODE_OPEN_SCAN)
    }


}
