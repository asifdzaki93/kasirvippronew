package id.kasirvippro.android.feature.sellReturn.main

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.addOn.AddOn
import id.kasirvippro.android.models.addOn.AddOnRestModel
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.transaction.RequestTransaction
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.PermissionUtil

class SellReturnPresenter(val context: Context, val view: SellReturnContract.View) : BasePresenter<SellReturnContract.View>(),
    SellReturnContract.Presenter, SellReturnContract.InteractorOutput {

    private var interactor = SellReturnInteractor(this)
    private var productRestModel = ProductRestModel(context)
    private var addonRestModel = AddOnRestModel(context)

    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var carts:HashMap<String,Cart> = HashMap()
    private var tempBarcode:String?=null
    private var addon:AddOn? = null
    private var addons:ArrayList<AddOn> = ArrayList()

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

    override fun onCheckVariable(data: Cart,position:Int) {
            interactor.callGetAddonAPI(context,addonRestModel, data.product?.id_product!!)
    }

    override fun onSuccessGetAddon(list: List<AddOn>) {
        if(list.isEmpty()){
            view.showMessage(999,"No product yet")
            return
        }
        addons = ArrayList()
        for(cat in list){
            val model = AddOn()
            model.id_addon = cat.id_addon
            model.id_product = cat.id_product
            model.name_addon = cat.name_addon
            model.nominal = cat.nominal
            model.inc = cat.inc
            addons.add(model)
        }
        Log.d("addon", Gson().toJson(addons))
        view.openProducts("Add On",addons,addon)
    }

    override fun addCart(data: Product) {
        data?.let {
            val stok = it.stock!!.toDouble()
            if(carts.containsKey(it.id_product)){
                val cart = carts[it.id_product]
                val count = cart?.count!!.plus(1.0)
                if(count <= stok){
                    cart.count = count
                    view.addCart(cart)
                }
                else{
                    view.showMessage(999,"Max Stock ${stok}")
                }

            }
            else{
                if("0" == data.have_stock){
                    val cart = Cart()
                    cart.product = it
                    cart.count = 1.0
                    carts.put(it.id_product!!,cart)
                    view.addCart(cart)
                }
                else{
                    if(stok > 0){
                        val cart = Cart()
                        cart.product = it
                        cart.count = 1.0
                        carts.put(it.id_product!!,cart)
                        view.addCart(cart)
                    }
                    else{
                        val cart = Cart()
                        cart.product = it
                        cart.count = 1.0
                        carts.put(it.id_product!!,cart)
                        view.addCart(cart)
                        //view.showMessage(999,"Product stock is empty")
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

    override fun setSelectedProduct(data: AddOn,position:Int) {
        val id = data.id_product
        if(!carts.containsKey(id)){
            return
        }
        val cart = carts[id]!!
        val nominal = data.nominal

        cart.nominal_addon = nominal
        carts[id!!] = cart
        Log.d("dataaddon", Gson().toJson(cart))
        view.onSelected(cart,position)
    }

    override fun increaseCart(data: Cart,position:Int) {
        val id = data.product!!.id_product
        if(!carts.containsKey(id)){
            return
        }
        val cart = carts[id]!!
        val produk = cart.product!!
        val stok = produk.stock!!.toDouble()
        val count = cart.count!!.toDouble().plus(1.0)
        if("1" == produk.have_stock){
            if(count > stok){
                return
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
        val count = cart.count!!.toDouble().minus(1.0)
        if(count < 0.0){
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
            view.setCartText("0","0")
            view.showErrorView("click search or scan icon for start")
            return
        }

        var total = 0.0
        var count = 0.0.toString()

        for(cart:Cart in carts.values){
            count += cart.count!!
            val sell = cart.product!!.selling_price!!.toDouble()
            val nominal = cart.nominal_addon?.toDouble()
            Log.d("no_invoice", nominal.toString())
            val new_price = cart.new_price?.toDouble()

            if(new_price == 0.0){
                val subtotal = cart.count!!.toDouble() * sell
                total += subtotal
            }else{
                val subtotal = cart.count!!.toDouble() * new_price!!
                total += subtotal
            }

        }
        val totalrounded = String.format("%.2f", total) // rounds to 3 decimal places
        if(count > 99.toString()){
            if(decimal=="No Decimal") {
                view.setCartText(">99",AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(total))
            }else{
                view.setCartText(">99",AppConstant.CURRENCY.getCurrencyData() + totalrounded)
            }

        }
        else{
            if(decimal=="No Decimal") {
                view.setCartText(">99",AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(total))
            }else{
                view.setCartText(Helper.convertToCurrency(count),AppConstant.CURRENCY.getCurrencyData() + totalrounded)
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
        else{
            //view.openAddManual(tempBarcode!!)
            tempBarcode = ""
        }


    }

    override fun onFailedBarcode(code: Int, msg: String) {
        onFailedAPI(code,msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.hideLoadingDialog()
        view.showMessage(code,msg)
    }

    override fun onDestroy() {
        interactor.onDestroy()
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
            barang.notes = cart.note.toString()
            list.add(barang)
        }
        return list
    }

    override fun getCartsSize():Int {
        return carts.size
    }

    override fun checkCart(){
        if(carts.isNullOrEmpty()){
            onFailedAPI(999,"empty shopping cart")
            return
        }
        view.deleteCartAll()
        view.openSuccessPage(carts)
    }

}