package id.kasirvippro.android.feature.pulsaPpob.dataProduct

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.pulsaPpob.order.OrderActivity
import id.kasirvippro.android.models.pulsaPpob.PulsaPpob
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_pulsa.*
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_pulsa.et_brand
import kotlinx.android.synthetic.main.activity_pulsa.rv_list
import kotlinx.android.synthetic.main.activity_pulsa.sw_refresh


class DataProductActivity : BaseActivity<DataProductPresenter, DataProductContract.View>(), DataProductContract.View {
    val adapter = DataProductAdapter()
    var list2 = arrayListOf<PulsaPpob>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_EDIT = 102

    override fun createPresenter(): DataProductPresenter {
        return DataProductPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_pulsa
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    public fun hideLoading(){
        hideLoadingDialog()
    }


    private fun renderView(){
        btn_click.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val search = et_search.text.toString().trim()
            getPresenter()?.onSearch(search)
            adapter.clearAdapter()
        }
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
        adapter.callback = object : DataProductAdapter.ItemClickCallback{
            override fun onClick(data: PulsaPpob) {
                openOrderPage(data)
            }

        }

        /*et_search.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                adapter.clearAdapter()
                sw_refresh.isRefreshing = true
                getPresenter()?.searchPrefix(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

         */

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_insert_number)
            elevation = 0f
        }

    }

    override fun getJenis(): String {
        val value = et_category.text.toString()
        return value
    }
    override fun getBrand(): String {
        val value = et_brand.text.toString()
        return value
    }

    override fun reloadData() {
        sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        getPresenter()?.loadData()
    }

    override fun openOrderPage(data: PulsaPpob) {
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun showErrorMessage(code: Int, msg: String?) {
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

    override fun getValue(): String {
        val value = et_search.text.toString()
        return value
    }

    override fun setData(list: List<PulsaPpob>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
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

    override fun showMessage(code: Int, msg: String?) {
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

    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    @SuppressLint("SetTextI18n")
    override fun setData(category:String?, brand:String?)
    {

        category?.let {
            et_category.visibility = View.GONE
            val et_category = findViewById<TextView>(R.id.et_category)
            et_category.setText(HtmlCompat.fromHtml(it, 0))
        }

        brand?.let {
            et_brand.visibility = View.GONE
            val et_brand = findViewById<TextView>(R.id.et_brand)
            et_brand.setText(HtmlCompat.fromHtml(it, 0))
        }


    }


}
