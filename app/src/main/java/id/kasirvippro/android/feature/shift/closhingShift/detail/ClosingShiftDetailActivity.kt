package id.kasirvippro.android.feature.shift.closingShift.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.feature.printer.PrinterActivity
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_edit_closeshift.*
import kotlinx.android.synthetic.main.activity_edit_closeshift.btn_save
import kotlinx.android.synthetic.main.activity_edit_closeshift.btn_printer
import kotlinx.android.synthetic.main.activity_edit_closeshift.btn_share
import kotlinx.android.synthetic.main.activity_edit_closeshift.ll_button

class ClosingShiftDetailActivity : BaseActivity<ClosingShiftDetailPresenter, ClosingShiftDetailContract.View>(), ClosingShiftDetailContract.View {


    override fun createPresenter(): ClosingShiftDetailPresenter {
        return ClosingShiftDetailPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_closeshift
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }



    private fun renderView(){
        btn_share.setOnClickListener {
            getPresenter()?.onCheckShare()
        }

        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val pemasukan = et_pemasukan.text.toString().trim().replace(",","")
            getPresenter()?.onCheck(pemasukan)
        }
        et_pemasukan.addTextChangedListener(NumberTextWatcher(et_pemasukan))

        btn_printer.setOnClickListener {
            getPresenter()?.onCheckBluetooth()
        }





    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.initial)
            elevation = 0f
        }

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

    override fun setData(
        cash_actual:String?,
        name_cashier:String?,
        initial_date:String?,
        cash_awal:String?,
        sales_debt:String?,
        sales_return:String?,
        variance:String?,
        ppn:String?,
        sc:String?,
        sales_non_cash:String?,
        shift:String?,
        flag:String?,
        sales_cash:String?,
        status:String?,
        total_sales:String?
    ) {
        cash_actual?.let {
            et_cash_actual.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(it)}"
        }

        name_cashier?.let {
            et_name_cashier.setText(it)
        }
        initial_date?.let {
            et_initial_date.setText(it)
        }
        cash_awal?.let {
            et_cash_awal.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(it)}"
        }

        sales_debt?.let {
            et_sales_debt.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(it)}"
        }
        sales_return?.let {
            et_sales_return.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(it)}"
        }
        variance?.let {
            et_variance.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(it)}"
        }
        ppn?.let {
            et_ppn.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(it)}"
        }
        sc?.let {
            et_sc.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(it)}"
        }
        sales_non_cash?.let {
            et_sales_noncash.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(it)}"
        }
        shift?.let {
            et_shift.setText(it)
        }
        status?.let {
            et_status.setText(it)
        }
        sales_cash?.let {
            et_sales_computer.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(it)}"
        }

        ll_pemasukan.visibility = View.GONE
        et_pemasukan?.let {
            ll_pemasukan.visibility = View.VISIBLE
        }

        total_sales?.let {
            et_total_sales.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(it)}"
        }




        if(flag=="S"){
            ll_pemasukan.visibility = View.GONE
            btn_printer.visibility = View.VISIBLE
            btn_share.visibility = View.VISIBLE
            et_status.setTextColor(ContextCompat.getColor(this,R.color.status_success_text))
        }
        else if(flag=="I"){
            ll_pemasukan.visibility = View.VISIBLE
            btn_printer.visibility = View.GONE
            btn_share.visibility = View.GONE
            ll_button.visibility = View.GONE
            et_status.setTextColor(ContextCompat.getColor(this,R.color.status_credit_text))
        }else if(flag=="C"){
            ll_pemasukan.visibility = View.GONE
            btn_printer.visibility = View.VISIBLE
            btn_share.visibility = View.VISIBLE
            et_status.setTextColor(ContextCompat.getColor(this,R.color.status_cancel_text))
        }



    }

    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }

    override fun getParentLayout(): NestedScrollView {
        return ns_scrolls
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun openPrinterPage() {
        val intent = Intent(this, PrinterActivity::class.java)
        intent.putExtra(AppConstant.DATA,getPresenter()?.getDataStruk())
        startActivity(intent)
    }



}
