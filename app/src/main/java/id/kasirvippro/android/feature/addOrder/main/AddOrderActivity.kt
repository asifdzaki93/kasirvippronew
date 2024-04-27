package id.kasirvippro.android.feature.addOrder.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.addProduct.ActivitySubCategory
import id.kasirvippro.android.feature.choose.productAdd.ChooseProductActivity
import id.kasirvippro.android.feature.dialog.CartCountDialog
import id.kasirvippro.android.feature.dialog.NoteDialog
import id.kasirvippro.android.feature.dialog.SingleDateDialog
import id.kasirvippro.android.feature.scan.ScanCodeActivity
import id.kasirvippro.android.feature.transaction.successadd.SuccessAddActivity
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_kulakan.btn_bayar
import kotlinx.android.synthetic.main.activity_kulakan.btn_date
import kotlinx.android.synthetic.main.activity_kulakan.et_date
import kotlinx.android.synthetic.main.activity_kulakan.ll_content
import kotlinx.android.synthetic.main.activity_kulakan.ll_error
import kotlinx.android.synthetic.main.activity_kulakan.ll_hutang
import kotlinx.android.synthetic.main.activity_kulakan.rg_payment
import kotlinx.android.synthetic.main.activity_kulakan.rv_list
import kotlinx.android.synthetic.main.activity_kulakan.tv_error
import kotlinx.android.synthetic.main.activity_kulakan.tv_subtotal
import kotlinx.android.synthetic.main.activity_kulakan.tv_total
import org.threeten.bp.LocalDate
import java.text.NumberFormat
import java.util.*


class AddOrderActivity : BaseActivity<AddOrderPresenter, AddOrderContract.View>(),
    AddOrderContract.View,
    SingleDateDialog.Listener, CartCountDialog.Listener,NoteDialog.Listener {

    companion object { var data = "" }


    private val codeOpenScan = 1001
    private val codeOpenChooseProduct = 1002

    private val adapter = AddOrderAdapter()

    override fun createPresenter(): AddOrderPresenter {
        return AddOrderPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_add_order
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
        openChooseProduct()
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.new_add_order)
            elevation = 0f
        }

    }

    private fun renderView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        val invoice = getIntent().getStringExtra("invoice")

        adapter.callback = object : AddOrderAdapter.ItemClickCallback {
            override fun onCountDialog(data: Cart, position: Int) {
                openCountDialog(data, position)
            }

            override fun onNote(data: Cart, position: Int) {
                openNoteDialog(data,position)
            }

            override fun onDecrease(data: Cart, position: Int) {
                getPresenter()?.decreaseCart(data, position)
            }

            override fun onIncrease(data: Cart, position: Int) {
                getPresenter()?.increaseCart(data, position)
            }

            override fun onDelete(data: Cart, position: Int) {
                getPresenter()?.deleteCart(data, position)

            }


        }

        rg_payment.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.rb_tunai -> showTunaiView()
                R.id.rb_piutang -> showPiutangView()
            }
        }

        rg_payment.check(R.id.rb_tunai)


        btn_date.setOnClickListener {
            openSingleDatePickerDialog(getPresenter()?.getSelectedDate())
        }

        btn_bayar.setOnClickListener {
            showLoadingDialog()
            when (rg_payment.checkedRadioButtonId) {
                R.id.rb_tunai -> {
                    getPresenter()?.checkTunai()
                }
                R.id.rb_piutang -> {
                    getPresenter()?.checkPiutang()
                }
            }
        }



//        Log.d("no_invoice", invoice)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scan_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.action_search -> openChooseProduct()
            R.id.action_scan -> getPresenter()?.onCheckScan()
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
                    toast(this, it)
                }
            }

        }
    }

    override fun openScanPage() {
        val intent = Intent(this, ScanCodeActivity::class.java)
        startActivityForResult(intent, codeOpenScan)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        openChooseProduct()
    }
    //passing to new Product
    override fun openChooseProduct() {
        val intent = if(AddOrderActivity.data.isEmpty()) Intent(this, ChooseProductActivity::class.java)
        else Intent(this, ActivitySubCategory::class.java).putExtra("data", AddOrderActivity.data)
        startActivityForResult(intent, codeOpenChooseProduct)
    }

    fun openChooseProductData() {
        val intent = Intent(this, ChooseProductActivity::class.java)
        startActivityForResult(intent, codeOpenChooseProduct)
    }

    override fun openNoteDialog(selected: Cart, pos: Int) {
        val noteDialog = NoteDialog.newInstance()
        noteDialog.setData(selected,pos)
        noteDialog.show(this.supportFragmentManager, NoteDialog.TAG)
    }

    override fun getTotalValue(): Double {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            val value = tv_total.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(".", "")

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

    override fun getInVoice(): String? {
        val invoice = getIntent().getStringExtra("invoice")
        return invoice
    }

    override fun showContentView() {
        ll_content.visibility = View.VISIBLE
        ll_error.visibility = View.GONE
    }

    override fun showErrorView(err: String) {
        ll_content.visibility = View.GONE
        ll_error.visibility = View.VISIBLE
        tv_error.text = err
    }

    override fun openSuccessPage(id: String) {
        val intent = Intent(this, SuccessAddActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)

    }

    override fun setCartText(nominal: String) {
        tv_total.text = nominal
        tv_subtotal.text = nominal
    }

    override fun addCart(data: Cart) {
        adapter.addItem(data)
    }

    override fun updateCart(cart: Cart, position: Int) {
        adapter.updateItem(cart, position)
    }

    override fun deleteCart(position: Int) {
        adapter.deleteItem(position)
    }

    override fun showTunaiView() {
        ll_hutang.visibility = View.GONE
    }


    override fun showPiutangView() {
        ll_hutang.visibility = View.VISIBLE
    }



    override fun openSingleDatePickerDialog(selected: CalendarDay?) {
        val dateDialog = SingleDateDialog.newInstance()
        val now = LocalDate.now()
        dateDialog.setData(selected, now, null, -1)
        dateDialog.show(this.supportFragmentManager, SingleDateDialog.TAG)
    }

    override fun openCountDialog(selected: Cart, pos: Int) {
        val dialog = CartCountDialog.newInstance()
        dialog.setData(selected, pos, false)
        dialog.show(this.supportFragmentManager, CartCountDialog.TAG)
    }

    override fun onNoteSaved(selected: Cart, pos: Int) {
        getPresenter()?.updateCart(selected,pos)
    }


    override fun onDateClicked(selected: CalendarDay?, type: Int) {
        getPresenter()?.setSelectedDate(selected)
        if (selected == null) {
            et_date.text = ""
        } else {
            et_date.text =
                Helper.getDateFormat(this, selected.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
        }
    }

    override fun onCountSaved(selected: Cart, pos: Int) {
        getPresenter()?.updateCart(selected, pos)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == codeOpenScan && resultCode == Activity.RESULT_OK) {
            val code = data?.getStringExtra(AppConstant.DATA)
            if (!code.isNullOrBlank() && !code.isNullOrEmpty()) {
                showLoadingDialog()
                getPresenter()?.searchByBarcode(code)
            }

        } else if (requestCode == codeOpenChooseProduct && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this, "Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this, "Data not found")
                return
            }
            val product = data.getSerializableExtra(AppConstant.DATA) as Product
            if (product.id_product == null) {
                toast(this, "Data not found")
            } else {
                getPresenter()?.checkCart(product)
            }
        }
    }



}
