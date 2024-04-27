package id.kasirvippro.android.feature.transaction.detailReturn

import android.content.Intent
import android.graphics.Bitmap
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
import id.kasirvippro.android.feature.dialog.PaymentDialog
import id.kasirvippro.android.feature.printerReturn.PrinterActivity
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_transaction_detailreturn.*
import kotlinx.android.synthetic.main.activity_transaction_detailreturn.btn_share
import kotlinx.android.synthetic.main.activity_transaction_detailreturn.imageView_qrCode
import kotlinx.android.synthetic.main.activity_transaction_detailreturn.ns_scroll

class DetailReturnActivity : BaseActivity<DetailReturnPresenter, DetailReturnContract.View>(), DetailReturnContract.View, PaymentDialog.Listener {

    val adapter = DetailReturnAdapter()
    private var qrImage : Bitmap? = null
    override fun createPresenter(): DetailReturnPresenter {
        return DetailReturnPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_transaction_detailreturn
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

        btn_printer.setOnClickListener {
            getPresenter()?.onCheckBluetooth()
        }
        btn_share.setOnClickListener {
            getPresenter()?.onCheckShare()
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

    override fun setProducts(list: List<DetailTransaction.Data>) {
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

    override fun setInfo(id: String, subtotal: String, total: String, date: String, operator: String?, customer:String?,
                         payment: String, status: String, bayar:String?,kembalian:String?,service:String?,pajak:String?,diskon:String?,sisaHutang:String?,name_store:String?,address:String?,email:String?,nohp:String?,footer:String?,img:String?) {
        tv_id.text = id
        tv_subtotal.text = subtotal
        tv_total_big.text = total
        tv_total.text = total
        tv_date.text = date
        tv_operator.text = operator
        tv_payment.text = payment
        tv_status.text = status
        tv_name_store.text = name_store
        tv_alldata.text = address + ", " + email
        tv_nohp.text = nohp
        tv_footer.text = footer

        qrImage = net.glxn.qrgen.android.QRCode.from("Seller. " + name_store +"\n" + id + "\nDate. " + date + "\nSubtotal. " + subtotal.replace(AppConstant.CURRENCY.getCurrencyData(), "") + "\nTax. " + pajak.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "") + "\nTotal. " + total.replace(AppConstant.CURRENCY.getCurrencyData(), "")).withColor(0xFF000000.toInt(),
            0xFFFFFFFF.toInt()
        ).withSize(500, 500).bitmap()

        if(qrImage != null)
        {
            imageView_qrCode.setImageBitmap(qrImage)
        }

        GlideApp.with(this)
            .load(img)
            .transform(CenterCrop(), RoundedCorners(4))
            .into(tv_photo)

        ll_operator.visibility = View.GONE
        operator?.let {
            ll_operator.visibility = View.VISIBLE
            tv_operator.text = it
        }

        ll_bayar.visibility = View.GONE
        bayar?.let {
            ll_bayar.visibility = View.VISIBLE
            if(payment == "non cash"){
                lbl_pay_noncash.text = getString(R.string.lbl_payment_noncash)
                val totalorder = it.replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(".", "")
                if(kembalian.isNullOrEmpty() || kembalian.isNullOrBlank()){
                    var cashnya = totalorder.toDouble()
                    tv_bayar.text = AppConstant.CURRENCY.getCurrencyData() + "${(cashnya)}"
                }
                else{
                    val cashpay = kembalian!!.replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(".", "")
                    var cashnya = totalorder.toDouble() - cashpay.toDouble()
                    tv_bayar.text = AppConstant.CURRENCY.getCurrencyData() + "${(cashnya)}"
                }
            }else if(payment == "debt"){
                val totalorder = it.replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(".", "")
                if(kembalian.isNullOrEmpty() || kembalian.isNullOrBlank()){
                    var cashnya = totalorder.toDouble()
                    tv_bayar.text = AppConstant.CURRENCY.getCurrencyData() + "${(cashnya)}"
                }
                else{
                    val cashpay = kembalian!!.replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(".", "")
                    var cashnya = totalorder.toDouble() - cashpay.toDouble()
                    tv_bayar.text = AppConstant.CURRENCY.getCurrencyData() + "${(cashnya)}"
                }
            }else{
                lbl_pay_noncash.text = getString(R.string.lbl_detail_bayar)
                tv_bayar.text = it
            }
        }

        ll_kembalian.visibility = View.GONE
        kembalian?.let {
            ll_kembalian.visibility = View.VISIBLE
            tv_kembalian.text = it
            if(payment == "non cash"){
                ll_change_cash.text = getString(R.string.cash_nominal)
            }else if(payment == "debt"){
                ll_change_cash.text = getString(R.string.cash_nominal)
            }else{
                ll_change_cash.text = getString(R.string.lbl_detail_kembalian)
            }
        }

        ll_service.visibility = View.GONE
        service?.let {
            ll_service.visibility = View.VISIBLE
            tv_service.text = it
        }

        ll_tax.visibility = View.GONE
        pajak?.let {
            ll_tax.visibility = View.VISIBLE
            tv_tax.text = it
        }

        ll_discount.visibility = View.GONE
        diskon?.let {
            ll_discount.visibility = View.VISIBLE
            tv_discount.text = it
        }

        ll_sisa_hutang.visibility = View.GONE
        sisaHutang?.let {
            ll_sisa_hutang.visibility = View.VISIBLE
            tv_sisa_hutang.text = it
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
            "cancel" -> {
                btn_printer.visibility = View.GONE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_cancel_text))

            }
            "debt" -> {
                btn_printer.visibility = View.GONE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_credit_text))
            }
            else -> {
                btn_printer.visibility = View.VISIBLE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_success_text))
            }
        }
    }

    override fun openPrinterPage() {
        val intent = Intent(this,PrinterActivity::class.java)
        intent.putExtra(AppConstant.DATA,getPresenter()?.getDataStruk())
        startActivity(intent)
    }

    override fun openPaymentDialog() {
        val dialog = PaymentDialog.newInstance()
        dialog.setData(getPresenter()?.getDataStruk()!!,getPresenter()?.getTypeTRX()!!)
        dialog.show(supportFragmentManager, PaymentDialog.TAG)
    }

    override fun onPay(selected: DetailTransaction, type: Int, value: String) {
        showLoadingDialog()
        getPresenter()?.onPay(value)
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



}
