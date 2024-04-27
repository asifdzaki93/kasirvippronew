package id.kasirvippro.android.feature.manage.recipe.list

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.manage.recipe.edit.EditRecipeActivity
import id.kasirvippro.android.feature.manage.recipe.add.AddRecipeActivity
import id.kasirvippro.android.models.recipe.Recipe
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_add_pricevariant.*
import kotlinx.android.synthetic.main.activity_list_product_recipe.btn_add
import kotlinx.android.synthetic.main.activity_list_product_recipe.rv_list
import kotlinx.android.synthetic.main.activity_list_product_recipe.sw_refresh

class RecipeListActivity : BaseActivity<RecipeListPresenter, RecipeListContract.View>(),
    RecipeListContract.View {

    val adapter = RecipeListAdapter()
    var list2 = arrayListOf<Recipe>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_ADD = 1001
    private val CODE_OPEN_EDIT = 1002

    override fun createPresenter(): RecipeListPresenter {
        return RecipeListPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_list_product_recipe
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

        adapter.callback = object : RecipeListAdapter.ItemClickCallback{
            override fun onClick(data: Recipe) {
                openEditPage(data)
            }

            override fun onDelete(data: Recipe,position :Int) {
                showLoadingDialog()
                getPresenter()?.deleteProduct(data.id_recipe!!,position)
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
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_recipe)
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

    override fun getDetail(): String? {
        val detail = getIntent().getStringExtra("detail")
        return detail
    }

    override fun setProducts(list: List<Recipe>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        list2 = list as ArrayList<Recipe>
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
                val product = data.getSerializableExtra(AppConstant.DATA) as Recipe
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
        val detail = getIntent().getStringExtra("detail")
        val intent = Intent(this, AddRecipeActivity::class.java)
        intent.putExtra(AppConstant.DATA,id_product)
        intent.putExtra("id_product", id_product);
        intent.putExtra("detail", detail);
        startActivityForResult(intent,CODE_OPEN_ADD)
    }

    @SuppressLint("SetTextI18n")
    override fun setProduct(value: String) {
        et_name_product.setText(value)
        Log.d("detail", value)
    }


    override fun openEditPage(data: Recipe) {
        hideLoadingDialog()
        val intent = Intent(this, EditRecipeActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }


}
