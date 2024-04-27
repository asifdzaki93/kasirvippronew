package id.kasirvippro.android.feature.pulsaPpob.order

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.transaction.success.SuccessActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_orders.*
import kotlinx.android.synthetic.main.activity_orders.et_name


class OrderActivity : BaseActivity<OrderPresenter, OrderContract.View>(), OrderContract.View {

    override fun createPresenter(): OrderPresenter {
        return OrderPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_orders
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){
        tv_order.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val pin = et_pin.text.toString().trim()
            getPresenter()?.onCheck(pin)
        }

        et_phone.setOnClickListener {
            hideKeyboard()
            val phone = et_phone.text.toString().trim()
            val brand = et_brand.text.toString().trim()
            getPresenter()?.onCheckToken(phone, brand)
        }

        et_hargajual.addTextChangedListener(NumberTextWatcher(et_hargajual))
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Detail"
            elevation = 0f

        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun showMessage(code:Int, msg:String?) {
        hideLoadingDialog()
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                msg?.let {
                 //   toast(this,it)
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Info")
                    builder.setMessage(it)
                    builder.setCancelable(false)
                    builder.setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    builder.show()
                }

            }

        }

    }

    override fun openSuccessPage(id: String) {
        val intent = Intent(this, SuccessActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
    }


    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }

    override fun getPhone(): String {
        val value = et_phone.text.toString()
        return value
    }
    override fun getSku(): String {
        val value = et_buyer_sku_code.text.toString()
        return value
    }
    override fun getProductname(): String {
        val value = et_name.text.toString()
        return value
    }

    override fun getPay(): Double {
        val value = et_hargajual.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")
        if (value.isBlank() || value.isEmpty()) {
            return 0.0
        }
        return value.toDouble()
    }

    @SuppressLint("SetTextI18n")
    override fun setData(product_name: String?, category:String?, brand:String?, price:String?, buyer_sku_code:String?, desc:String?, phone:String?, gbr:String?, buyer_product_status:String?, seller_product_status:String?)
    {
        product_name?.let {
            et_name.setText(it)
        }

        desc?.let {
            val et_detail = findViewById<TextView>(R.id.et_detail)
            et_detail.setText(HtmlCompat.fromHtml(it, 0))
        }
        seller_product_status?.let {
            val et_status = findViewById<TextView>(R.id.et_status)
            et_status.setText(HtmlCompat.fromHtml(it, 0))
        }
        price?.let {
            val et_price = findViewById<TextView>(R.id.et_price)
            et_price.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it)
        }
        phone?.let {
            val et_phone = findViewById<TextView>(R.id.et_phone)
            et_phone.setText(HtmlCompat.fromHtml(it, 0))
        }

        buyer_sku_code?.let {
            et_buyer_sku_code.visibility = View.GONE
            val et_buyer_sku_code = findViewById<TextView>(R.id.et_buyer_sku_code)
            et_buyer_sku_code.setText(HtmlCompat.fromHtml(it, 0))
        }

        brand?.let {
            et_brand.visibility = View.GONE
            val et_brand = findViewById<TextView>(R.id.et_brand)
            et_brand.setText(HtmlCompat.fromHtml(it, 0))
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }


}
