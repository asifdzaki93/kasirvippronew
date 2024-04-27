package id.kasirvippro.android.feature.manage.packages.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.orderProduct.ActivitySubCategory
import id.kasirvippro.android.feature.dialog.CartCountDialog
import id.kasirvippro.android.feature.scan.ScanCodeActivity
import id.kasirvippro.android.feature.choose.productPackages.ChooseProductPackagesActivity
import id.kasirvippro.android.feature.choose.store.ChooseStoreActivity
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.feature.history.kulakan.KulakanFragment
import id.kasirvippro.android.feature.order.main.OrderActivity
import id.kasirvippro.android.models.packages.Packages
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.ui.NumberTextWatcher
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_packages_add.btn_bayar
import kotlinx.android.synthetic.main.activity_packages_add.et_name
import kotlinx.android.synthetic.main.activity_packages_add.et_price
import kotlinx.android.synthetic.main.activity_packages_add.ll_content
import kotlinx.android.synthetic.main.activity_packages_add.ll_error
import kotlinx.android.synthetic.main.activity_packages_add.rv_list
import kotlinx.android.synthetic.main.activity_packages_add.tv_error
import java.util.regex.PatternSyntaxException


class AddPackagesActivity : BaseActivity<AddPackagesPresenter, AddPackagesContract.View>(),
    AddPackagesContract.View, CartCountDialog.Listener {

    private val TAG = KulakanFragment::class.java.simpleName
    private val codeOpenChooseProduct = 1002
    private val CODE_OPEN_SCAN = 1001
    private val CODE_OPEN_CHOOSE_PRODUCT = 1002
    private val CODE_OPEN_CHOOSE_STORE = 1005

    private val adapter = AddPackagesAdapter()

    override fun createPresenter(): AddPackagesPresenter {
        return AddPackagesPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_packages_add
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
            title = getString(R.string.lbl_produk_packages_title)
            elevation = 0f
        }

    }

    private fun renderView() {
        val data = intent.getSerializableExtra(AppConstant.DATA) as Packages
        et_name.setText(data.name_packages)
        et_price.setText(Helper.convertToCurrency(data.price!!))

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        adapter.callback = object :AddPackagesAdapter.ItemClickCallback {

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
        val decimal = AppConstant.DECIMAL.getDecimalData()
        btn_bayar.setOnClickListener {
            showLoadingDialog()
            if(decimal=="No Decimal") {
                val id = data.id_product
                val price = et_price.text.toString().trim().replace(",","")
                getPresenter()?.checkAdd(id!!, price)
            }else {
                val id = data.id_product
                val price = et_price.text.toString().trim()
                getPresenter()?.checkAdd(id!!, price)
            }
        }

        if(decimal=="No Decimal") {
            et_price.addTextChangedListener(NumberTextWatcher(et_price))
        }else{
            et_price.inputFilterDecimal(maxDigitsIncludingPoint = 9, maxDecimalPlaces = 2)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scan_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_search -> openChooseProduct()
            R.id.action_scan -> getPresenter()?.onCheckScan()
            //R.id.action_add -> openAddManual("")
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
        val intent = if(OrderActivity.data.isEmpty()) Intent(this, ChooseProductPackagesActivity::class.java)
        else Intent(this, ActivitySubCategory::class.java).putExtra("data", OrderActivity.data)
        startActivityForResult(intent, codeOpenChooseProduct)
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
        builder.setMessage("Success in adding Packages")
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            restartMainActivity()
        }
        builder.show()
    }

    override fun setCartText(nominal: String) {
       // tv_total.text = nominal
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

    override fun setStoreName(data: Store?) {
        //et_store.text = ""
        data?.let {
         //   et_store.text = it.name_store
        }
    }

    override fun openChooseStore() {
        val intent = Intent(this, ChooseStoreActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_STORE)
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
        } else if (requestCode == CODE_OPEN_CHOOSE_STORE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this, "Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this, "Data not found")
                return
            }
            val store = data.getSerializableExtra(AppConstant.DATA) as Store
            if (store.id_store == null) {
                toast(this, "Data not found")
            } else {
                getPresenter()?.updateStore(store)
            }
        }
    }

    override fun openHistoryKulakan() {
        val intent = Intent(this, KulakanFragment::class.java)
        intent.putExtra(AppConstant.CODE, AppConstant.Code.CODE_TRANSACTION_SUPPLIER)
        startActivity(intent)
    }


}
