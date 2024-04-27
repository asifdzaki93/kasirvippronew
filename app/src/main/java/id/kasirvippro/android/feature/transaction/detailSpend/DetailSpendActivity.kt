package id.kasirvippro.android.feature.transaction.detailSpend

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.transaction.DetailSpend
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_spend_detail.*

class DetailSpendActivity : BaseActivity<DetailSpendPresenter, DetailSpendContract.View>(), DetailSpendContract.View{

    val adapter = DetailSpendAdapter()

    override fun createPresenter(): DetailSpendPresenter {
        return DetailSpendPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_spend_detail
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView() {
        sw_refresh.isRefreshing = false
        sw_refresh.setOnRefreshListener {
            reloadData()
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        btn_share.setOnClickListener {
            getPresenter()?.onCheckShare()
        }

        btn_printer.setOnClickListener {
            getPresenter()?.onCheckBluetooth()
        }


        btn_cancel.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.deleteDetail()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> onClose()
        }
        return super.onOptionsItemSelected(item!!)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Expense Details"
            elevation = 0f
        }

    }

    override fun setProducts(list: List<DetailSpend.Data>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
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

    override fun reloadData() {
        hideLoadingDialog()
        sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        hideShowActionButton(View.GONE)
        getPresenter()?.loadDetail()
    }

    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }

    override fun setInfo(id:String,total:String,date:String,user:String?,img:String?) {
        tv_id.text = id
        tv_total.text = total
        tv_total_big.text = total
        tv_date.text = date

        ll_user.visibility = View.GONE
        user?.let {
            ll_user.visibility = View.VISIBLE
            tv_user.text = it
        }

        if(img == null){
            ll_receipt.visibility = View.GONE
        }
        else{
            GlideApp.with(this)
                .load(img)
                .error(R.drawable.logo_bulat)
                .placeholder(R.drawable.logo_bulat)
                .transform(CenterCrop(), RoundedCorners(4))
                .into(receipt)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onBackPressed() {
        onClose()
    }

    override fun onClose() {
        finish()
    }

    override fun getParentLayout(): NestedScrollView {
        return ns_scroll
    }

    override fun hideShowActionButton(visibility: Int) {
        ll_button.visibility = visibility
    }



}
