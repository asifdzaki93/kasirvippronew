package id.kasirvippro.android.feature.sell.confirmation

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.SingleDateDialog
import id.kasirvippro.android.feature.choose.customer.ChooseCustomerActivity
import id.kasirvippro.android.feature.choose.discount.ChooseDiscountActivity
import id.kasirvippro.android.feature.choose.kurir.ChooseKurirActivity
import id.kasirvippro.android.feature.choose.nonTunai.ChooseNonTunaiActivity
import id.kasirvippro.android.feature.choose.table.ChooseTableActivity
import id.kasirvippro.android.feature.sell.addCustomer.AddCustomerActivity
import id.kasirvippro.android.feature.sell.addDiscount.AddActivity
import id.kasirvippro.android.feature.sell.paymentNonTunai.WebViewActivity
import id.kasirvippro.android.feature.transaction.success.SuccessActivity
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.price.Price
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.transaction.NonTunai
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import org.threeten.bp.LocalDate
import id.kasirvippro.android.ui.PaymentNumberTextWatcher
import kotlinx.android.synthetic.main.activity_sell_confirmation.*
import java.text.NumberFormat
import java.util.*
import java.util.regex.PatternSyntaxException

class ConfirmationActivity : BaseActivity<ConfirmationPresenter,ConfirmationContract.View>(), ConfirmationContract.View, SingleDateDialog.Listener{

    private val TAG = ConfirmationActivity::class.java.simpleName

    private val CODE_OPEN_CHOOSE_NONTUNAI = 1003
    private val CODE_OPEN_CHOOSE_DISCOUNT = 1004
    private val CODE_OPEN_CHOOSE_CUSTOMER = 1005
    private val CODE_OPEN_ADD_CUSTOMER = 1006
    private val CODE_OPEN_ADD_DISCOUNT = 1009
    private val CODE_OPEN_CHOOSE_KURIR = 1008
    private val codeOpenChooseTable = 1007
    private val adapter = ConfirmationAdapter()
    private val adapter2 = PriceAdapter()

    override fun createPresenter(): ConfirmationPresenter {
        return ConfirmationPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_sell_confirmation
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Sales Confirmation"
            elevation = 0f
        }

    }

    private fun renderView() {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = this.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        val layoutManager2 = GridLayoutManager(this, 4)
        rv_list2.layoutManager = layoutManager2
        rv_list2.adapter = adapter2

        adapter2.callback = object : PriceAdapter.ItemClickCallback {
            override fun onClick(data: Price) {
                showLoadingDialog()
                getPresenter()?.setSelectedPrice(data)
            }
        }

        if(decimal=="No Decimal") {
            et_pay.addTextChangedListener(PaymentNumberTextWatcher(et_pay, getPresenter()!!))
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

        et_pay_debt.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                getPresenter()?.countDebt()
            }
        })

        rg_payment.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_tunai -> showTunaiView()
                R.id.rb_nontunai -> showNonTunaiView()
                R.id.rb_piutang -> showPiutangView()
            }
        }
        rg_payment.check(R.id.rb_tunai)

        et_customer.setOnClickListener {
            openChooseCustomer()
        }
        /*et_payment.setOnClickListener {
            openChooseNonTunai()
           // if ( connectivity != null) {
            //     info = connectivity!!.activeNetworkInfo
            //  if (info != null) {
            //      if (info!!.state == NetworkInfo.State.CONNECTED) {
            //          openChooseNonTunai()
            //      }else{
            //          Toast.makeText(this,"Not Connected", Toast.LENGTH_SHORT)
            //      }
            //  }else{
            //      Toast.makeText(this,"Not Connected", Toast.LENGTH_SHORT)
            //  }
            // }else{
            //  Toast.makeText(this,"Not Connected", Toast.LENGTH_SHORT)
            // }
        }

         */

        btn_add_customer.setOnClickListener {
            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        openAddCustomer()
                    }
                }
            }
        }

        btn_add_discount.setOnClickListener {
            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        openAddDiscount()
                    }
                }
            }
        }

        btn_delete_table.setOnClickListener {
            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        getPresenter()?.updateTable(null)
                    }
                }
            }
        }

        btn_delete_customer.setOnClickListener {
            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        getPresenter()?.updateCustomer(null)
                    }
                }
            }
        }

        et_discount.setOnClickListener {
            openChooseDiscount()
        }

        btn_delete_discount.setOnClickListener {
            if ( connectivity != null) {
                info = connectivity!!.activeNetworkInfo
                if (info != null) {
                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        getPresenter()?.updateDiscount(null)
                    }
                }
            }
        }

        btn_date.setOnClickListener {
            val now = LocalDate.now()
            openSingleDatePickerDialog(getPresenter()?.getSelectedDate(),now,null,AppConstant.Code.CODE_FILTER_DATE_SELL)
        }

        btn_bayar.setOnClickListener {
            showLoadingDialog()
            val note = ""
            when (rg_payment.checkedRadioButtonId) {
                R.id.rb_tunai -> {
                    getPresenter()?.checkTunai()
                }
                R.id.rb_nontunai -> {
                    getPresenter()?.checkNonTunai(note)
                }

                R.id.rb_piutang -> {
                    getPresenter()?.checkPiutang()
                }
            }
        }

        et_table.setOnClickListener {
            openChooseTable()
        }
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
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = this.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        if (requestCode == CODE_OPEN_CHOOSE_CUSTOMER && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val customer = data.getSerializableExtra(AppConstant.DATA) as Customer
            if (customer.id_customer == null) {
                toast(this,"Data not found")
            } else {
                getPresenter()?.updateCustomer(customer)
            }
        } else if (requestCode == CODE_OPEN_ADD_CUSTOMER && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val customer = data.getSerializableExtra(AppConstant.DATA) as Customer
            if (customer.id_customer == null) {
                toast(this,"Data not found")
            } else {
                getPresenter()?.updateCustomer(customer)
            }
        } else if (requestCode == CODE_OPEN_ADD_DISCOUNT && resultCode == Activity.RESULT_OK) {
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
        } else if (requestCode == CODE_OPEN_CHOOSE_KURIR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val kurir = data.getSerializableExtra(AppConstant.DATA) as Staff
            if (kurir.phone_number == null) {
                toast(this,"Data not found")
            } else {
                //getPresenter()?.updateKurirupdateKurir(kurir)
            }
        }
        else if (requestCode == CODE_OPEN_CHOOSE_DISCOUNT && resultCode == Activity.RESULT_OK) {
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
        }
        else if (requestCode == CODE_OPEN_CHOOSE_NONTUNAI && resultCode == Activity.RESULT_OK) {
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
        else if (requestCode == codeOpenChooseTable && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this, "Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this, "Data not found")
                return
            }
            val table = data.getSerializableExtra(AppConstant.DATA) as Table
            if (table.id_table == null) {
                toast(this, "Data not found")
            } else {
                getPresenter()?.updateTable(table)
            }
        }
    }

    override fun showMessage(code: Int, msg: String?) {
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

    override fun openChooseTable() {
        val intent = Intent(this, ChooseTableActivity::class.java)
        startActivityForResult(intent, codeOpenChooseTable)
    }

    override fun openChooseCustomer() {
        val intent = Intent(this, ChooseCustomerActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_CUSTOMER)
    }

    override fun openChooseKurir() {
        val intent = Intent(this, ChooseKurirActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_KURIR)
    }

    override fun openAddCustomer() {
        val intent = Intent(this, AddCustomerActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_ADD_CUSTOMER)
    }


    override fun openAddDiscount() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_ADD_DISCOUNT)
    }

    override fun openChooseNonTunai() {
        val intent = Intent(this, ChooseNonTunaiActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_NONTUNAI)
    }

    override fun openSuccessPage(id: String) {
        val intent = Intent(this, SuccessActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
    }

    override fun openPaymentNonTunai(url: String,id:String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(AppConstant.Webview.URL,url)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
    }


    override fun setCart(list: List<Cart>) {
        adapter.setItems(list)
    }

    override fun setTableName(data: Table?) {
        et_table.text = ""
        btn_delete_table.visibility = View.GONE
        data?.let {
            et_table.text = it.name_table
            btn_delete_table.visibility = View.VISIBLE
        }
    }

    override fun setPrice(list: List<Price>) {
        hideLoadingDialog()
        adapter2.setItems(list)
    }

    override fun updatePrice(nominal: String) {
        et_pay.setText(nominal)
        hideLoadingDialog()
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
            val value = et_pay.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")

            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()
        }
    }

    override fun getPayNon(): Double {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            val value = et_pay_non.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")

            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()

        }else{
            val nf: NumberFormat = NumberFormat.getInstance(Locale.GERMAN)
            val number: Double = nf.parse(et_pay_non.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")).toDouble()

            val value = et_pay_non.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")
            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()
        }
    }

    override fun getPayDebt(): Double {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            val value = et_pay_debt.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(",", "")

            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()

        }else{
            val nf: NumberFormat = NumberFormat.getInstance(Locale.GERMAN)
            val number: Double = nf.parse(et_pay_debt.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")).toDouble()

            val value = et_pay_debt.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")
            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setCashback(value: Double) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        when {
            value == 0.0 -> {
                hideShowCashback(View.GONE)
                //enableBtnBuy(true)
            }
            value < 0.0 -> {
                val ret = -1 * value
                hideShowCashback(View.VISIBLE)
                //enableBtnBuy(true)
                if(decimal=="No Decimal") {
                    tv_kembalian.text = "Change " + AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(ret)
                }else{
                    tv_kembalian.text = "Change " + AppConstant.CURRENCY.getCurrencyData() + ret
                }
                tv_kembalian.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
            }
            else -> {
                hideShowCashback(View.VISIBLE)
                //enableBtnBuy(false)
                if(decimal=="No Decimal") {
                    tv_kembalian.text = "Insufficient payment "  + AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(value)
                }else{
                    tv_kembalian.text = "Insufficient payment "  + AppConstant.CURRENCY.getCurrencyData() + value
                }
                tv_kembalian.setTextColor(ContextCompat.getColor(this, R.color.vermillion))
            }
        }
    }

    @SuppressLint("SetTextI18n", "ShowToast")
    override fun setCashNon(value: Double) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        when {
            value == 0.0 -> {
                hideShowNon(View.VISIBLE)
                tv_paynon.text = "0"
            }
            value < 0.0 -> {
                hideShowNon(View.VISIBLE)
                tv_paynon.text = "0"
            }
            else -> {
                hideShowNon(View.VISIBLE)
                if(decimal=="No Decimal") {
                    tv_paynon.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(value)
                }else{
                    tv_paynon.text = AppConstant.CURRENCY.getCurrencyData() + value
                }
            }
        }
    }



    @SuppressLint("SetTextI18n", "ShowToast")
    override fun setCashDebt(value: Double) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        when {
            value == 0.0 -> {
                hideShowDebt(View.VISIBLE)
                if(decimal=="No Decimal") {
                    tv_paydebt.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(value)
                }else{
                    tv_paydebt.text = AppConstant.CURRENCY.getCurrencyData() + value
                }
            }
            value < 0.0 -> {
                hideShowDebt(View.VISIBLE)
                tv_paydebt.text = "0"
            }
            else -> {
                hideShowDebt(View.VISIBLE)
                if(decimal=="No Decimal") {
                    tv_paydebt.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(value)
                }else{
                    tv_paydebt.text = AppConstant.CURRENCY.getCurrencyData() + value
                }
            }
        }
    }

    override fun hideShowDebt(value: Int) {
        tv_paydebt.visibility = value
    }

    override fun hideShowNon(value: Int) {
        tv_paynon.visibility = value
    }

    override fun hideShowCashback(value: Int) {
        tv_kembalian.visibility = value
    }

    override fun showTunaiView() {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
            ll_tables.visibility = View.GONE
        }else{
            ll_tables.visibility = View.VISIBLE
        }
        ll_tunai.visibility = View.VISIBLE
        ll_non_tunai.visibility = View.GONE
        ll_debt.visibility = View.GONE
        ll_cash.visibility = View.GONE
        ll_cashdebt.visibility = View.GONE
        ll_hutang.visibility = View.GONE
        //ll_nontunai.visibility = View.GONE
        // ll_nocard.visibility = View.GONE
        et_pay.visibility = View.VISIBLE
        tv_paynon.visibility = View.GONE
        ll_discount.visibility = View.VISIBLE
        getPresenter()?.countCashback()
    }

    override fun showNonTunaiView() {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
            ll_tables.visibility = View.GONE
        }else{
            ll_tables.visibility = View.VISIBLE
        }
        ll_tunai.visibility = View.GONE
        ll_non_tunai.visibility = View.VISIBLE
        ll_debt.visibility = View.GONE
        ll_cash.visibility = View.VISIBLE
        ll_cashdebt.visibility = View.GONE
        ll_hutang.visibility = View.GONE
      //  ll_nontunai.visibility = View.VISIBLE
        //  ll_nocard.visibility = View.VISIBLE
        et_pay.visibility = View.GONE
        et_pay_non.visibility = View.VISIBLE
        tv_pay_non.visibility = View.VISIBLE
        tv_kembalian.visibility = View.GONE
        ll_discount.visibility = View.VISIBLE
    }

    override fun showPiutangView() {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store" || typestore=="Service products" || typestore=="Healthcare") {
            ll_tables.visibility = View.GONE
        }else{
            ll_tables.visibility = View.VISIBLE
        }
        ll_tunai.visibility = View.GONE
        ll_debt.visibility = View.VISIBLE
        tv_pay_debt.visibility = View.VISIBLE
        ll_non_tunai.visibility = View.GONE
        ll_cash.visibility = View.GONE
        ll_cashdebt.visibility = View.VISIBLE
        ll_hutang.visibility = View.VISIBLE
        // ll_nocard.visibility = View.GONE
        // ll_nontunai.visibility = View.GONE
        ll_discount.visibility = View.VISIBLE
    }

    override fun showPointView() {

        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = this.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    ll_tunai.visibility = View.GONE
                    ll_non_tunai.visibility = View.GONE
                    ll_debt.visibility = View.GONE
                    ll_hutang.visibility = View.GONE
                    //  ll_nontunai.visibility = View.GONE
                    //   ll_nocard.visibility = View.GONE
                    et_pay.visibility = View.GONE
                    tv_pay_non.visibility = View.GONE
                    tv_pay_debt.visibility = View.GONE
                    tv_kembalian.visibility = View.GONE
                    tv_paynon.visibility = View.GONE
                    ll_discount.visibility = View.VISIBLE
                }else{
                    Toast.makeText(this,"Not Connected", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Not Connected", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Not Connected", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setCustomerName(data: Customer?) {
        et_customer.text = ""
        btn_delete_customer.visibility = View.GONE
        data?.let {
            et_customer.text = it.name_customer
            btn_delete_customer.visibility = View.VISIBLE
        }
    }

    override fun openChooseDiscount() {
        val intent = Intent(this, ChooseDiscountActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_DISCOUNT)
    }

    @SuppressLint("SetTextI18n")
    override fun setDetailText(subtotal:Double,discount:Double?,service:Double?,tax:Double?,total:Double) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            tv_subtotal.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(subtotal)
            tv_total.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(total)

            val name:String = Helper.convertToCurrency(total)
            et_pay_non.setText(name)
            et_pay_debt.setText(name)
        }else{
            tv_subtotal.text = AppConstant.CURRENCY.getCurrencyData() + subtotal
            tv_total.text = AppConstant.CURRENCY.getCurrencyData() + total

            val name:String = total.toString()
            et_pay_non.setText(name)
            et_pay_debt.setText(name)
        }

        ll_discount_total.visibility = View.GONE
        discount?.let {
            if(it > 0){
                ll_discount_total.visibility = View.VISIBLE
                if(decimal=="No Decimal") {
                    tv_discount.text = "-" + AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it)
                }else{
                    tv_discount.text = "-" + AppConstant.CURRENCY.getCurrencyData() + it
                }
            }
        }

        ll_service.visibility = View.GONE
        service?.let {
            if(it > 0){
                ll_service.visibility = View.VISIBLE
                if(decimal=="No Decimal") {
                    tv_service.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it)
                }else{
                    tv_service.text = AppConstant.CURRENCY.getCurrencyData() + it
                }
            }
        }

        ll_tax.visibility = View.GONE
        tax?.let {
            if(it > 0){
                ll_tax.visibility = View.VISIBLE
                if(decimal=="No Decimal") {
                    tv_tax.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it)
                }else{
                    tv_tax.text = AppConstant.CURRENCY.getCurrencyData() + it
                }
            }
        }
    }

    override fun setDiscount(data: Discount?) {
        et_discount.text = ""
        btn_delete_discount.visibility = View.GONE
        data?.let {
            et_discount.text = it.name_discount
            btn_delete_discount.visibility = View.VISIBLE
        }
    }

    override fun setNonTunai(data: NonTunai?) {
        //et_payment.text = ""
        data?.let {
          //  et_payment.text = it.name_link
        }
    }

    override fun openSingleDatePickerDialog(selected: CalendarDay?, minDate: LocalDate?, maxDate: LocalDate?, type: Int) {
        val dateDialog = SingleDateDialog.newInstance()
        dateDialog.setData(selected,minDate,maxDate,-1)
        dateDialog.show(this.supportFragmentManager, SingleDateDialog.TAG)
    }

    override fun onDateClicked(selected: CalendarDay?, type: Int) {
        getPresenter()?.setSelectedDate(selected)
        if (selected == null) {
            et_date.text = ""
        } else {
            et_date.text = Helper.getDateFormat(this, selected.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
        }
    }

}
