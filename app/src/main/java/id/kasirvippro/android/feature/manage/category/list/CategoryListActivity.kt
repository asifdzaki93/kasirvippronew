package id.kasirvippro.android.feature.manage.category.list

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
import id.kasirvippro.android.feature.manage.category.add.AddCategoryActivity
import id.kasirvippro.android.feature.manage.category.edit.EditCategoryActivity
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppConstant.Code.CODE_OPEN_ADD
import kotlinx.android.synthetic.main.activity_list_category.*
import kotlinx.android.synthetic.main.activity_list_category.rv_list
import kotlinx.android.synthetic.main.activity_list_category.sw_refresh

class CategoryListActivity : BaseActivity<CategoryListPresenter, CategoryListContract.View>(),
    CategoryListContract.View {

    val adapter = CategoryListAdapter()
    var list2 = arrayListOf<Category>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_EDIT = 102

    override fun createPresenter(): CategoryListPresenter {
        return CategoryListPresenter(this, this)
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

        adapter.callback = object : CategoryListAdapter.ItemClickCallback{
            override fun onClick(data: Category) {
                openEditCategoryPage(data)
            }

            override fun onDelete(data: Category,position :Int) {
                showLoadingDialog()
                getPresenter()?.deleteCategory(data.id_category!!,position,data.inc!!)
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
        searchView.queryHint = getString(R.string.lbl_search_category)
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
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_category)
            elevation = 0f
        }

    }

    override fun setData(list: List<Category>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    public fun setList(){
        adapter.clearAdapter()
        adapter.setItems(list2)
        adapter.notifyDataSetChanged()
    }

    override fun setCategorys(list: List<Category>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        list2 = list as ArrayList<Category>
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
                    getPresenter()?.loadCategories()
                    dataManager.clearCategoryAll()
                    return
                }else{
                    var allcategory = dataManager.allCategory()
                    var categoryList = mutableListOf<Category>()
                    for (item in allcategory){
                        var category = Category();
                        category.set(
                            item.id_category,
                            item.name_category,
                            item.increment
                        )
                        categoryList.add(category)
                    }
                    getPresenter()?.setCategory(categoryList);
                }
            }else{
                var allcategory = dataManager.allCategory()

                var categoryList = mutableListOf<Category>()
                for (item in allcategory){
                    var category = Category();
                    category.set(
                        item.id_category,
                        item.name_category,
                        item.increment
                    )
                    categoryList.add(category)
                }
                getPresenter()?.setCategory(categoryList);
            }
        }else{
            var allcategory = dataManager.allCategory()

            var categoryList = mutableListOf<Category>()
            for (item in allcategory){
                var category = Category();
                category.set(
                    item.id_category,
                    item.name_category,
                    item.increment
                )
                categoryList.add(category)
            }
            getPresenter()?.setCategory(categoryList);
        }
    }

    override fun openAddCategoryPage() {
        val intent = Intent(this,AddCategoryActivity::class.java)
        startActivityForResult(intent,CODE_OPEN_ADD)
    }

    override fun openEditCategoryPage(data: Category) {
        val intent = Intent(this,EditCategoryActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            reloadData()
        }
    }



}
