package id.kasirvippro.android.feature.label.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.newProduct.ActivitySubCategory
import id.kasirvippro.android.feature.dialog.CartCountDialog
import id.kasirvippro.android.feature.scan.ScanCodeActivity
import id.kasirvippro.android.feature.choose.product.ChooseProductContract
import id.kasirvippro.android.feature.choose.product.ChooseProductInteractor
import id.kasirvippro.android.feature.choose.productPurchase.ChooseProductPurchaseActivity
import id.kasirvippro.android.feature.dialog.AddOnDialog
import id.kasirvippro.android.feature.dialog.CartPriceDialog
import id.kasirvippro.android.feature.dialog.NoteDialog
import id.kasirvippro.android.feature.label.confirmation.ConfirmationLabelActivity
import id.kasirvippro.android.models.addOn.AddOn
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_label.*

class LabelActivity : BaseActivity<LabelPresenter,LabelContract.View>(), LabelContract.View,
    ChooseProductContract.InteractorOutput,
CartCountDialog.Listener,NoteDialog.Listener,AddOnDialog.Listener,CartPriceDialog.Listener{

    private var interactorProduct  = ChooseProductInteractor(this)

    private val productDialog = AddOnDialog.newInstance()

    companion object { var data = "" }

    private val TAG = LabelActivity::class.java.simpleName
    private val CODE_OPEN_SCAN = 1001
    private val CODE_OPEN_CHOOSE_PRODUCT = 1002

    private val adapter = LabelAdapter()

    override fun createPresenter(): LabelPresenter {
        return LabelPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_label
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
        openChooseProduct()
    }

    private fun getProduct(){
        var restModel = ProductRestModel(this)
        val check = intent.getBooleanExtra(AppConstant.DATA,true)
        var id = if(check){
            ""
        }
        else{
            "1"
        }
        interactorProduct.callGetProductsAPI(this,restModel,id,1)
    }
    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_sell)
            elevation = 0f
        }

    }

    private fun renderView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        adapter.callback = object : LabelAdapter.ItemClickCallback {
            override fun onCountDialog(data: Cart, position: Int) {
                openCountDialog(data,position)
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

            override fun onAddOnDialog(data: Cart, position: Int) {
                getPresenter()?.onCheckVariable(data, position)
            }
        }

        btn_bayar.setOnClickListener {
            getPresenter()?.checkCart()
        }

    }

    override fun openProducts(title: String, list: List<AddOn>, selectedp: AddOn?) {
        hideLoadingDialog()
        if (productDialog.dialog != null && productDialog.dialog!!.isShowing) {

        } else {
            productDialog.setData(title, 1, list, selectedp)
            productDialog.show(supportFragmentManager, "addon")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scan_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
            R.id.action_search -> openChooseProductData()
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
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val product = data.getSerializableExtra(AppConstant.DATA) as Product
            if (product.id_product == null) {
                toast(this,"Data not found")
            } else {
                getPresenter()?.checkCart(product)
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

    override fun openScanPage() {
        val intent = Intent(this, ScanCodeActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_SCAN)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        openChooseProduct()
    }
    //passing to new Product
    override fun openChooseProduct() {
        val intent = if(data.isEmpty()) Intent(this, ChooseProductPurchaseActivity::class.java)
        else Intent(this, ActivitySubCategory::class.java).putExtra("data", data)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_PRODUCT)
    }

    fun openChooseProductData() {
        val intent = Intent(this, ChooseProductPurchaseActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_PRODUCT)
    }

    override fun openSuccessPage(carts: HashMap<String,Cart>) {
        val intent = Intent(this, ConfirmationLabelActivity::class.java)
        intent.putExtra(AppConstant.DATA,carts)
        startActivity(intent)
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

    override fun setCartText(count: String, nominal: String) {
        tv_number.text = count
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

    override fun openNoteDialog(selected: Cart, pos: Int) {
        val noteDialog = NoteDialog.newInstance()
        noteDialog.setData(selected,pos)
        noteDialog.show(this.supportFragmentManager, NoteDialog.TAG)
    }

    override fun onNoteSaved(selected: Cart, pos: Int) {
        getPresenter()?.updateCart(selected,pos)
    }

    override fun openCountDialog(selected: Cart, pos: Int) {
        val dialog = CartCountDialog.newInstance()
        dialog.setData(selected,pos,true)
        dialog.show(this.supportFragmentManager, CartCountDialog.TAG)
    }


    /*override fun openAddOnDialog(selected: Cart, pos: Int) {
        val pricedialog = AddOnDialog.newInstance()
        pricedialog.setData(selected,pos,true)
        pricedialog.show(this.supportFragmentManager, AddOnDialog.TAG)
    }*/


    override fun onCountSaved(selected: Cart, pos: Int) {
        getPresenter()?.updateCart(selected,pos)
    }

    override fun onSuccessGetProducts(list: List<Product>) {
    }

    override fun onSuccessGetProductsVariable(list: List<Product>) {
        TODO("Not yet implemented")
    }
    override fun onFailedAPI(code: Int, msg: String) {
    }

    override fun openPriceDialog(selected: Cart, pos: Int) {
        val pricedialog = CartPriceDialog.newInstance()
        pricedialog.setData(selected,pos,true)
        pricedialog.show(this.supportFragmentManager, CartPriceDialog.TAG)
    }

    override fun onPriceSaved(selected: Cart, pos: Int) {
        getPresenter()?.updateCart(selected,pos)
    }

    override fun onItemClicked(data: AddOn, position: Int) {
        getPresenter()?.setSelectedProduct(data,position)
    }

    override fun onSelected(selected: Cart, pos: Int) {
        getPresenter()?.updateCart(selected,pos)
    }


}
