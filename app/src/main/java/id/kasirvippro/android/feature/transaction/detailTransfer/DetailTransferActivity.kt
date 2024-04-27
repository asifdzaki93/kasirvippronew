package id.kasirvippro.android.feature.transaction.detailTransfer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.printer.PrinterActivity
import id.kasirvippro.android.feature.transaction.success.SuccessActivity
import id.kasirvippro.android.models.transaction.DetailTransfer
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_transfer_detail.*

class DetailTransferActivity : BaseActivity<DetailTransferPresenter, DetailTransferContract.View>(), DetailTransferContract.View {

    val adapter = DetailTransferAdapter()
    private val codeOpenChooseDiscount = 1004
    private val codeOpenChooseNontunai = 1003

    override fun createPresenter(): DetailTransferPresenter {
        return DetailTransferPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_transfer_detail
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

        //btn_printer.setOnClickListener {
        //    getPresenter()?.onCheckBluetooth()
        // }

        btn_share.setOnClickListener {
            getPresenter()?.onCheckShare()
        }

        btn_finish.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.finishDetail()
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
            title = getString(R.string.menu_detail_transaction)
            elevation = 0f
        }

    }

    override fun setProducts(list: List<DetailTransfer.Data>) {
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

    @SuppressLint("SetTextI18n")
    override fun setInfo(id: String, subtotal: String, total: String, date: String, operator: String?,
                         payment: String, status: String, bayar:String?, kembalian:String?, sisaHutang:String?, name_store:String?, address:String?, email:String?, nohp:String?, img:String?,store_destination:String?) {
        tv_id.text = id
        tv_total_big.text = total
        tv_total.text = total
        tv_date.text = date
        tv_operator.text = operator
        tv_status.text = status
        tv_name_store.text = name_store
        tv_alldata.text = address + ", " + email
        tv_nohp.text = nohp
        tv_store.text = store_destination

        val totalorder:String = total.replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(".", "")

        GlideApp.with(this)
            .load(img)
            .transform(CenterCrop(), RoundedCorners(4))
            .into(tv_photo)

        ll_operator.visibility = View.GONE
        operator?.let {
            ll_operator.visibility = View.VISIBLE
            tv_operator.text = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onBackPressed() {
        onClose()
    }

    override fun enableBtn(type:String?) {

        when (type) {
            "pending" -> {
                // btn_printer.visibility = View.VISIBLE
                btn_finish.visibility = View.VISIBLE
                btn_share.visibility = View.GONE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_credit_text))
            }
            "finish" -> {
                // btn_printer.visibility = View.VISIBLE
                btn_share.visibility = View.GONE
                btn_finish.visibility = View.GONE
                btn_finish.visibility = View.GONE
            }
            else -> {
                //  btn_printer.visibility = View.VISIBLE
                btn_finish.visibility = View.GONE
                btn_share.visibility = View.VISIBLE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_success_text))
            }
        }
    }

    override fun openPrinterPage() {
        val intent = Intent(this,PrinterActivity::class.java)
        intent.putExtra(AppConstant.DATA,getPresenter()?.getDataStruk())
        startActivity(intent)
    }

    override fun onClose() {
        if(getPresenter()?.isOpenMain()!!){
            restartMainActivity()
        }
        else{
            finish()
        }
    }

    override fun getParentLayout(): NestedScrollView {
        return ns_scroll
    }

    override fun hideShowActionButton(visibility: Int) {
        ll_button.visibility = visibility
    }

    override fun onPremiumPage(isPremium: Boolean) {
        if(isPremium){
            tv_powered_by.visibility = View.GONE
        }
        else{
            tv_powered_by.visibility = View.VISIBLE
        }
    }


    override fun openSuccessPage(id: String) {
        val intent = Intent(this, SuccessActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)

    }



}
