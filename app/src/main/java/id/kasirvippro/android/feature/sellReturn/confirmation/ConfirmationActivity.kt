package id.kasirvippro.android.feature.sellReturn.confirmation

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.transaction.successReturn.SuccessActivity
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_sell_confirmationreturn.*
import kotlinx.android.synthetic.main.activity_sell_confirmationreturn.btn_bayar
import kotlinx.android.synthetic.main.activity_sell_confirmationreturn.et_pay
import kotlinx.android.synthetic.main.activity_sell_confirmationreturn.ll_tunai
import kotlinx.android.synthetic.main.activity_sell_confirmationreturn.rg_payment
import kotlinx.android.synthetic.main.activity_sell_confirmationreturn.rv_list
import kotlinx.android.synthetic.main.activity_sell_confirmationreturn.tv_kembalian
import kotlinx.android.synthetic.main.activity_sell_confirmationreturn.tv_subtotal
import kotlinx.android.synthetic.main.activity_sell_confirmationreturn.tv_total
import java.text.NumberFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

class ConfirmationActivity : BaseActivity<ConfirmationPresenter,ConfirmationContract.View>(), ConfirmationContract.View{

    private val TAG = ConfirmationActivity::class.java.simpleName

    private val CODE_OPEN_CHOOSE_NONTUNAI = 1003
    private val CODE_OPEN_CHOOSE_DISCOUNT = 1004
    private val CODE_OPEN_CHOOSE_CUSTOMER = 1005
    private val CODE_OPEN_ADD_CUSTOMER = 1006

    private val adapter = ConfirmationAdapter()

    override fun createPresenter(): ConfirmationPresenter {
        return ConfirmationPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_sell_confirmationreturn
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Confirmation"
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

        if(decimal=="No Decimal") {
            et_pay.addTextChangedListener(NumberTextWatcher(et_pay))
        }else{
            et_pay.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
        }

        rg_payment.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_tunai -> showTunaiView()
            }
        }
        rg_payment.check(R.id.rb_tunai)


        btn_bayar.setOnClickListener {
            showLoadingDialog()
            val note = et_invoice.text.toString().trim()
            when (rg_payment.checkedRadioButtonId) {
                R.id.rb_tunai -> {
                    getPresenter()?.checkTunai(note)
                }
            }
        }
    }

	fun EditText.inputFilterDecimal(
        // maximum digits including point and without decimal places
        maxDigitsIncludingPoint: Int,
        maxDecimalPlaces: Int // maximum decimal places

    ){

        try {

            filters = arrayOf<InputFilter>(

                DecimalDigitsInputFilter(maxDigitsIncludingPoint, maxDecimalPlaces)

            )

        }catch (e: PatternSyntaxException){

            isEnabled = false

            hint = e.message

        }

    }



// class to decimal digits input filter

    class DecimalDigitsInputFilter(
        maxDigitsIncludingPoint: Int, maxDecimalPlaces: Int
    ) : InputFilter {
        private val pattern: Pattern = Pattern.compile(
            "[0-9]{0," + (maxDigitsIncludingPoint - 1) + "}+((\\.[0-9]{0,"
                    + (maxDecimalPlaces - 1) + "})?)||(\\.)?"
        )

        override fun filter(

            p0: CharSequence?, p1: Int, p2: Int, p3: Spanned?, p4: Int, p5: Int

        ): CharSequence? {

            p3?.apply {

                val matcher: Matcher = pattern.matcher(p3)

                return if (!matcher.matches()) "" else null

            }

            return null

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
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


    override fun openSuccessPage(id: String) {
        val intent = Intent(this, SuccessActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
    }


    override fun setCart(list: List<Cart>) {
        adapter.setItems(list)
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



    override fun hideShowCashback(value: Int) {
        tv_kembalian.visibility = value
    }

    override fun showTunaiView() {
        ll_tunai.visibility = View.VISIBLE
        et_pay.visibility = View.VISIBLE
        getPresenter()?.countCashback()
    }

    @SuppressLint("SetTextI18n")
    override fun setDetailText(subtotal:Double,total:Double) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            tv_subtotal.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(subtotal)
            tv_total.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(total)

        }else{
            val subtotalrounded = String.format("%.2f", subtotal) // rounds to 3 decimal places
            val totalrounded = String.format("%.2f", total) // rounds to 3 decimal places
            tv_subtotal.text = AppConstant.CURRENCY.getCurrencyData() + subtotalrounded
            tv_total.text = AppConstant.CURRENCY.getCurrencyData() + totalrounded
        }
    }
}
