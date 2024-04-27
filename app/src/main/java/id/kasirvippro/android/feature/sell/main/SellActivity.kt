package id.kasirvippro.android.feature.sell.main

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.newProduct.ActivitySubCategory
import id.kasirvippro.android.feature.choose.customer.ChooseCustomerContract
import id.kasirvippro.android.feature.choose.customer.ChooseCustomertInteractor
import id.kasirvippro.android.feature.choose.discount.ChooseDiscountContract
import id.kasirvippro.android.feature.choose.discount.ChooseDiscountInteractor
import id.kasirvippro.android.feature.scan.ScanCodeActivity
import id.kasirvippro.android.feature.choose.product.ChooseProductActivity
import id.kasirvippro.android.feature.choose.product.ChooseProductContract
import id.kasirvippro.android.feature.choose.product.ChooseProductInteractor
import id.kasirvippro.android.feature.dialog.*
import id.kasirvippro.android.feature.sell.confirmation.ConfirmationActivity
import id.kasirvippro.android.models.addOn.AddOn
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.discount.DiscountRestModel
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_sell.*

class SellActivity : BaseActivity<SellPresenter,SellContract.View>(), SellContract.View,
    ChooseProductContract.InteractorOutput, ChooseDiscountContract.InteractorOutput,
    ChooseCustomerContract.InteractorOutput,
CartCountDialog.Listener,NoteDialog.Listener,AddOnDialog.Listener,CartPriceDialog.Listener, BottomProductDialog.Listener{

    private var interactorProduct  = ChooseProductInteractor(this)
    private var interactorDiscount  = ChooseDiscountInteractor(this)
    private var interactorCustomer  = ChooseCustomertInteractor(this)

    private val productDialog = BottomProductDialog.newInstance()
    private var recyclerVisiblePosition = 0

    companion object { var data = "" }

    private val TAG = SellActivity::class.java.simpleName
    private val CODE_OPEN_SCAN = 1001
    private val CODE_OPEN_CHOOSE_PRODUCT = 1002

    private val adapter = SellAdapter()
    private val adapter2 = ProductAdapter()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun createPresenter(): SellPresenter {
        return SellPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_sell
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()

        //getProduct();
        //getDiscount();
        //getCustomer();
       // openChooseProduct()
    }

    private fun getProduct(){
        val restModel = ProductRestModel(this)
        val check = intent.getBooleanExtra(AppConstant.DATA,true)
        val id = if(check){
            ""
        }
        else{
            "1"
        }
        interactorProduct.callGetProductsAPI(this,restModel,id,1)
    }

    private fun getDiscount(){
        val restModel = DiscountRestModel(this)
        interactorDiscount.callGetsAPI(this,restModel)
    }

    private fun getCustomer(){
        val restModel = CustomerRestModel(this)
        interactorCustomer.callGetDataAPI(this,restModel)
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
        val rotation = this.getResources().getConfiguration().orientation;
        val metrics = this.resources.displayMetrics
        val width = metrics.widthPixels
        val height = metrics.heightPixels

        val x = Math.pow((metrics.widthPixels / metrics.xdpi).toDouble(), 2.0)
        val y = Math.pow((metrics.heightPixels / metrics.ydpi).toDouble(), 2.0)
        val screenInches = Math.sqrt(x + y)
        val hasil = +Math.round(screenInches)
        // Toast.makeText(this,"Screen inches " + hasil + ".",Toast.LENGTH_SHORT).show()

        if(hasil >= 7) {
            if (rotation == Configuration.ORIENTATION_PORTRAIT) {
                ll_error.visibility = View.VISIBLE
                ll_content.visibility = View.GONE
            } else {

                sw_refresh.isRefreshing = false
                sw_refresh.setOnRefreshListener {
                    scrollListener.resetState()
                    reloadData()
                }

                // val layoutManagers = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                val layoutManagers = GridLayoutManager(this, 4)
                rv_list2.layoutManager = layoutManagers
                rv_list2.adapter = adapter2

                scrollListener = object : EndlessRecyclerViewScrollListener(layoutManagers) {
                    override fun onFirstItemVisible(isFirstItem: Boolean) {
                        sw_refresh.isEnabled = isFirstItem && adapter2.itemCount > 0
                    }

                    override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                        val pages = page+1
                        Log.d("Tambah",page.toString())
                        Handler().postDelayed(Runnable { getPresenter()?.loadProducts(pages) }, 1000)
                    }
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0) {
                            //Handler().postDelayed(Runnable { getNewPosition(recyclerView) }, 1000)
                            Log.d("Handle", recyclerView.toString())
                        }

                    }
                }
                rv_list2.addOnScrollListener(scrollListener)

                adapter2.callback = object : ProductAdapter.ItemClickCallback {
                    override fun onClick(data: Product) {
                        showLoadingDialog()
                        if (data.id_master == "0") {
                            onSelected2(data)
                        } else {
                            getPresenter()?.onCheckVariable2(data.id_product!!)
                        }
                    }
                }
            }

        }else{
            openChooseProduct()
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        adapter.callback = object : SellAdapter.ItemClickCallback {
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

            override fun onPriceDialog(data: Cart, position: Int) {
                openPriceDialog(data,position)
            }

            override fun onStock(data: Cart, position: Int) {
                val name = data.product?.name_product
                openStock(name)
            }
        }

        btn_bayar.setOnClickListener {
            getPresenter()?.checkCart()
        }

    }



    private fun getNewPosition(@NonNull recyclerView: RecyclerView) {
        val layoutManagers = recyclerView.layoutManager as GridLayoutManager?
        if (layoutManagers != null) {
            recyclerVisiblePosition = layoutManagers.findLastVisibleItemPosition()
            val totalItem: Int = layoutManagers.getItemCount()
            val lastVisibleItem: Int = layoutManagers.findLastVisibleItemPosition()
            val page = recyclerVisiblePosition/19

            if (lastVisibleItem == totalItem - 1) {
                sw_refresh.isRefreshing = true
                getPresenter()?.loadProducts(page)
                Log.d("TAG", "loadNextPage: $page $totalItem $lastVisibleItem")
            }
        }
    }

    override fun reloadData() {
        sw_refresh.isRefreshing = true
        adapter2.clearAdapter()
        getPresenter()?.loadProducts(1)
    }

    override fun setProducts(list: List<Product>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter2.setItems(list)
    }

    override fun openProducts(title: String, list: List<Product>, selectedp: Product?) {
        hideLoadingDialog()
        if (productDialog.dialog != null && productDialog.dialog!!.isShowing) {

        } else {
            productDialog.setData(title, 1, list, selectedp)
            productDialog.show(supportFragmentManager, "product")
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
        val intent = if(data.isEmpty()) Intent(this, ChooseProductActivity::class.java)
        else Intent(this, ActivitySubCategory::class.java).putExtra("data", data)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_PRODUCT)
    }

    fun openChooseProductData() {
        val intent = Intent(this, ChooseProductActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_PRODUCT)
    }

    override fun openSuccessPage(carts: HashMap<String,Cart>) {
        val intent = Intent(this, ConfirmationActivity::class.java)
        intent.putExtra(AppConstant.DATA,carts)
        startActivity(intent)
        carts.clear()
    }

    override fun showContentView() {
        ll_content.visibility = View.VISIBLE
        ll_error.visibility = View.GONE
    }

    override fun showErrorView(err: String) {
       // ll_content.visibility = View.GONE
       // ll_error.visibility = View.VISIBLE
        tv_error.text = err
    }

    override fun setCartText(count: String, nominal: String) {
        tv_number.text = count
        tv_total.text = nominal
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

    override fun deleteCartAll() {
        adapter.clearAdapter()
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

    override fun onSuccessGets(list: List<Discount>) {
    }

    override fun onSuccessGetData(list: List<Customer>) {
    }

    override fun onFailedAPI(code: Int, msg: String) {
    }

    override fun openPriceDialog(selected: Cart, pos: Int) {
        val pricedialog = CartPriceDialog.newInstance()
        pricedialog.setData(selected,pos,true)
        pricedialog.show(this.supportFragmentManager, CartPriceDialog.TAG)
    }

    override fun openStock(name: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("Stock " + name + " is almost out, please add stock.")
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
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

    override fun onSelected2(data: Product) {
        getPresenter()?.setSelectedProduct2(data)
        hideLoadingDialog()
    }

    override fun onItemClicked(data: Product, type: Int) {
        getPresenter()?.setSelectedProduct2(data)
    }


}
