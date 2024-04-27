package id.kasirvippro.android.feature.manage.rawMaterial.list

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
import id.kasirvippro.android.feature.manage.rawMaterial.add.AddRawMaterialActivity
import id.kasirvippro.android.feature.manage.rawMaterial.edit.EditRawMaterialActivity
import id.kasirvippro.android.feature.scan.ScanCodeActivity
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_list_product.btn_add
import kotlinx.android.synthetic.main.activity_list_product.rv_list
import kotlinx.android.synthetic.main.activity_list_product.sw_refresh
import kotlin.collections.mutableListOf

class RawMaterialListActivity : BaseActivity<RawMaterialListPresenter, RawMaterialListContract.View>(),
    RawMaterialListContract.View {

    val adapter = RawMaterialListAdapter()
    var list2 = arrayListOf<RawMaterial>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_ADD = 1001
    private val CODE_OPEN_EDIT = 1002
    private val CODE_OPEN_SCAN = 1003

    override fun createPresenter(): RawMaterialListPresenter {
        return RawMaterialListPresenter(this, this)
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

            }
        }
        rv_list.addOnScrollListener(scrollListener)

        adapter.callback = object : RawMaterialListAdapter.ItemClickCallback{
            override fun onClick(data: RawMaterial) {
                openEditPage(data)
            }

            override fun onDelete(data: RawMaterial,position :Int) {
                showLoadingDialog()
                getPresenter()?.deleteProduct(data.id_raw_material!!,position,data.id_raw_material!!)
            }
        }

        btn_add.setOnClickListener {
            openAddPage()
        }
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_manage_raw_material_title)
            elevation = 0f
        }

    }

    public fun setList(){
        adapter.clearAdapter()
        adapter.setItems(list2)
        adapter.notifyDataSetChanged()
    }

    override fun setProducts(list: List<RawMaterial>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        list2 = list as ArrayList<RawMaterial>
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
       if (requestCode == CODE_OPEN_ADD){
            if(resultCode == Activity.RESULT_OK){
                if(data == null){
                    reloadData()
                    return
                }
                if(data.getSerializableExtra(AppConstant.DATA) == null){
                    reloadData()
                    return
                }
                val product = data.getSerializableExtra(AppConstant.DATA) as RawMaterial
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
        val intent = Intent(this,AddRawMaterialActivity::class.java)
        startActivityForResult(intent,CODE_OPEN_ADD)
    }

    override fun openEditPage(data: RawMaterial) {
        hideLoadingDialog()
        val intent = Intent(this, EditRawMaterialActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }


}
