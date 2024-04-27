package id.kasirvippro.android.feature.manage.packages.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manage.packages.add.AddPackagesActivity
import id.kasirvippro.android.feature.manage.packages.edit.EditPackagesActivity
import id.kasirvippro.android.feature.manage.packages.item.PackagesItemActivity
import id.kasirvippro.android.feature.manage.packages.main.PackagesActivity
import id.kasirvippro.android.models.packages.Packages
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppConstant.Code.CODE_OPEN_ADD
import kotlinx.android.synthetic.main.activity_list_category.*
import kotlinx.android.synthetic.main.activity_list_category.rv_list
import kotlinx.android.synthetic.main.activity_list_category.sw_refresh

class PackagesListActivity : BaseActivity<PackagesListPresenter, PackagesListContract.View>(),
    PackagesListContract.View {

    val adapter = PackagesListAdapter()
    var list2 = arrayListOf<Packages>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_EDIT = 102
    private val CODE_OPEN_ADDITEM = 104

    override fun createPresenter(): PackagesListPresenter {
        return PackagesListPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_list_category
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

        adapter.callback = object : PackagesListAdapter.ItemClickCallback{
            override fun onClick(data: Packages) {
                openEditCategoryPage(data)
            }

            override fun onAdd(data: Packages) {
                openAdd(data)
            }

            override fun onDetail(data: Packages) {
                openList(data)
            }

            override fun onDelete(data: Packages,position :Int) {
                showLoadingDialog()
                getPresenter()?.deleteCategory(data.id_product!!,position)
            }
        }

        btn_add.setOnClickListener {
            openAddCategoryPage()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_one, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.lbl_search_packages)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener  {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.clearAdapter()
                sw_refresh.isRefreshing = true
                getPresenter()?.searchCategory(newText)
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
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_produk_packages_title)
            elevation = 0f
        }

    }

    override fun setData(list: List<Packages>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    public fun setList(){
        adapter.clearAdapter()
        adapter.setItems(list2)
        adapter.notifyDataSetChanged()
    }

    override fun setCategorys(list: List<Packages>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        list2 = list as ArrayList<Packages>
        adapter.setItems(list)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun showErrorMessage(code: Int, msg: String?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
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

    override fun showSuccessMessage(msg: String?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        msg?.let {
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }
        reloadData()

    }

    override fun reloadData() {
        sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        getPresenter()?.loadCategories()
    }

    override fun openAddCategoryPage() {
        val intent = Intent(this,PackagesActivity::class.java)
        startActivityForResult(intent,CODE_OPEN_ADD)
    }

    override fun openEditCategoryPage(data: Packages) {
        val intent = Intent(this,EditPackagesActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun openAdd(data: Packages) {
        val id = data.id_product
        val intent = Intent(this,AddPackagesActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        intent.putExtra("id_product", id)
        startActivityForResult(intent,CODE_OPEN_ADDITEM)
    }

    override fun openList(data: Packages) {
        val id = data.sesi
        val intent = Intent(this,PackagesItemActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        intent.putExtra("sesi", id)
        startActivityForResult(intent,CODE_OPEN_ADDITEM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            reloadData()
        }
    }



}
