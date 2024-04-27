package id.kasirvippro.android.feature.transaction.detailSplit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.view.View
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.PermissionUtil
import id.kasirvippro.android.R
import id.kasirvippro.android.callback.PermissionCallback
import org.greenrobot.eventbus.EventBus
import id.kasirvippro.android.events.onReloadTransaction
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.transaction.*
import id.kasirvippro.android.utils.BluetoothUtil
import id.kasirvippro.android.utils.*
import mumayank.com.airlocationlibrary.AirLocation


class DetailPresenter(val context: Context, val view: DetailContract.View) : BasePresenter<DetailContract.View>(),
    DetailContract.Presenter, DetailContract.InteractorOutput {

    private var interactor = DetailInteractor(this)
    private var restModel = TransactionRestModel(context)
    private var data: DetailTransaction? = null
    private var id: String? = null
    private var customer:Customer?=null
    private var totalorder = 0.0
    private lateinit var bluetoothPermission:PermissionCallback
    private var typeTRX = AppConstant.Code.CODE_TRANSACTION_CUSTOMER
    private var openMain = false
    private lateinit var storagePermission: PermissionCallback
    private var carts:HashMap<String,DetailTransaction.Data> = HashMap()
    private var premium:Boolean = false
    private var transactionRestModel = TransactionRestModel(context)
    private var req = ReqTrans()
    private lateinit var locationPermission: PermissionCallback
    private var airLocation: AirLocation? = null
    private var permissionUtil = PermissionUtil(context)
    private var isNonTunai = false
    private var order:Order?=null
    private var date:CalendarDay?=null


    override fun onViewCreated(intent: Intent) {
        premium = "1" == interactor.getUserPaket(context)
        view.onPremiumPage(premium)
        bluetoothPermission = object : PermissionCallback{
            override fun onSuccess() {

                if(BluetoothUtil.isBluetoothOn()){
                    view.openPrinterPage()
                }
                else{
                    BluetoothUtil.openBluetooth(context)
                }

            }



            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_bluetooth))
            }

        }

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

        storagePermission = object :PermissionCallback{
            override fun onSuccess() {
                ImageHelper.takeScreenshot(context,view.getParentLayout())
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_write_external))
            }
        }

        id = intent.getStringExtra(AppConstant.DATA)
        if(id.isNullOrBlank() || id.isNullOrEmpty()){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }
        typeTRX = intent.getIntExtra(AppConstant.CODE,AppConstant.Code.CODE_TRANSACTION_CUSTOMER)
        openMain = intent.getBooleanExtra(AppConstant.MAIN,false)
        loadDetail()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadDetail() {
        if(AppConstant.Code.CODE_TRANSACTION_CUSTOMER == typeTRX){
            interactor.callGetDetailAPI(context,restModel,id!!)
        }
        else{
            interactor.callGetDetailSupplierAPI(context,restModel,id!!)
        }

    }

    override fun onSuccessGetDetail(detail: DetailTransaction?) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(detail == null){
            onFailedAPI(999,"no data")
            return
        }
        this.data = detail
        val struk = detail.struk
        val data = detail.data
        var service = struk?.service_charge
        var tax = struk?.tax
        var discount = struk?.discount

        when {
            "cancel" == struk?.status -> {

            }
            "debt" == struk?.status -> {


            }
            else -> {
                if(typeTRX == AppConstant.Code.CODE_TRANSACTION_SUPPLIER){

                }
                else{

                }

            }
        }

        if(service.isNullOrEmpty() || service.isNullOrBlank() || service == "0"){
            service = null
        }
        else{
            if(decimal=="No Decimal") {
                service = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(service)
            }else{
                service = AppConstant.CURRENCY.getCurrencyData() + service
            }
        }

        if(tax.isNullOrEmpty() || tax.isNullOrBlank() || tax == "0"){
            tax = null
        }
        else{
            if(decimal=="No Decimal") {
                tax = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(tax)
            }else{
                tax = AppConstant.CURRENCY.getCurrencyData() + tax
            }
        }

        if(discount.isNullOrEmpty() || discount.isNullOrBlank() || discount == "0"){
            discount = null
        }
        else{
            if(decimal=="No Decimal") {
                discount = "-" + AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(discount)
            }else{
                discount = "-" + AppConstant.CURRENCY.getCurrencyData() + discount
            }
        }

        if(decimal=="No Decimal") {
            view.setInfo(context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!,
                Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd","dd MMMM yyyy"),
                struk.status!!,AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(struk.totallast!!),struk.operator!!,service,tax,discount,AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(struk.totalorder!!)
            )

        }else{
            view.setInfo(context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!,
                Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd","dd MMMM yyyy"),
                struk.status!!,AppConstant.CURRENCY.getCurrencyData() + struk.totallast!!,struk.operator!!,service,tax,discount,AppConstant.CURRENCY.getCurrencyData() + struk.totalorder!!
            )
        }


        view.setProducts(data!!)

        view.hideShowActionButton(View.VISIBLE)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onSuccessDeleteDetail(message: String?) {
        view.showMessage(999,message)
        EventBus.getDefault().post(onReloadTransaction(true))
        view.onClose(Activity.RESULT_OK)
    }

    override fun deleteDetail() {
        if(AppConstant.Code.CODE_TRANSACTION_CUSTOMER == typeTRX){
            interactor.callDeleteDetailAPI(context,restModel,id!!)
        }
        else{
            interactor.callSupplierDeleteDetailAPI(context,restModel,id!!)
        }

    }


    override fun onCheckBluetooth() {
        permissionUtil.checkBluetoothPermission(bluetoothPermission)
    }

    override fun onCheckShare() {
        permissionUtil.checkWriteExternalPermission(storagePermission)
    }

    override fun getDataStruk():DetailTransaction {
        return data!!
    }

    override fun getTypeTRX(): Int {
        return typeTRX
    }

    override fun onPay(value: String) {
        val struk = data?.struk
        if(AppConstant.Code.CODE_TRANSACTION_CUSTOMER == typeTRX){
            interactor.callPayPiutangAPI(context,restModel,struk?.no_invoice!!,value)
        }
        else{
            interactor.callPayHutangAPI(context,restModel,struk?.no_invoice!!,value)
        }
    }

    override fun onSuccessPay(message: String?) {
        view.showMessage(999,message)
        EventBus.getDefault().post(onReloadTransaction(true))
        view.reloadData()
    }

    override fun isOpenMain(): Boolean {
        return openMain
    }

    override fun onSuccessCheckDiscount(data: List<DetailPayment>?) {
        view.hideLoadingDialog()
        data?.let {list ->
            if(list.isNotEmpty()){
                val detail = list[0]

            }
        }
    }

    override fun onSuccessDeleteProduct(msg: String?) {
        view.showSuccessMessage(msg)
    }



    override fun onSuccessOrder(order: Order) {
        view.hideLoadingDialog()
        if(order.invoice == null){
            view.showMessage(999,"Invoice number not found")
            return
        }
        this.order = order

            view.openSuccessPage(order.invoice!!)

    }

    override fun onSuccessUpdate() {
        view.hideLoadingDialog()
        view.openSuccessPage(id!!)
    }

    override fun checkTunai(detail: DetailTransaction?) {
        val struk = data?.struk
        val no_invoice = struk?.no_invoice

        val total = view.getTotalValue()
        req = ReqTrans()
        req.payment_type = 1
        req.id = no_invoice
        req.total_order = total.toInt()
        req.product = getBarang()

        if(customer != null){
            req.id_customer = customer?.id_customer
        }
        isNonTunai = false

        interactor.callPayOrderAPI(context,transactionRestModel,req,note = String(),id = String())

    }

    private fun getBarang(): List<DetailTransaction.Data>? {
        val list = ArrayList<DetailTransaction.Data>()
        if(carts.size == 0){
            return list
        }
        for(cart in carts.values){
            val barang = DetailTransaction.Data()
            barang.id_product = cart.id_product
            barang.amount = cart.amount
            list.add(barang)
        }
        return list
    }

    override fun deleteProduct(no_invoice: String, id: String) {
        interactor.callDeleteProductAPI(context,restModel,no_invoice,id)
    }

    override fun plusProduct(no_invoice: String, id: String) {
        interactor.callPlusProductAPI(context,restModel,no_invoice,id)
    }
    override fun minusProduct(no_invoice: String, id: String) {
        interactor.callMinusProductAPI(context,restModel,no_invoice,id)
    }


    override fun addOrder() {
        view.openAddOrderPage(id!!)
    }

    override fun setSelectedDate(date: CalendarDay?) {
        this.date = date
    }

    override fun getSelectedDate(): CalendarDay? {
        return date
    }

    override fun checkPiutang(note: String) {
        if(date == null){
            view.showMessage(999,"Delivery Date is required")
            return
        }
        if(note.isBlank() || note.isEmpty()){
            view.showMessage(999,"Jam harus diisi")
            return
        }
        val req = RequestTransaction()
        req.id = id!!
        req.note = note
        req.due_date = date?.date.toString()
        interactor.callOrderAPI(context,transactionRestModel,req)
    }


}