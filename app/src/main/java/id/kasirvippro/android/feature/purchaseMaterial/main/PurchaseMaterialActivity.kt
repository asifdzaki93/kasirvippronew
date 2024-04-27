package id.kasirvippro.android.feature.purchaseMaterial.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.CartRawCountDialog
import id.kasirvippro.android.feature.dialog.SingleDateDialog
import id.kasirvippro.android.feature.choose.rawMaterial.ChooseRawMaterialActivity
import id.kasirvippro.android.models.cartRaw.CartRaw
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_kulakan.*
import org.threeten.bp.LocalDate
import id.kasirvippro.android.feature.history.kulakan.KulakanFragment
import id.kasirvippro.android.models.rawMaterial.RawMaterial
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
import java.text.NumberFormat
import java.util.*


class PurchaseMaterialActivity : BaseActivity<PurchaseMaterialPresenter, PurchaseMaterialContract.View>(),
    PurchaseMaterialContract.View,
    SingleDateDialog.Listener, CartRawCountDialog.Listener {

    private val TAG = KulakanFragment::class.java.simpleName
    private val CODE_OPEN_CHOOSE_PRODUCT = 1002

    private val adapter = PurchaseMaterialAdapter()

    override fun createPresenter(): PurchaseMaterialPresenter {
        return PurchaseMaterialPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_purchase_raw
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
        //openChooseProduct()
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_add_material)
            elevation = 0f
        }

    }

    private fun renderView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        adapter.callback = object : PurchaseMaterialAdapter.ItemClickCallback {
            override fun onCountDialog(data: CartRaw, position: Int) {
                openCountDialog(data, position)
            }

            override fun onDecrease(data: CartRaw, position: Int) {
                getPresenter()?.decreaseCart(data, position)
            }

            override fun onIncrease(data: CartRaw, position: Int) {
                getPresenter()?.increaseCart(data, position)
            }

            override fun onDelete(data: CartRaw, position: Int) {
                getPresenter()?.deleteCart(data, position)

            }
        }

        btn_bayar.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.checkTunai()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_add -> openChooseProduct()
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
    }

    override fun openChooseProduct() {
        val intent = Intent(this, ChooseRawMaterialActivity::class.java)
        intent.putExtra(AppConstant.DATA, false)
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
        builder.setMessage("Your shopping was successful!")
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            restartMainActivity()
        }
        builder.show()
    }

    override fun setCartText(nominal: String) {
        tv_total.text = nominal
      //  tv_subtotal.text = nominal
    }

    override fun addCart(data: CartRaw) {
        adapter.addItem(data)
    }

    override fun updateCart(cart: CartRaw, position: Int) {
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

    override fun setSupplierName(data: Supplier?) {
        et_supplier.text = ""
        btn_delete_supplier.visibility = View.GONE
        data?.let {
            et_supplier.text = it.name_supplier
            btn_delete_supplier.visibility = View.VISIBLE
        }
    }

    override fun openChooseSupplier() {
    }

    override fun openSingleDatePickerDialog(selected: CalendarDay?) {
        val dateDialog = SingleDateDialog.newInstance()
        val now = LocalDate.now()
        dateDialog.setData(selected, now, null, -1)
        dateDialog.show(this.supportFragmentManager, SingleDateDialog.TAG)
    }

    override fun openCountDialog(selected: CartRaw, pos: Int) {
        val dialog = CartRawCountDialog.newInstance()
        dialog.setData(selected, pos, false)
        dialog.show(this.supportFragmentManager, CartRawCountDialog.TAG)
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

    override fun onCountSaved(selected: CartRaw, pos: Int) {
        getPresenter()?.updateCart(selected, pos)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_OPEN_CHOOSE_PRODUCT && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this, "Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this, "Data not found")
                return
            }
            val rawmaterial = data.getSerializableExtra(AppConstant.DATA) as RawMaterial
            if (rawmaterial.id_raw_material == null) {
                toast(this, "Data not found")
            } else {
                getPresenter()?.checkCart(rawmaterial)
            }
        }
    }

    override fun openHistoryKulakan() {
        val intent = Intent(this, KulakanFragment::class.java)
        intent.putExtra(AppConstant.CODE, AppConstant.Code.CODE_TRANSACTION_SUPPLIER)
        startActivity(intent)
    }


}
