package id.kasirvippro.android.feature.addOn.recipe.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.customer.ChooseCustomerActivity
import id.kasirvippro.android.feature.choose.orderProduct.ActivitySubCategory
import id.kasirvippro.android.feature.dialog.CartCountDialog
import id.kasirvippro.android.feature.scan.ScanCodeActivity
import id.kasirvippro.android.feature.choose.productPurchase.ChooseProductPurchaseActivity
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_recipe.*
import id.kasirvippro.android.feature.history.kulakan.KulakanFragment
import id.kasirvippro.android.feature.order.main.OrderActivity
import id.kasirvippro.android.models.customer.Customer
import kotlinx.android.synthetic.main.activity_recipe.btn_bayar
import kotlinx.android.synthetic.main.activity_recipe.et_customer
import kotlinx.android.synthetic.main.activity_recipe.ll_content
import kotlinx.android.synthetic.main.activity_recipe.ll_error
import kotlinx.android.synthetic.main.activity_recipe.rg_payment
import kotlinx.android.synthetic.main.activity_recipe.rv_list
import kotlinx.android.synthetic.main.activity_recipe.tv_error
import kotlinx.android.synthetic.main.activity_recipe.tv_total
import java.text.NumberFormat
import java.util.*


class RecipeActivity : BaseActivity<RecipePresenter, RecipeContract.View>(),
    RecipeContract.View, CartCountDialog.Listener {

    private val TAG = KulakanFragment::class.java.simpleName

    private val CODE_OPEN_SCAN = 1001
    private val CODE_OPEN_CHOOSE_PRODUCT = 1002
    private val CODE_OPEN_CHOOSE_PATIENT = 1005

    private val adapter = RecipeAdapter()

    override fun createPresenter(): RecipePresenter {
        return RecipePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_recipe
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
            title = getString(R.string.lbl_print_receipt_title)
            elevation = 0f
        }

    }

    private fun renderView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        adapter.callback = object :RecipeAdapter.ItemClickCallback {
            override fun onCountDialog(data: Cart, position: Int) {
                openCountDialog(data, position)
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
            }
        }

        rg_payment.check(R.id.rb_tunai)

        et_customer.setOnClickListener {
            openChooseCustomer()
        }

        btn_bayar.setOnClickListener {
            showLoadingDialog()
            val complaint = et_complaint.text.toString().trim()
            val advice = et_advice.text.toString().trim()
            when (rg_payment.checkedRadioButtonId) {
                R.id.rb_tunai -> {
                    getPresenter()?.checkTunai(complaint,advice)
                }
            }
        }
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
            //R.id.action_add -> openAddManual("")
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
        startActivityForResult(intent, CODE_OPEN_SCAN)
    }

    override fun openChooseProduct() {
        val intent = if(OrderActivity.data.isEmpty()) Intent(this, ChooseProductPurchaseActivity::class.java)
        else Intent(this, ActivitySubCategory::class.java).putExtra("data", OrderActivity.data)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_PRODUCT)
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
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Success")
        builder.setMessage("Drug Prescription successfully created, data automatically enters the pharmacy")
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            restartMainActivity()
        }
        builder.show()
    }

    override fun setCartText(nominal: String) {
        tv_total.text = nominal
       // tv_subtotal.text = nominal
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
     //   ll_hutang.visibility = View.GONE
    }

    override fun setCustomerName(data: Customer?) {
        et_customer.text = ""
        data?.let {
            et_customer.text = it.name_customer
        }
    }

    override fun openChooseCustomer() {
        val intent = Intent(this, ChooseCustomerActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_PATIENT)
    }

    override fun openCountDialog(selected: Cart, pos: Int) {
        val dialog = CartCountDialog.newInstance()
        dialog.setData(selected, pos, false)
        dialog.show(this.supportFragmentManager, CartCountDialog.TAG)
    }

    override fun onCountSaved(selected: Cart, pos: Int) {
        getPresenter()?.updateCart(selected, pos)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_OPEN_SCAN && resultCode == Activity.RESULT_OK) {
            val code = data?.getStringExtra(AppConstant.DATA)
            if (!code.isNullOrBlank() && !code.isNullOrEmpty()) {
                showLoadingDialog()
                getPresenter()?.searchByBarcode(code)
            }

        } else if (requestCode == CODE_OPEN_CHOOSE_PRODUCT && resultCode == Activity.RESULT_OK) {
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
        } else if (requestCode == CODE_OPEN_CHOOSE_PATIENT && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this, "Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this, "Data not found")
                return
            }
            val customer = data.getSerializableExtra(AppConstant.DATA) as Customer
            if (customer.id_customer == null) {
                toast(this, "Data not found")
            } else {
                getPresenter()?.updateCustomer(customer)
            }
        }
    }

    override fun openHistoryKulakan() {
        val intent = Intent(this, KulakanFragment::class.java)
        intent.putExtra(AppConstant.CODE, AppConstant.Code.CODE_TRANSACTION_SUPPLIER)
        startActivity(intent)
    }


}
