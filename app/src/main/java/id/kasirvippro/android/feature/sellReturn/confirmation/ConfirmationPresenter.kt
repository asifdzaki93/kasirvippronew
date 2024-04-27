package id.kasirvippro.android.feature.sellReturn.confirmation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.view.View
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.transaction.*
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.PermissionUtil
import mumayank.com.airlocationlibrary.AirLocation
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ConfirmationPresenter(val context: Context, val view: ConfirmationContract.View) : BasePresenter<ConfirmationContract.View>(),
    ConfirmationContract.Presenter, ConfirmationContract.InteractorOutput {

    private var interactor = ConfirmationInteractor(this)
    private var transactionRestModel = TransactionRestModel(context)

    private var carts:HashMap<String,Cart> = HashMap()
    private var total = 0.0
    private var subtotal = 0.0
    private var req = RequestTransaction()
    private lateinit var locationPermission: PermissionCallback
    private var airLocation: AirLocation? = null
    private var permissionUtil = PermissionUtil(context)
    private var isNonTunai = false
    private var order:Order?=null
    private var table: Table?=null

    override fun onViewCreated(intent: Intent) {
        locationPermission = object : PermissionCallback {
            override fun onSuccess() {
                airLocation = AirLocation(context as Activity,
                    shouldWeRequestPermissions = true,
                    shouldWeRequestOptimization = true,
                    callbacks = object: AirLocation.Callbacks {
                        override fun onSuccess(location: Location) {
                            view.showLoadingDialog()
                            req.latitude = location.latitude
                            req.longitude = location.longitude
                            interactor.callOrderAPI(context,transactionRestModel,req,note = String())
                        }

                        override fun onFailed(locationFailedEnum: AirLocation.LocationFailedEnum) {
                            onFailedAPI(999,context.getString(R.string.reason_permission_location))
                        }
                    })
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_location))

            }

        }
        carts = intent.getSerializableExtra(AppConstant.DATA) as HashMap<String,Cart>
        view.setCart(ArrayList(carts.values))
        countCart()

    }



    override fun countCart() {

        for(cart:Cart in carts.values){
            if(cart.new_price == "0"){
                val sell = cart.product!!.selling_price!!.toDouble()
                val subtotal = cart.count!!*sell
                total += subtotal
                view.setDetailText(subtotal,total)
            }else{
                val sell = cart.new_price!!.toDouble()
                val subtotal = cart.count!!*sell
                total += subtotal
                view.setDetailText(subtotal,total)
            }
        }

    }


    override fun countCashback() {
        val total = view.getTotalValue()
        val pay = view.getPayValue()
        if(pay == 0.0 || total == 0.0){
            view.hideShowCashback(View.GONE)
            return
        }
        val cashback = total - pay
        view.setCashback(cashback)

    }
    override fun onFailedAPI(code: Int, msg: String) {
        view.hideLoadingDialog()
        view.showMessage(code,msg)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun checkTunai(note:String) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        val pay = view.getPayValue()
        if(pay == 0.0){
            view.showMessage(999,"The money received cannot be empty")
            return
        }
        if(note.isBlank() || note.isEmpty()){
            view.showMessage(999,"Invoice Number cannot be empty")
            return
        }
        val totalValue = view.getTotalValue()
        val cashback = totalValue - pay
        if(cashback > 0){
            if(decimal=="No Decimal") {
                view.showMessage(999,"Insufficient payment ${AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(cashback)}")
                return
            }else{
                view.showMessage(999,"Insufficient payment ${AppConstant.CURRENCY.getCurrencyData() + cashback}")
                return
            }

        }

        val dataManager = DataManager (context)

        req = RequestTransaction()
        val dt = dataManager.store("1")
        req.name_store = dt?.name_store
        req.email_store = dt?.email
        req.address_store = dt?.address
        req.nohp_store = dt?.nohp
        req.footer = dt?.footer
        req.id_table = table?.id_table
        req.note = note
        req.payment_type = 1
        req.payment_amount = pay.toInt()
        req.total_order = total.toInt()
        req.product = getBarang()
        isNonTunai = false
        permissionUtil.checkLocationPermission(locationPermission)

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
            if(cart.new_price == "0"){
                barang.new_price = cart.product?.selling_price!!
            }else{
                barang.new_price = cart.new_price.toString()
            }
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
        this.order = order
        if(isNonTunai){
            //val url = AppConstant.URL.NONTUNAI+interactor.getToken(context)+"&link="+nonTunai?.id_link+"&no_invoice="+order.invoice
           // view.openPaymentNonTunai(url,order.invoice!!)
            view.openSuccessPage(order.invoice!!)
        }
        else{
            view.openSuccessPage(order.invoice!!)
        }

    }

    override fun getCartsSize():Int {
        return carts.size
    }

}