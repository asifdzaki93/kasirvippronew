package id.kasirvippro.android.feature.kulakan.main

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.transaction.Order
import id.kasirvippro.android.models.transaction.RequestKulakan
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.PermissionUtil
import id.kasirvippro.android.utils.AppConstant

class KulakanPresenter(val context: Context, val view: KulakanContract.View) : BasePresenter<KulakanContract.View>(),
    KulakanContract.Presenter, KulakanContract.InteractorOutput {

    private var interactor = KulakanInteractor(this)
    private var productRestModel = ProductRestModel(context)
    private var transactionRestModel = TransactionRestModel(context)

    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var carts:HashMap<String,Cart> = HashMap()
    private var tempBarcode:String?=null
    private var supplier:Supplier?=null
    private var date:CalendarDay?=null

    override fun onViewCreated() {
        cameraPermission = object : PermissionCallback{
            override fun onSuccess() {
                view.openScanPage()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }
        carts = HashMap()
        countCart()
    }

    override fun addCart(data: Product) {
        data?.let {
            val stok = it.stock!!.toDouble()
            if(carts.containsKey(it.id_product)){
                val cart = carts[it.id_product]
                val count = cart?.count!!.plus(1)
                cart.count = count
                view.addCart(cart)

            }
            else{
                val cart = Cart()
                cart.product = it
                cart.count = 1.0
                carts.put(it.id_product!!,cart)
                view.addCart(cart)

            }
            countCart()
        }
    }

    override fun checkCart(data: Product) {
        if(data.posisi == true){
            addCart(data)
        }
//        else{
//            view.openEditManual(data)
//        }
    }

    override fun increaseCart(data: Cart,position:Int) {
        val id = data.product!!.id_product
        if(!carts.containsKey(id)){
            return
        }
        val cart = carts[id]!!
        val stok = cart.product!!.stock!!.toDouble()
        val count = cart.count!!.plus(1)
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
        val stok = cart.product!!.stock!!.toDouble()
        val count = cart.count!!.minus(1)
        if(count < 0){
            return
        }
        if(count == 0.0){
            deleteCart(data,position)
            return
        }
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
        carts[id!!]?.let { view.updateCart(it,position) }
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
            view.showErrorView("click search or scan icon for start")
            return
        }

        var total = 0.0
        var count = 0.0

        for(cart:Cart in carts.values){
            count += cart.count!!
            val sell = cart.product!!.purchase_price!!.toDouble()
            val subtotal = cart.count!!*sell
            total += subtotal
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
//        else{
//            view.openAddManual(tempBarcode!!)
//            tempBarcode = ""
//        }
        tempBarcode = ""

    }

    override fun onFailedBarcode(code: Int, msg: String) {
        onFailedAPI(code,msg)
        tempBarcode = ""
//        if("No data available" == msg){
//            view.hideLoadingDialog()
//            view.openAddManual(tempBarcode!!)
//            tempBarcode = ""
//        }
//        else{
//            onFailedAPI(code,msg)
//        }
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.hideLoadingDialog()
        view.showMessage(code,msg)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun updateSupplier(data: Supplier?) {
        supplier = data
        view.setSupplierName(data)
    }

    override fun setSelectedDate(date: CalendarDay?) {
        this.date = date
    }

    override fun getSelectedDate(): CalendarDay? {
        return date
    }

    override fun checkTunai() {
        if(supplier == null){
            view.showMessage(999,"Supplier data must be filled")
            return
        }

        val total = view.getTotalValue()
        val req = RequestKulakan()
        req.payment_type = 1
        req.total_order = total.toInt()
        req.id_supplier = supplier?.id_supplier
        req.product = getBarang()

        interactor.callOrderAPI(context,transactionRestModel,req)

    }

    override fun checkPiutang() {
        if(supplier == null){
            view.showMessage(999,"Supplier data has not been filled")
            return
        }
        if(date == null){
            view.showMessage(999,"The due date has not been filled")
            return
        }
        val total = view.getTotalValue()
        val req = RequestKulakan()
        req.payment_type = 3
        req.total_order = total.toInt()
        req.id_supplier = supplier?.id_supplier
        req.due_date = date?.date.toString()
        req.product = getBarang()

        interactor.callOrderAPI(context,transactionRestModel,req)
    }

    private fun getBarang():List<RequestKulakan.Barang>{
        val list = ArrayList<RequestKulakan.Barang>()
        if(carts.size == 0){
            return list
        }
        for(cart in carts.values){
            val barang = RequestKulakan.Barang()
            barang.id_product = cart.product?.id_product
            barang.amount_product = cart.count?.toInt()
            list.add(barang)
        }
        return list
    }

    override fun onSuccessOrder(order: Order) {
        view.hideLoadingDialog()
        if(order.invoice == null){
            view.showMessage(999,"Invoice number not found")
            return
        }
        view.openSuccessPage(order.invoice!!)
    }

    override fun getCartsSize():Int {
        return carts.size
    }

}