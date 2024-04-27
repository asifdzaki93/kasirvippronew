package id.kasirvippro.android.feature.manage.productVariant.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manage.productVariant.add.AddProductVariantActivity
import id.kasirvippro.android.feature.manage.productVariant.edit.EditProductVariantActivity
import id.kasirvippro.android.feature.scan.ScanCodeActivity
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_list_product_variant.btn_add
import kotlinx.android.synthetic.main.activity_list_product_variant.rv_list
import kotlinx.android.synthetic.main.activity_list_product_variant.sw_refresh

class ProductVariantListActivity : BaseActivity<ProductVariantListPresenter, ProductVariantListContract.View>(),
    ProductVariantListContract.View {

    val adapter = ProductVariantListAdapter()
    var list2 = arrayListOf<Product>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_ADD = 1001
    private val CODE_OPEN_EDIT = 1002
    private val CODE_OPEN_SCAN = 1003

    override fun createPresenter(): ProductVariantListPresenter {
        return ProductVariantListPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_list_product_variant
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

        adapter.callback = object : ProductVariantListAdapter.ItemClickCallback{
            override fun onClick(data: Product) {
                openEditPage(data)
            }

            override fun onDelete(data: Product,position :Int) {
                showLoadingDialog()
                getPresenter()?.deleteProduct(data.id_product!!,position,data.inc!!)
            }
        }

        btn_add.setOnClickListener {
            getPresenter()?.onAddPage()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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
            title = getString(R.string.lbl_manage_variant_produk_title)
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
        adapter.clearAdapter()
        getPresenter()?.loadProducts()
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
                }
            }
        }


    }


    override fun openAddPage(id: String) {
        val id_product = getIntent().getStringExtra("id_product")
        val intent = Intent(this, AddProductVariantActivity::class.java)
        intent.putExtra(AppConstant.DATA,id_product)
        intent.putExtra("id_product", id_product);
        startActivityForResult(intent,CODE_OPEN_ADD)
    }


    override fun openEditPage(data: Product) {
        hideLoadingDialog()
        val intent = Intent(this, EditProductVariantActivity::class.java)
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
