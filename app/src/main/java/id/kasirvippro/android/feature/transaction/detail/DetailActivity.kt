package id.kasirvippro.android.feature.transaction.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.discount.ChooseDiscountActivity
import id.kasirvippro.android.feature.choose.nonTunai.ChooseNonTunaiActivity
import id.kasirvippro.android.feature.dialog.PaymentDialog
import id.kasirvippro.android.feature.printer.PrinterActivity
import id.kasirvippro.android.feature.transaction.success.SuccessActivity
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.models.transaction.NonTunai
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.feature.sell.paymentNonTunai.WebViewActivity
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_transaction_detail.*
import kotlinx.android.synthetic.main.activity_transaction_detail.btn_bayar
import kotlinx.android.synthetic.main.activity_transaction_detail.btn_printer
import kotlinx.android.synthetic.main.activity_transaction_detail.btn_share
import kotlinx.android.synthetic.main.activity_transaction_detail.et_pay
import kotlinx.android.synthetic.main.activity_transaction_detail.et_pay_non
import kotlinx.android.synthetic.main.activity_transaction_detail.imageView_qrCode
import kotlinx.android.synthetic.main.activity_transaction_detail.lbl_pay_noncash
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_bayar
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_button
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_discount
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_non_tunai
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_service
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_tax
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_tunai
import kotlinx.android.synthetic.main.activity_transaction_detail.rg_payment
import kotlinx.android.synthetic.main.activity_transaction_detail.rv_list
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_discount
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_kembalian
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_service
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_subtotal
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_tax
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_total
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_paynon
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_cash
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_change_cash
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_kembalian
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_operator
import kotlinx.android.synthetic.main.activity_transaction_detail.ll_sisa_hutang
import kotlinx.android.synthetic.main.activity_transaction_detail.ns_scroll
import kotlinx.android.synthetic.main.activity_transaction_detail.sw_refresh
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_alldata
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_bayar
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_date
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_footer
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_id
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_name_store
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_nohp
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_operator
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_payment
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_photo
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_powered_by
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_sisa_hutang
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_status
import kotlinx.android.synthetic.main.activity_transaction_detail.tv_total_big
import java.text.NumberFormat
import java.util.*
import java.util.regex.PatternSyntaxException

class DetailActivity : BaseActivity<DetailPresenter, DetailContract.View>(), DetailContract.View, PaymentDialog.Listener {

    val adapter = DetailAdapter()
    private var qrImage : Bitmap? = null
    private val codeOpenChooseDiscount = 1004
    private val codeOpenChooseNontunai = 1003

    override fun createPresenter(): DetailPresenter {
        return DetailPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_transaction_detail
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView() {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        sw_refresh.isRefreshing = false
        sw_refresh.setOnRefreshListener {
            reloadData()
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        btn_cancel.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.deleteDetail()
        }

        btn_printer.setOnClickListener {
            getPresenter()?.onCheckBluetooth()
        }

        btn_bayar.setOnClickListener {
            openPaymentDialog()
        }

        btn_share.setOnClickListener {
            getPresenter()?.onCheckShare()
        }

        btn_link.setOnClickListener {
            getPresenter()?.onShare()
        }

        btn_finish.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.finishDetail()
        }

        //et_discount.setOnClickListener {
        //     openChooseDiscount()
        // }
        // btn_delete_discount.setOnClickListener {
        //     getPresenter()?.updateDiscount(null)
        //}

        rg_payment.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_tunai -> showTunaiView()
                R.id.rb_nontunai -> showNonTunaiView()
            }
        }
        rg_payment.check(R.id.rb_tunai)

       // et_payment.setOnClickListener {
        //     openChooseNonTunai()
        //}

        btn_update_order.setOnClickListener {
            showLoadingDialog()
            val note = ""
            val id = tv_id.text.toString().trim()
            when (rg_payment.checkedRadioButtonId) {
                R.id.rb_tunai -> {
                    getPresenter()?.checkTunai(DetailTransaction())
                }
                R.id.rb_nontunai -> {
                    getPresenter()?.checkNonTunai(DetailTransaction())
                }
            }
        }

        if(decimal=="No Decimal") {
            et_pay.addTextChangedListener(NumberTextWatcher(et_pay))
        }else{
            et_pay.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
        }

        et_pay_non.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                getPresenter()?.countNon()
            }
        })
    }
    fun EditText.inputFilterDecimal(
        // maximum digits including point and without decimal places
        maxDigitsIncludingPoint: Int,
        maxDecimalPlaces: Int // maximum decimal places

    ){

        try {

            filters = arrayOf<InputFilter>(

                Helper.DecimalDigitsInputFilter(maxDigitsIncludingPoint, maxDecimalPlaces)

            )

        }catch (e: PatternSyntaxException){

            isEnabled = false

            hint = e.message

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == codeOpenChooseDiscount && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val discount = data.getSerializableExtra(AppConstant.DATA) as Discount
            if (discount.id_discount == null) {
                toast(this,"Data not found")
            } else {
                getPresenter()?.updateDiscount(discount)
            }
        }else if (requestCode == codeOpenChooseNontunai && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val discount = data.getSerializableExtra(AppConstant.DATA) as NonTunai
            if (discount.id_link == null) {
                toast(this,"Data not found")
            } else {
                getPresenter()?.updateNonTunai(discount)
            }
        }

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

    override fun getTotalValue(): Double {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            val value = tv_total.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")

            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()

        }else{
            val nf: NumberFormat = NumberFormat.getInstance(Locale.GERMAN)
            val number: Double = nf.parse(tv_total.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")).toDouble()

            val value = tv_total.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")
            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()
        }

    }

    override fun getPayValue(): Double {

        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            val value = et_pay.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")

            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()

        }else{
            val nf: NumberFormat = NumberFormat.getInstance(Locale.GERMAN)
            val number: Double = nf.parse(et_pay.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")).toDouble()

            val value = tv_total.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")
            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()
        }
    }

    override fun getNoteValue(): String {
        val value = ""
        return value.toString()
    }

    override fun showTunaiView() {
        // ll_nontunai.visibility = View.GONE
        //  ll_nocard.visibility = View.GONE
        //  ll_payments.visibility = View.GONE
        ll_tunai.visibility = View.VISIBLE
        ll_non_tunai.visibility = View.GONE
        ll_cash.visibility = View.GONE
    }
    override fun showNonTunaiView() {
        //  ll_nontunai.visibility = View.VISIBLE
        // ll_nocard.visibility = View.VISIBLE
        // ll_payments.visibility = View.VISIBLE
        ll_tunai.visibility = View.GONE
        ll_non_tunai.visibility = View.VISIBLE
        ll_cash.visibility = View.VISIBLE
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
    override fun setInfo(id: String, subtotal: String, total: String, date: String, operator: String?, customer:String?, table:String?, supplier:String?,
                         payment: String, status: String, bayar:String?, kembalian:String?, service:String?, pajak:String?, diskon:String?, sisaHutang:String?, name_store:String?, address:String?, email:String?, nohp:String?, footer:String?, img:String?, id_table:String?, link_order:String?, due_date:String?, note:String?, ongkir:String?, divisi:String?) {
        tv_id.text = id
        tv_subtotal.text = subtotal
        tv_total_big.text = total
        tv_total.text = total
        tv_date.text = date
        tv_due.text = due_date
        tv_operator.text = operator
        tv_payment.text = payment
        tv_table.text = table
        tv_status.text = status
        tv_name_store.text = name_store
        tv_alldata.text = address + ", " + email
        tv_nohp.text = nohp
        tv_note.text = note
        tv_footer.text = footer

        qrImage = net.glxn.qrgen.android.QRCode.from(link_order).withColor(0xFF000000.toInt(),
            0xFFFFFFFF.toInt()
        ).withSize(500, 500).bitmap()

        if(qrImage != null)
        {
            imageView_qrCode.setImageBitmap(qrImage)
        }

        val totalorder:String = total.replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")
        et_pay_non.setText(totalorder)

        GlideApp.with(this)
            .load(img)
            .transform(CenterCrop(), RoundedCorners(4))
            .into(tv_photo)

        ll_operator.visibility = View.GONE
        operator?.let {
            ll_operator.visibility = View.VISIBLE
            tv_operator.text = it
        }

        ll_customer.visibility = View.GONE
        customer?.let {
            ll_customer.visibility = View.VISIBLE
            tv_customer.text = it
        }
        ll_table.visibility = View.GONE
        table?.let {
            ll_table.visibility = View.VISIBLE
            tv_table.text = it
        }

        ll_supplier.visibility = View.GONE
        supplier?.let {
            if (it != "null"){
                ll_supplier.visibility = View.VISIBLE
                tv_supplier.text = it
            }
        }

        ll_due.visibility = View.GONE
        due_date?.let {
            if (it != "0000-00-00"){
                ll_due.visibility = View.VISIBLE
                tv_due.text = it
            }
        }

        ll_bayar.visibility = View.GONE
        bayar?.let {
            ll_bayar.visibility = View.VISIBLE
            if(payment == "non cash"){
                lbl_pay_noncash.text = getString(R.string.lbl_payment_noncash)
                val totalorder = it.replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")
                if(kembalian.isNullOrEmpty() || kembalian.isNullOrBlank()){
                    var cashnya = totalorder.toDouble()
                    tv_bayar.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(cashnya)
                }
                else{
                    val cashpay = kembalian!!.replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")
                    var cashnya = totalorder.toDouble() - cashpay.toDouble()
                    tv_bayar.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(cashnya)
                }
            }else if(payment == "debt"){
                val totalorder = it.replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")
                if(kembalian.isNullOrEmpty() || kembalian.isNullOrBlank()){
                    var cashnya = totalorder.toDouble()
                    tv_bayar.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(cashnya)
                }
                else{
                    val cashpay = kembalian!!.replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")
                    var cashnya = totalorder.toDouble() - cashpay.toDouble()
                    tv_bayar.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(cashnya)
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
            }else if(payment == ""){
                ll_change_cash.text = getString(R.string.not_paid)
            }else{
                ll_change_cash.text = getString(R.string.lbl_detail_kembalian)
            }
        }

        ll_service.visibility = View.GONE
        service?.let {
            ll_service.visibility = View.VISIBLE
            tv_service.text = it
        }

        ll_ongkir.visibility = View.GONE
        ongkir?.let {
            ll_ongkir.visibility = View.VISIBLE
            tv_ongkir.text = it
        }

        ll_divisi.visibility = View.GONE
        divisi?.let {
            ll_divisi.visibility = View.VISIBLE
            tv_divisi.text = it
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
                btn_bayar.visibility = View.GONE
                btn_cancel.visibility = View.GONE
                btn_printer.visibility = View.VISIBLE
                btn_share.visibility = View.VISIBLE
                ll_paymentbox.visibility = View.GONE
                btn_finish.visibility = View.GONE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_cancel_text))

            }
            "billing" -> {
                btn_bayar.visibility = View.GONE
                btn_cancel.visibility = View.VISIBLE
                btn_printer.visibility = View.VISIBLE
                btn_share.visibility = View.GONE
                ll_paymentbox.visibility = View.GONE
                ll_kembalian.visibility = View.GONE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_credit_text))
            }
            "process" -> {
                btn_bayar.visibility = View.GONE
                btn_cancel.visibility = View.VISIBLE
                btn_printer.visibility = View.VISIBLE
                btn_share.visibility = View.GONE
                ll_paymentbox.visibility = View.GONE
                ll_kembalian.visibility = View.GONE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_credit_text))
            }
            "pre order" -> {
                btn_bayar.visibility = View.VISIBLE
                btn_cancel.visibility = View.VISIBLE
                btn_printer.visibility = View.VISIBLE
                btn_share.visibility = View.GONE
                ll_paymentbox.visibility = View.GONE
                ll_kembalian.visibility = View.VISIBLE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_credit_text))
            }
            "debt" -> {
                btn_bayar.visibility = View.VISIBLE
                btn_cancel.visibility = View.GONE
                btn_printer.visibility = View.VISIBLE
                btn_share.visibility = View.VISIBLE
                ll_paymentbox.visibility = View.GONE
                btn_finish.visibility = View.GONE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_debt_text))
            }
            "finish" -> {
                btn_bayar.visibility = View.VISIBLE
                btn_cancel.visibility = View.VISIBLE
                btn_printer.visibility = View.VISIBLE
                btn_share.visibility = View.GONE
                btn_finish.visibility = View.GONE
            }
            else -> {
                btn_bayar.visibility = View.GONE
                btn_cancel.visibility = View.GONE
                btn_printer.visibility = View.VISIBLE
                btn_finish.visibility = View.GONE
                btn_share.visibility = View.VISIBLE
                ll_paymentbox.visibility = View.GONE
                tv_status.setTextColor(ContextCompat.getColor(this,R.color.status_success_text))
            }
        }
    }

    override fun openPrinterPage() {
        val intent = Intent(this,PrinterActivity::class.java)
        intent.putExtra(AppConstant.DATA,getPresenter()?.getDataStruk())
        startActivity(intent)
    }

    override fun openChooseDiscount() {
        val intent = Intent(this, ChooseDiscountActivity::class.java)
        startActivityForResult(intent, codeOpenChooseDiscount)
    }
    override fun openChooseNonTunai() {
        val intent = Intent(this, ChooseNonTunaiActivity::class.java)
        startActivityForResult(intent, codeOpenChooseNontunai)
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

    override fun onWaiterPage() {

        ll_paymentbox.visibility = View.GONE

    }


    override fun onKitchenPage() {

        ll_paymentbox.visibility = View.GONE
        btn_cancel.visibility = View.VISIBLE

    }
    override fun setDiscount(data: Discount?) {
        //et_discount.text = ""
        // btn_delete_discount.visibility = View.GONE
        data?.let {
            //     et_discount.text = it.name_discount
            //     btn_delete_discount.visibility = View.VISIBLE
        }
    }

    override fun setNonTunai(data: NonTunai?) {
       // et_payment.text = ""
        data?.let {
            //   et_payment.text = it.name_link
        }
    }


    override fun openSuccessPage(id: String) {
        val intent = Intent(this, SuccessActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)

    }

    override fun getPayNon(): Double {
        val value = et_pay_non.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")
        if (value.isBlank() || value.isEmpty()) {
            return 0.0
        }
        return value.toDouble()
    }

    override fun hideShowNon(value: Int) {
        tv_paynon.visibility = value
    }

    @SuppressLint("SetTextI18n", "ShowToast")
    override fun setCashNon(value: Double) {
        when {
            value == 0.0 -> {
                hideShowNon(View.VISIBLE)
                tv_paynon.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(value)
            }
            value < 0.0 -> {
                hideShowNon(View.VISIBLE)
                tv_paynon.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(value)
            }
            else -> {
                hideShowNon(View.VISIBLE)
                tv_paynon.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(value)
            }
        }
    }

    override fun openShare(code: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT, "Check your Order Status at: $code")
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "Send to"))
    }

    override fun openPaymentNonTunai(url: String, id: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(AppConstant.Webview.URL,url)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
    }


}
