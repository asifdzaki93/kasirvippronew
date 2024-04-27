package id.kasirvippro.android.feature.order.main

import android.annotation.SuppressLint
import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.gson.Gson
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.CartCountDialog
import id.kasirvippro.android.feature.dialog.SingleDateDialog
import id.kasirvippro.android.feature.dialog.NoteDialog
import id.kasirvippro.android.feature.choose.customer.ChooseCustomerActivity
import id.kasirvippro.android.feature.choose.discount.ChooseDiscountActivity
import id.kasirvippro.android.feature.choose.divisi.ChooseDivisiActivity
import id.kasirvippro.android.feature.choose.kurir.ChooseKurirActivity
import id.kasirvippro.android.feature.choose.ongkir.ChooseOngkirActivity
import id.kasirvippro.android.feature.order.addNewCustomer.AddNewCustomerActivity
import id.kasirvippro.android.feature.choose.table.ChooseTableActivity
import id.kasirvippro.android.feature.scan.ScanCodeActivity
import id.kasirvippro.android.feature.choose.productOrder.ChooseProductActivity
import id.kasirvippro.android.feature.choose.orderProduct.ActivitySubCategory
import id.kasirvippro.android.feature.choose.sift.ChooseSiftActivity
import id.kasirvippro.android.feature.dialog.BottomProductDialog
import id.kasirvippro.android.feature.sell.addDiscount.AddActivity
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.divisi.Divisi
import id.kasirvippro.android.models.ongkir.Ongkir
import id.kasirvippro.android.models.sift.Sift
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.ImageCompression
import id.kasirvippro.android.utils.ImageUtil
import id.kasirvippro.android.utils.glide.GlideApp
import org.threeten.bp.LocalDate
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_order.btn_add_customer
import kotlinx.android.synthetic.main.activity_order.btn_add_discount
import kotlinx.android.synthetic.main.activity_order.btn_bayar
import kotlinx.android.synthetic.main.activity_order.btn_date
import kotlinx.android.synthetic.main.activity_order.btn_delete_customer
import kotlinx.android.synthetic.main.activity_order.btn_delete_discount
import kotlinx.android.synthetic.main.activity_order.btn_delete_table
import kotlinx.android.synthetic.main.activity_order.et_customer
import kotlinx.android.synthetic.main.activity_order.et_date
import kotlinx.android.synthetic.main.activity_order.et_discount
import kotlinx.android.synthetic.main.activity_order.et_pay_debt
import kotlinx.android.synthetic.main.activity_order.et_table
import kotlinx.android.synthetic.main.activity_order.ll_cashdebt
import kotlinx.android.synthetic.main.activity_order.ll_content
import kotlinx.android.synthetic.main.activity_order.ll_error
import kotlinx.android.synthetic.main.activity_order.ll_hutang
import kotlinx.android.synthetic.main.activity_order.ll_tables
import kotlinx.android.synthetic.main.activity_order.rg_payment
import kotlinx.android.synthetic.main.activity_order.rv_list
import kotlinx.android.synthetic.main.activity_order.rv_list2
import kotlinx.android.synthetic.main.activity_order.sw_refresh
import kotlinx.android.synthetic.main.activity_order.tv_error
import kotlinx.android.synthetic.main.activity_order.tv_paydebt
import kotlinx.android.synthetic.main.activity_order.tv_subtotal
import kotlinx.android.synthetic.main.activity_order.tv_total
import kotlinx.android.synthetic.main.activity_order.et_hour
import java.io.File
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class OrderActivity : BaseActivity<OrderPresenter, OrderContract.View>(),
    OrderContract.View,
    SingleDateDialog.Listener, CartCountDialog.Listener,NoteDialog.Listener,  BottomProductDialog.Listener {
    private lateinit var choosePhotoHelper: ChoosePhotoHelper
    companion object { var data = "" }

    private val codeOpenScan = 1001
    private val codeOpenChooseProduct = 1002
    private val codeOpenChooseCustomer = 1005
    private val codeOpenAddCustomer = 1006
    private val codeOpenChooseTable = 1007
    private val codeOpenChooseOngkir = 1003
    private val codeOpenChooseDivisi = 1011
    private val codeOpenChooseStaff = 1008
    private val codeOpenChooseShift = 1012
    private val CODE_OPEN_CHOOSE_DISCOUNT = 1009
    private val CODE_OPEN_ADD_DISCOUNT = 1010

    private val adapter = OrderAdapter()
    private val adapter2 = ProductAdapter()
    private val productDialog = BottomProductDialog.newInstance()
    private var recyclerVisiblePosition = 0
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun createPresenter(): OrderPresenter {
        return OrderPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_order
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
       // openChooseProduct()
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.new_order)
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
                            Log.d("dataaja", Gson().toJson(data))
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

        adapter.callback = object : OrderAdapter.ItemClickCallback {
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

        rg_payment.check(R.id.rb_piutang)

        et_table.setOnClickListener {
            openChooseTable()
        }
        et_ongkir.setOnClickListener {
            openChooseOngkir()
        }
        et_divisi.setOnClickListener {
            openChooseDivisi()
        }

        et_customer.setOnClickListener {
            openChooseCustomer()
        }

        et_staff.setOnClickListener {
            openChooseStaff()
        }

        et_shift.setOnClickListener {
            openChooseSift()
        }

        btn_add_customer.setOnClickListener {
            openAddCustomer()
        }

        btn_add_discount.setOnClickListener {
            openAddDiscount()
        }

        btn_delete_discount.setOnClickListener {
            getPresenter()?.updateDiscount(null)
        }

        et_discount.setOnClickListener {
            openChooseDiscount()
        }

        btn_delete_table.setOnClickListener {
            getPresenter()?.updateTable(null)
        }

        btn_delete_staff.setOnClickListener {
            getPresenter()?.updateStaff(null)
        }

        btn_delete_customer.setOnClickListener {
            getPresenter()?.updateCustomer(null)
        }

        btn_add_customer.setOnClickListener {
            openAddCustomer()
        }

        et_hour.setOnClickListener {
            getTime(et_hour)
        }

        btn_date.setOnClickListener {
            openSingleDatePickerDialog(getPresenter()?.getSelectedDate())
        }

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

        btn_camera.setOnClickListener {
            getPresenter()?.onCheckPhoto()
        }

        btn_bayar.setOnClickListener {
            showLoadingDialog()
            val note = "Jam Acara: " + et_hour.text.toString().trim() + ", Jenis Pengiriman: " + et_shift.text.toString().trim() + ", Catatan: " + et_location.text.toString().trim()
            when (rg_payment.checkedRadioButtonId) {
                R.id.rb_tunai -> {
                    getPresenter()?.checkTunai()
                }
                R.id.rb_piutang -> {
                    getPresenter()?.checkPiutang(note)
                }
            }
        }

        choosePhotoHelper = ChoosePhotoHelper.with(this)
            .asFilePath()
            .build(ChoosePhotoCallback { photo ->
                if(photo.isNullOrEmpty() || photo.isNullOrBlank()){
                    getPresenter()?.setImagePhotoPath(null)
                    loadPhoto("")
                    iv_camera.visibility = View.VISIBLE
                }
                else{
//                    val tmpBitmap = BitmapFactory.decodeFile(photo)
//                    val uri = Uri.fromFile(File(photo))
//                    val bitmap = ImageHelper.rotateImageIfRequired(tmpBitmap, uri)
//                    val uriImage = ImageUtil.getImageUri(this, bitmap)
//                    val path = FilePathUtil.getPath(this@AddProductActivity,uriImage)
//                    val size = ImageUtil.getSizeFile(path!!)
//                    Log.d("choosePhotoHelper",""+size)
                    val imageUtil = @SuppressLint("StaticFieldLeak")
                    object : ImageCompression(this@OrderActivity) {
                        @SuppressLint("LongLogTag")
                        override fun onPostExecute(imagePath: String) {
                            super.onPostExecute(imagePath)
                            val compressedImageFile = File(imagePath)
                            if(compressedImageFile.exists()){
                                val compressedSize = ImageUtil.getSizeFile(imagePath)
                                Log.d("choosePhotoHelper compressed size",""+compressedSize)
                                getPresenter()?.setImagePhotoPath(imagePath)
                                loadPhoto(imagePath)
                                iv_camera.visibility = View.GONE
                            }else{
                                getPresenter()?.setImagePhotoPath(null)
                                loadPhoto("")
                                iv_camera.visibility = View.VISIBLE
                                showMessage(999,"Photo not found")
                            }
                        }
                    }
                    //imageUtil.execute(path!!)
                    imageUtil.execute(photo)


                }

            })
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(textView: TextView){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            et_hour.text = SimpleDateFormat("HH:mm").format(cal.time)
        }
        textView.setOnClickListener {
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_scan_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.action_search -> openChooseProductData()
            R.id.action_scan -> getPresenter()?.onCheckScan()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun openImageChooser() {
        choosePhotoHelper.showChooser()
    }
    override fun loadPhoto(path: String) {
        Log.d("choosePhotoHelper",""+path)
        GlideApp.with(this)
            .load(path)
            .transform(CenterCrop(), RoundedCorners(4))
            .into(iv_photo_order)

    }

    override fun deleteCartAll() {
        adapter.clearAdapter()
    }

    override fun openProducts(title: String, list: List<Product>, selected: Product?) {
        hideLoadingDialog()
        if (productDialog.dialog != null && productDialog.dialog!!.isShowing) {

        } else {
            productDialog.setData(title, 1, list, selected)
            productDialog.show(supportFragmentManager, "product")
        }
    }

    override fun onSelected2(data: Product) {
        getPresenter()?.setSelectedProduct2(data)
        hideLoadingDialog()
    }

    @SuppressLint("SetTextI18n")
    override fun setDetailText(subtotal:Double,discount:Double?,service:Double?,tax:Double?,total:Double) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            tv_subtotal.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(subtotal)
            tv_total.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(total)

            val name:String = Helper.convertToCurrency(total)
            et_pay_debt.setText(name)
        }else{
            tv_subtotal.text = AppConstant.CURRENCY.getCurrencyData() + subtotal
            tv_total.text = AppConstant.CURRENCY.getCurrencyData() + total

            val name:String = total.toString()
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
    }

    override fun openChooseDiscount() {
        val intent = Intent(this, ChooseDiscountActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_DISCOUNT)
    }

    override fun setDiscount(data: Discount?) {
        et_discount.text = ""
        btn_delete_discount.visibility = View.GONE
        data?.let {
            et_discount.text = it.name_discount
            btn_delete_discount.visibility = View.VISIBLE
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

    override fun openAddDiscount() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_ADD_DISCOUNT)
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
        val intent = if(data.isEmpty()) Intent(this, ChooseProductActivity::class.java)
        else Intent(this, ActivitySubCategory::class.java).putExtra("data", data)
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
       // ll_content.visibility = View.GONE
       // ll_error.visibility = View.VISIBLE
        tv_error.text = err
    }

    override fun openSuccessPage() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Success")
        builder.setMessage("Order has been processed!")
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            restartMainActivity()
        }
        et_table.setText("");
        et_staff.setText("");
        et_customer.setText("");
        et_date.setText("");
        et_hour.text = ""
        et_location.setText("");
        et_pay_debt.setText("");
        builder.show()
    }

    override fun setCartText(nominal: String) {
        tv_total.text = nominal
        tv_subtotal.text = nominal
        tv_paydebt.text = nominal
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
        val typestore = AppConstant.TYPESTORE.getTypeData()
        ll_discount.visibility = View.VISIBLE
        ll_hutang.visibility = View.GONE
        ll_jam.visibility = View.GONE
        ll_cashdebt.visibility = View.GONE
        ll_foto.visibility = View.GONE
        ll_ongkir.visibility = View.VISIBLE
        if(typestore=="Culinary") {
            ll_tables.visibility = View.GONE
            ll_staff.visibility = View.VISIBLE
        }else if(typestore=="Service products") {
            ll_tables.visibility = View.GONE
            ll_staff.visibility = View.VISIBLE
        }else{
            ll_tables.visibility = View.GONE
            ll_staff.visibility = View.GONE
        }
    }


    override fun showPiutangView() {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        ll_discount.visibility = View.GONE
        ll_hutang.visibility = View.VISIBLE
        ll_jam.visibility = View.VISIBLE
        ll_cashdebt.visibility = View.VISIBLE
        ll_foto.visibility = View.VISIBLE
        ll_ongkir.visibility = View.VISIBLE
        if(typestore=="Culinary") {
            ll_tables.visibility = View.GONE
            ll_staff.visibility = View.VISIBLE
        }else if(typestore=="Service products") {
            ll_tables.visibility = View.GONE
            ll_staff.visibility = View.VISIBLE
        }else{
            ll_tables.visibility = View.GONE
            ll_staff.visibility = View.GONE
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

    override fun setTableName(data: Table?) {
        et_table.text = ""
        btn_delete_table.visibility = View.GONE
        data?.let {
            et_table.text = it.name_table
            btn_delete_table.visibility = View.VISIBLE
        }
    }

    override fun setOngkirName(data: Ongkir?) {
        et_ongkir.text = ""
        btn_delete_ongkir.visibility = View.GONE
        data?.let {
            et_ongkir.text = it.name_ongkir
            btn_delete_ongkir.visibility = View.VISIBLE
        }
    }

    override fun setDivisiName(data: Divisi?) {
        et_divisi.text = ""
        btn_delete_divisi.visibility = View.GONE
        data?.let {
            et_divisi.text = it.name_divisi
            btn_delete_divisi.visibility = View.VISIBLE
        }
    }

    override fun setStaffName(data: Staff?) {
        et_staff.text = ""
        btn_delete_staff.visibility = View.GONE
        data?.let {
            et_staff.text = it.full_name
            btn_delete_staff.visibility = View.VISIBLE
        }
    }

    override fun openChooseTable() {
        val intent = Intent(this, ChooseTableActivity::class.java)
        startActivityForResult(intent, codeOpenChooseTable)
    }

    override fun openChooseOngkir() {
        val intent = Intent(this, ChooseOngkirActivity::class.java)
        startActivityForResult(intent, codeOpenChooseOngkir)
    }

    override fun openChooseDivisi() {
        val intent = Intent(this, ChooseDivisiActivity::class.java)
        startActivityForResult(intent, codeOpenChooseDivisi)
    }

    override fun openChooseCustomer() {
        val intent = Intent(this, ChooseCustomerActivity::class.java)
        startActivityForResult(intent, codeOpenChooseCustomer)
    }

    override fun openChooseStaff() {
        val intent = Intent(this, ChooseKurirActivity::class.java)
        startActivityForResult(intent, codeOpenChooseStaff)
    }
    override fun openChooseSift() {
        val intent = Intent(this, ChooseSiftActivity::class.java)
        startActivityForResult(intent, codeOpenChooseShift)
    }

    override fun openAddCustomer() {
        val intent = Intent(this, AddNewCustomerActivity::class.java)
        startActivityForResult(intent, codeOpenAddCustomer)
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

    override fun hideShowDebt(value: Int) {
        tv_paydebt.visibility = value
    }

    @SuppressLint("SetTextI18n")
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
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data)
        Log.d("request code",requestCode.toString())
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
        } else if (requestCode == codeOpenAddCustomer && resultCode == Activity.RESULT_OK) {
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
        else if (requestCode == codeOpenChooseCustomer && resultCode == Activity.RESULT_OK) {
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
        else if (requestCode == codeOpenChooseOngkir && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this, "Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this, "Data not found")
                return
            }
            val ongkir = data.getSerializableExtra(AppConstant.DATA) as Ongkir
            if (ongkir.nominal == null) {
                toast(this, "Data not found")
            } else {
                getPresenter()?.updateOngkir(ongkir)
            }
        }
        else if (requestCode == codeOpenChooseDivisi && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this, "Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this, "Data not found")
                return
            }
            val divisi = data.getSerializableExtra(AppConstant.DATA) as Divisi
            if (divisi.name_divisi == null) {
                toast(this, "Data not found")
            } else {
                getPresenter()?.updateDivisi(divisi)
            }
        }
        else if (requestCode == codeOpenChooseStaff && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this, "Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this, "Data not found")
                return
            }
            val staff = data.getSerializableExtra(AppConstant.DATA) as Staff
            if (staff.phone_number == null) {
                toast(this, "Data not found")
            } else {
                getPresenter()?.updateStaff(staff)
            }
        }
        else if (requestCode == codeOpenChooseShift && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val toko = data.getSerializableExtra(AppConstant.DATA) as Sift
            if (toko.name_sift == null) {
                toast(this,"Data tidak ditemukan")
            } else {
                et_shift.text = toko.name_sift
            }
        }
    }

    override fun onItemClicked(data: Product, type: Int) {
        getPresenter()?.setSelectedProduct2(data)
    }


}
