package id.kasirvippro.android.feature.pulsaPpob.detailProduct

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
import id.kasirvippro.android.feature.transaction.success.SuccessActivity
import id.kasirvippro.android.models.pulsaPpob.DetailPpob
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_ppob.*
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.item_detail_productppob.et_hargajual
import kotlinx.android.synthetic.main.item_detail_productppob.et_pin
import kotlinx.android.synthetic.main.activity_ppob.et_brand
import kotlinx.android.synthetic.main.item_detail_productppob.*


class DetailProductActivity : BaseActivity<DetailProductPresenter, DetailProductContract.View>(), DetailProductContract.View {
    val adapter = DetailProductAdapter()
    var list2 = arrayListOf<DetailPpob>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_EDIT = 102

    override fun createPresenter(): DetailProductPresenter {
        return DetailProductPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_ppob
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    public fun hideLoading(){
        hideLoadingDialog()
    }


    private fun renderView(){
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
        adapter.callback = object : DetailProductAdapter.ItemClickCallback{
            override fun onClick(data: DetailPpob) {
               // openOrderPage(data)
            }

            override fun onOrder(data: DetailPpob) {
                hideKeyboard()
                showLoadingDialog()
                val pin = et_pin.text.toString().trim()
                getPresenter()?.onCheck(pin)
            }

        }

        btn_cek.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val search = et_search.text.toString().trim()
            getPresenter()?.searchPrefix(search)
        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_insert_number)
            elevation = 0f
        }

    }

    override fun getPay(): Double {
        val value = et_hargajual.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(".", "")
        if (value.isBlank() || value.isEmpty()) {
            return 0.0
        }
        return value.toDouble()
    }

    override fun getPhone(): String {
        val value = tv_customerno.text.toString()
        return value
    }
    override fun getSku(): String {
        val value = tv_sku.text.toString()
        return value
    }
    override fun getRef(): String {
        val value = tv_reff.text.toString()
        return value
    }
    override fun getHa(): String {
        val value = tv_price.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(".", "")
        return value
    }


    override fun openSuccessPage(id: String) {
        val intent = Intent(this, SuccessActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
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
    }

    override fun openOrderPage(data: DetailPpob) {
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

    override fun setData(list: List<DetailPpob>) {
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
