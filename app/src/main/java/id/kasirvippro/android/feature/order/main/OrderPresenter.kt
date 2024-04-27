package id.kasirvippro.android.feature.order.main

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.divisi.Divisi
import id.kasirvippro.android.models.ongkir.Ongkir
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.transaction.DetailPayment
import id.kasirvippro.android.models.transaction.Order
import id.kasirvippro.android.models.transaction.RequestTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.PermissionUtil
import id.kasirvippro.android.utils.AppConstant

class OrderPresenter(val context: Context, val view: OrderContract.View) : BasePresenter<OrderContract.View>(),
    OrderContract.Presenter, OrderContract.InteractorOutput {

    private var interactor = OrderInteractor(this)
    private var productRestModel = ProductRestModel(context)
    private var transactionRestModel = TransactionRestModel(context)

    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var carts:HashMap<String,Cart> = HashMap()
    private var tempBarcode:String?=null
    private var customer:Customer?=null
    private var table:Table?=null
    private var ongkir:Ongkir?=null
    private var divisi:Divisi?=null
    private var staff:Staff?=null
    private var date:CalendarDay?=null
    private lateinit var photoPermission: PermissionCallback
    private var photoPath:String? = null
    private var id = ""
    private var product:Product? = null
    private var products:ArrayList<Product> = ArrayList()
    private var haveStok = ""
    private var discount:Discount?=null
    private var total = 0.0


    override fun onViewCreated() {
        photoPermission = object : PermissionCallback {
            override fun onSuccess() {
                view.openImageChooser()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }
        cameraPermission = object : PermissionCallback{
            override fun onSuccess() {
                view.openScanPage()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }
        carts = HashMap()
        loadProducts(1)
        countCart()
    }



    override fun onCheckPhoto() {
        permissionUtil.checkCameraPermission(photoPermission)
    }

    override fun setImagePhotoPath(path: String?) {
        photoPath = path
    }

    override fun onCheckVariable2(id_product: String) {
        if(products.isEmpty()){
            interactor.callGetProductsVariableAPI(context,productRestModel,id_product,haveStok)
        }
        else{
            interactor.callGetProductsVariableAPI(context,productRestModel,id_product,haveStok)
        }
    }

    override fun setSelectedProduct2(data: Product) {
        Log.d("dataaddon", Gson().toJson(data))
        checkCart(data)
    }

    override fun updateDiscount(data: Discount?) {
        discount = data
        view.setDiscount(data)
        checkDiscount()
    }

    override fun checkDiscount() {
        view.showLoadingDialog()
        interactor.callCheckDiscountAPI(context,transactionRestModel,total,discount?.id_discount)
    }

    override fun addCart(data: Product) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        data.let {
            val stok = it.stock!!.toDouble()
            if(carts.containsKey(it.id_product)){
                val cart = carts[it.id_product]
                val count = cart?.count!!.plus(1)
                if(count <= stok){
                    cart.count = count
                    view.addCart(cart)
                } else{
                    if(decimal=="No Decimal") {
                        view.showMessage(999,"Max Stock ${Helper.convertToCurrency(stok)}")
                    }else{
                        view.showMessage(999,"Max Stock ${stok}")
                    }
                }

            } else{
                if("0" == data.have_stock){
                    val cart = Cart()
                    cart.product = it
                    cart.count = 1.0
                    carts.put(it.id_product!!,cart)
                    view.addCart(cart)
                } else{
                    if(stok > 0){
                        val cart = Cart()
                        cart.product = it
                        cart.count = 1.0
                        carts.put(it.id_product!!,cart)
                        view.addCart(cart)
                    } else{
                        view.showMessage(999,"Product stock is empty")
                    }
                }


            }
            countCart()
        }
    }

    override fun checkCart(data: Product) {
        if(data.posisi == true){
            addCart(data)
        }
    }

    override fun increaseCart(data: Cart,position:Int) {
        val id = data.product!!.id_product
        if(!carts.containsKey(id)){
            return
        }
        val cart = carts[id]!!
        val count = cart.count!!.plus(1.0)
        val produk = cart.product!!
        val dataprice = produk.pricevariant
        val countprice = dataprice!!.size
        for (i in 0 until countprice) {
            val minimal = produk.pricevariant?.get(i)?.minimal
            if(count >= minimal!!.toDouble() ){
                val price = produk.pricevariant?.get(i)?.price
                cart.new_price = price
                Log.d("Harga", count.toString() + minimal + price)
            }else{
                Log.d("Harga2", count.toString() + minimal)
            }
        }
        cart.count = count
        carts[id!!] = cart
        view.updateCart(cart,position)
        countCart()
    }

    override fun decreaseCart(data: Cart,position:Int) {
        val id = data.product!!.id_product
        if(!carts.containsKey(id)){
            return
        }
        val cart = carts[id]!!
        val count = cart.count!!.minus(1.0)
        if(count < 0.0){
            return
        }
        val produk = cart.product!!
        val dataprice = produk.pricevariant
        val countprice = dataprice!!.size
        for (i in 0 until countprice) {
            val minimal = produk.pricevariant?.get(i)?.minimal
            if(count >= minimal!!.toDouble() ){
                val price = produk.pricevariant?.get(i)?.price
                cart.new_price = price
                Log.d("Harga", count.toString() + minimal + price)
            }else{
                Log.d("Harga2", count.toString() + minimal)
            }
        }
        if(count == 0.0){
            deleteCart(data,position)
            return
        }
        Log.d("count", count.toString())
        cart.count = count
        carts[id!!] = cart
        view.updateCart(cart,position)
        countCart()
    }

    override fun updateCart(data: Cart,position:Int) {
        val id = data.product!!.id_product
        if(!carts.containsKey(id)){
            return
        }
        carts[id!!] = data
        carts[id]?.let { view.updateCart(it,position) }
        countCart()
    }

    override fun deleteCart(data: Cart,position:Int) {
        carts.remove(data.product!!.id_product)
        view.deleteCart(position)
        countCart()
    }

    override fun countCart() {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(carts.size == 0){
            view.setCartText("0")
            view.showErrorView("click add or scan icon for start")
            return
        }

        var count = 0.0
        var total = 0.0
        for(cart:Cart in carts.values){
            count += cart.count!!
            val sell = cart.product!!.selling_price!!.toDouble()
            val new_price   = cart.new_price?.toDouble()
            val nominal     = cart.nominal_addon?.toDouble()
            val ongkir      = cart.ongkir?.toDouble()
            Log.d("no_invoice",cart.count!!.toString() + sell + nominal!!)
            if(new_price == 0.0){
                val subtotal = cart.count!! * sell + nominal + ongkir!!
                total += subtotal
               // interactor.callCheckDiscountAPI(context,transactionRestModel,total,discount?.id_discount)
            }else{
                val subtotal = cart.count!! * new_price!! + nominal + ongkir!!
                total += subtotal
               //  interactor.callCheckDiscountAPI(context,transactionRestModel,total,discount?.id_discount)
            }
        }

        if(count > 99){
            if(decimal=="No Decimal") {
                view.setCartText(AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(total))
            }else{
                view.setCartText(AppConstant.CURRENCY.getCurrencyData() + total)
            }
        }
        else{
            if(decimal=="No Decimal") {
                view.setCartText(AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(total))
            }else{
                view.setCartText(AppConstant.CURRENCY.getCurrencyData() + total)
            }
        }
        view.showContentView()
    }

    override fun onCheckScan() {
        permissionUtil.checkCameraPermission(cameraPermission)
    }

    override fun loadProducts(page: Int?) {
        interactor.callGetProductsAPI(context,productRestModel,id,page)
    }

    override fun searchByBarcode(search:String) {
        tempBarcode = search
        interactor.callSearchByBarcodeAPI(context,productRestModel,search)
    }

    override fun onSuccessByBarcode(list: List<Product>) {
        view.hideLoadingDialog()
        if(list.isNotEmpty()){
            val product = list[0]
            checkCart(product)
        }
        tempBarcode = ""

    }

    override fun onFailedBarcode(code: Int, msg: String) {
        onFailedAPI(code,msg)
        tempBarcode = ""
    }

    override fun onSuccessGetProducts(list: List<Product>) {
        view.setProducts(list)
    }

    override fun onSuccessCheckDiscount(data: List<DetailPayment>?) {
        view.hideLoadingDialog()
        data?.let {list ->
            if(list.isNotEmpty()){
                val detail = list[0]
                view.setDetailText(detail.totalprice!!,detail.discount,detail.service_charge,detail.tax,detail.totalpay!!)
                countDebt()
            }
        }
    }

    override fun onSuccessGetProductsVariable(list: List<Product>) {
        if(list.isEmpty()){
            view.showMessage(999,"No product yet")
            return
        }
        products = ArrayList()
        for(cat in list){
            val model = Product()
            model.id_product = cat.id_product
            model.name_product = cat.name_product
            model.img = cat.img
            model.codeproduct = cat.codeproduct
            model.id_category = cat.id_category
            model.name_category = cat.name_category
            model.active = cat.active
            model.alertstock = cat.alertstock
            model.selling_price = cat.selling_price
            model.purchase_price = cat.purchase_price
            model.stock = cat.stock
            model.minimalstock = cat.minimalstock
            model.description = cat.description
            model.discount = cat.discount
            model.posisi = cat.posisi
            model.online = cat.online
            model.have_stock = cat.have_stock
            model.wholesale_price = cat.wholesale_price
            model.pricevariant = cat.pricevariant
            model.inc = cat.inc
            products.add(model)
        }
        Log.d("products", Gson().toJson(products))
        view.openProducts("Product Variant",products,product)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.hideLoadingDialog()
        view.showMessage(code,msg)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun updateCustomer(data: Customer?) {
        customer = data
        view.setCustomerName(data)
    }
    override fun updateTable(data: Table?) {
        table = data
        view.setTableName(data)
    }

    override fun updateOngkir(data: Ongkir?) {
        ongkir = data
        view.setOngkirName(data)
    }

    override fun updateDivisi(data: Divisi?) {
        divisi = data
        view.setDivisiName(data)
    }

    override fun updateStaff(data: Staff?) {
        staff = data
        view.setStaffName(data)
    }

    override fun setSelectedDate(date: CalendarDay?) {
        this.date = date
    }

    override fun getSelectedDate(): CalendarDay? {
        return date
    }

    override fun checkTunai() {


        val total = view.getTotalValue()
        val req = RequestTransaction()
        req.payment_type = 1
        req.total_order = total.toInt()
        req.id_customer = customer?.id_customer
        req.id_table = table?.id_table
        req.ongkir = ongkir?.nominal
        req.divisi = divisi?.name_divisi
        req.id = staff?.phone_number
        req.product = getBarang()

        interactor.callOrderAPI(context,transactionRestModel,req,photoPath)

    }

    override fun checkPiutang(note:String) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        val paydebt = view.getPayDebt()
        if(paydebt < 0.0){
            view.showMessage(999,"Cash payments cannot be empty")
            return
        }
        val totalValue = view.getTotalValue()
        if(paydebt > totalValue){
            if(decimal=="No Decimal") {
                view.showMessage(999,"Your maximum payment is ${AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(totalValue)}")
                return
            }else{
                view.showMessage(999,"Your maximum payment is ${AppConstant.CURRENCY.getCurrencyData() + totalValue}")
                return
            }
        }
        val cashdebt = paydebt
        if(date == null){
            view.showMessage(999,"Delivery Date is required")
            return
        }
        val total = view.getTotalValue()
        val req = RequestTransaction()
        req.payment_type = 2
        req.total_order = total.toInt()
        req.payment_amount = cashdebt.toInt()
        req.note = note
        req.id_customer = customer?.id_customer
        req.id_table = table?.id_table
        req.ongkir = ongkir?.nominal
        req.divisi = divisi?.name_divisi
        req.id = staff?.phone_number
        req.due_date = date?.date.toString()
        req.product = getBarang()

        interactor.callOrderAPI(context,transactionRestModel,req,photoPath)
    }

    private fun getBarang():List<RequestTransaction.Barang>{
        val list = ArrayList<RequestTransaction.Barang>()
        if(carts.size == 0){
            return list
        }
        for(cart in carts.values){
            val barang = RequestTransaction.Barang()
            barang.id_product = cart.product?.id_product
            barang.amount_product = cart.count?.toInt()
            if(cart.note == ""){
                barang.notes = "-"
            }else{
                barang.notes = cart.note.toString()
            }
            list.add(barang)
        }
        return list
    }

    override fun onSuccessOrder() {
        view.hideLoadingDialog()
        view.deleteCartAll()
        view.openSuccessPage()
        carts.clear()
    }

    override fun getCartsSize():Int {
        return carts.size
    }

    override fun countDebt() {
        val total = view.getTotalValue()
        val paydebt = view.getPayDebt()
        if(paydebt == 0.0){
            view.hideShowDebt(View.VISIBLE)
            val cashdebt = total
            view.setCashDebt(cashdebt)
            return
        }else{
            val cashdebt = total - paydebt
            view.setCashDebt(cashdebt)

        }
    }
}