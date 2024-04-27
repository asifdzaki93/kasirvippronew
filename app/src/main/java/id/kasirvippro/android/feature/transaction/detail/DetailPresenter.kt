package id.kasirvippro.android.feature.transaction.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.view.View
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.PermissionUtil
import id.kasirvippro.android.R
import id.kasirvippro.android.callback.PermissionCallback
import org.greenrobot.eventbus.EventBus
import id.kasirvippro.android.events.onReloadTransaction
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.transaction.*
import id.kasirvippro.android.sqlite.Model.SalesDataSQL
import id.kasirvippro.android.sqlite.Model.SalesSQL
import id.kasirvippro.android.utils.BluetoothUtil
import id.kasirvippro.android.utils.*
import mumayank.com.airlocationlibrary.AirLocation


class DetailPresenter(val context: Context, val view: DetailContract.View) : BasePresenter<DetailContract.View>(),
    DetailContract.Presenter, DetailContract.InteractorOutput {

    private var interactor = DetailInteractor(this)
    private var restModel = TransactionRestModel(context)
    private val permissionUtil = PermissionUtil(context)
    private var data: DetailTransaction? = null
    private var id: String? = null
    private var nonTunai:NonTunai?=null
    private var discount:Discount?=null
    private var total = 0.0
    private lateinit var bluetoothPermission:PermissionCallback
    private var typeTRX = AppConstant.Code.CODE_TRANSACTION_CUSTOMER
    private var openMain = false
    private lateinit var storagePermission: PermissionCallback
    private var premium:Boolean = false
    private var transactionRestModel = TransactionRestModel(context)
    private var req = RequestTransaction()
    private lateinit var locationPermission: PermissionCallback
    private var airLocation: AirLocation? = null
    private var isNonTunai = false
    private var order: Order?=null
    private var level:String? = "kasir"

    private var sales : ArrayList<SalesSQL> = ArrayList()
    private lateinit var salesdata : SalesDataSQL

    override fun onViewCreated(intent: Intent) {
        premium = "1" == interactor.getUserPaket(context)
        view.onPremiumPage(premium)
        level = interactor.getUserLevel(context)
        when(level){
            "waiter" -> view.onWaiterPage()
            "kitchen" -> view.onKitchenPage()
        }
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

        if(intent.getSerializableExtra(AppConstant.sales) != null){
            this.sales = intent.getSerializableExtra(AppConstant.sales) as ArrayList<SalesSQL>
            this.salesdata = intent.getSerializableExtra(AppConstant.salesData) as SalesDataSQL
            loadOffline()
        }else{
            loadDetail()
        }
    }

    fun loadOffline(){
        val decimal = AppConstant.DECIMAL.getDecimalData()
        val struk = DetailTransaction.Struk()
        struk.set(
            name_store = this.salesdata.name_store,
            email = this.salesdata.email_store,
            nohp = this.salesdata.nohp_store,
            address = this.salesdata.address_store,
            no_invoice = this.salesdata.no_invoice,
            operator = this.salesdata.operator,
            id_customer = this.salesdata.id_customer,
            name_customer = this.salesdata.name_customer,
            id_supplier = this.salesdata.id_supplier,
            name_supplier =this.salesdata.name_supplier,
            date = this.salesdata.date,payment = this.salesdata.payment,
            totalorder = this.salesdata.totalorder,
            totalpay = this.salesdata.totalpay,
            totallast = this.salesdata.totalprice,
            changepay=this.salesdata.changepay,
            status = this.salesdata.status,
            note = this.salesdata.note,
            due_date =this.salesdata.due_date,
            service_charge = this.salesdata.service_charge,
            discount = this.salesdata.discount,
            tax = this.salesdata.tax,
            footer = this.salesdata.footer,
            img = this.salesdata.img,
            id_table = this.salesdata.id_table
        )

        val list = mutableListOf<DetailTransaction.Data>()

        for (item in sales){
            var dat = DetailTransaction.Data();
            dat.set(
                codeproduct = item.codeproduct,name_product = item.name_product,date = item.date,amount = item.amount,price = item.price,totalprice = item.totalprice,status = item.status
            )
            list.add(dat)
        }

        val data = list
        val pelanggan = struk?.name_customer
        val table = struk?.name_table
        val supplier = struk?.name_supplier
        val operator = struk?.operator
        var bayar = struk?.totalpay
        var kembalian = struk?.changepay
        var service = struk?.service_charge
        var tax = struk?.tax
        var discount = struk?.discount
        var id_table = struk?.id_table
        var sisaHutang:String? = null
        when {
            "cancel" == struk?.status -> {
                bayar = null
                kembalian = null
            }
            else -> {
                if(typeTRX == AppConstant.Code.CODE_TRANSACTION_SUPPLIER){
                    bayar = null
                    kembalian = null
                }
                else{

                    if(decimal=="No Decimal") {
                        bayar = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(salesdata.totalpay)
                    }else{
                        bayar = AppConstant.CURRENCY.getCurrencyData() + salesdata.totalpay
                    }

                    if(kembalian.isNullOrEmpty() || kembalian.isNullOrBlank() || kembalian == "0"){
                        kembalian = null
                    }
                    else{
                        if(decimal=="No Decimal") {
                            kembalian = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(kembalian)
                        }else{
                            kembalian = AppConstant.CURRENCY.getCurrencyData() + kembalian
                        }
                    }
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
            view.setInfo(
                context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!,
                AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(struk.totalorder!!),
                AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(struk.totallast!!),
                Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd","dd MMMM yyyy"),
                operator,
                pelanggan,
                table,
                supplier,
                struk.payment!!,
                struk.status!!,
                bayar,
                kembalian,
                service,
                tax,
                discount,
                sisaHutang,
                struk.name_store,
                struk.address!!,
                struk.email!!,
                struk.nohp!!,
                struk.footer!!,
                struk.img!!,
                struk.id_table!!,
                context.getString(R.string.lbl_detail_id)+" "+ struk.no_invoice!!,
                struk.due_date,
                struk.note,
                struk.ongkir,
                struk.divisi
            )
        }else{
            view.setInfo(
                context.getString(R.string.lbl_detail_id)+" "+ struk.no_invoice!!,
                AppConstant.CURRENCY.getCurrencyData() + struk.totalorder!!,
                AppConstant.CURRENCY.getCurrencyData() + struk.totallast!!,
                Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd","dd MMMM yyyy"),
                operator,
                pelanggan,
                table,
                supplier,
                struk.payment!!,
                struk.status!!,
                bayar,
                kembalian,
                service,
                tax,
                discount,
                sisaHutang,
                struk.name_store,
                struk.address!!,
                struk.email!!,
                struk.nohp!!,
                struk.footer!!,
                struk.img!!,
                struk.id_table!!,
                context.getString(R.string.lbl_detail_id)+" "+ struk.no_invoice!!,
                struk.due_date,
                struk.note,
                struk.ongkir,
                struk.divisi
            )
        }


        view.enableBtn(struk.status)

        view.setProducts(data)

        view.hideShowActionButton(View.VISIBLE)
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

    override fun onSuccessOrder(order: Order) {
        view.hideLoadingDialog()
        if(order.invoice == null){
            view.showMessage(999,"Invoice number not found")
            return
        }
        this.order = order
        if(isNonTunai){
            val url = AppConstant.URL.NONTUNAIPAY+interactor.getToken(context)+"&no_invoice="+order.invoice
            view.openPaymentNonTunai(url,order.invoice!!)
        }
        else{
            view.openSuccessPage(order.invoice!!)
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
        val pelanggan = struk?.name_customer
        val table = struk?.name_table
        val supplier = struk?.name_supplier
        val operator = struk?.operator
        var bayar = struk?.totalpay
        var kembalian = struk?.changepay
        var service = struk?.service_charge
        var tax = struk?.tax
        var discount = struk?.discount
        var sisaHutang:String? = null
        val img = struk?.img
        when {
            "cancel" == struk?.status -> {
                bayar = null
                kembalian = null
            }
            "debt" == struk?.status -> {
                if(bayar.isNullOrEmpty() || bayar.isNullOrBlank() || bayar == "0"){
                    bayar = null
                }
                else{
                    if(decimal=="No Decimal") {
                        bayar = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(bayar)
                    }else{
                        bayar = AppConstant.CURRENCY.getCurrencyData() + bayar
                    }
                }
                kembalian = null
                val tagihan = struk.changepay!!.toDouble()*-1
                if(tagihan > 0){
                    if(decimal=="No Decimal") {
                        sisaHutang = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(tagihan)
                    }else{
                        sisaHutang = AppConstant.CURRENCY.getCurrencyData() + tagihan
                    }
                }
            }
            else -> {
                if(typeTRX == AppConstant.Code.CODE_TRANSACTION_SUPPLIER){
                    bayar = null
                    kembalian = null
                }
                else{
                    bayar = "0"
                    struk?.totalpay?.let {

                        if(decimal=="No Decimal") {
                            bayar = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it)
                        }else{
                            bayar = AppConstant.CURRENCY.getCurrencyData() + it
                        }
                    }

                    if(kembalian.isNullOrEmpty() || kembalian.isNullOrBlank() || kembalian == "0"){
                        kembalian = null
                    }
                    else{
                        if(decimal=="No Decimal") {
                            kembalian = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(kembalian)
                        }else{
                            kembalian = AppConstant.CURRENCY.getCurrencyData() + kembalian
                        }
                    }
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
            view.setInfo(context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!,AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(struk.totalorder!!),
                AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(struk.totallast!!), Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd hh:mm","dd MMMM yyyy hh:mm"),
                operator,pelanggan,table,supplier,struk.payment!!,struk.status!!,bayar,kembalian,service,tax,discount,sisaHutang,struk.name_store!!,struk.address!!,struk.email!!,struk.nohp!!,struk.footer!!,img,struk.id_table!!,struk.link_order!!,struk.due_date!!,struk.note!!,AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(struk.ongkir!!), struk.divisi)
        }else{
            view.setInfo(context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!,AppConstant.CURRENCY.getCurrencyData() + struk.totalorder!!,
                AppConstant.CURRENCY.getCurrencyData() + struk.totallast!!, Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd hh:mm","dd MMMM yyyy hh:mm"),
                operator,pelanggan,table,supplier,struk.payment!!,struk.status!!,bayar,kembalian,service,tax,discount,sisaHutang,struk.name_store!!,struk.address!!,struk.email!!,struk.nohp!!,struk.footer!!,img,struk.id_table!!,struk.link_order!!,struk.due_date!!,struk.note!!,AppConstant.CURRENCY.getCurrencyData() + struk.ongkir!!, struk.divisi)
        }


        view.enableBtn(struk.status)

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
    override fun finishDetail() {
        if(AppConstant.Code.CODE_TRANSACTION_CUSTOMER == typeTRX){
            interactor.callFinishDetailAPI(context,restModel,id!!)
        }
        else{
            interactor.callSupplierDeleteDetailAPI(context,restModel,id!!)
        }

    }

    override fun onSuccessCheckDiscount(data: List<DetailPayment>?) {
        view.hideLoadingDialog()
        data?.let {list ->
            if(list.isNotEmpty()){
                val detail = list[0]

            }
        }
    }

    override fun checkDiscount() {
        view.showLoadingDialog()
        interactor.callCheckDiscountAPI(context,transactionRestModel,total,discount?.id_discount)
    }

    override fun updateDiscount(data: Discount?) {
        discount = data
        view.setDiscount(data)
        checkDiscount()
    }
    override fun updateNonTunai(data: NonTunai?) {
        nonTunai = data
        view.setNonTunai(data)
    }

    override fun countNon() {
        val total = view.getTotalValue()
        val paynon = view.getPayNon()
        if(paynon == 0.0){
            view.hideShowNon(View.GONE)
            return
        }
        val cashnon = total - paynon
        view.setCashNon(cashnon)

    }
    override fun onSuccessFinishDetail(message: String?) {
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

    override fun onShare() {
        data?.let { data ->
            if(!data.struk?.link_order.isNullOrEmpty() && !data.struk?.link_order.isNullOrBlank()){
                view.openShare(data.struk?.link_order!!)
            }
        }
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

    override fun checkTunai(detail: DetailTransaction?) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        val struk = data?.struk
        val no_invoice = struk?.no_invoice
        val pay = view.getPayValue()
        if(pay == 0.0){
            view.showMessage(999,"The money received cannot be empty")
            return
        }
        val totalValue = view.getTotalValue()
        val cashback = totalValue - pay
        if(cashback > 0){
            if(decimal=="No Decimal") {
                view.showMessage(999,"Insufficient payment ${Helper.convertToCurrency(cashback)}")
                return
            }else{
                view.showMessage(999,"Insufficient payment ${cashback}")
                return
            }

        }
        req = RequestTransaction()
        req.payment_type = 1
        req.payment_amount = pay.toInt()
        req.id = no_invoice

        if(discount != null){
            req.id_discount = discount?.id_discount
        }
        isNonTunai = false

        interactor.callPayOrderAPI(context,transactionRestModel,req,note = String(),id = String())

    }

    override fun checkNonTunai(detail: DetailTransaction?) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        val paynon = view.getPayNon()
        if(paynon == 0.0){
            view.showMessage(999,"Non-cash payments cannot be empty")
            return
        }
        val totalValue = view.getTotalValue()
        if(paynon > totalValue){
            if(decimal=="No Decimal") {

                view.showMessage(999,"Your maximum payment is ${AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(totalValue)}")
                return
            }else{

                view.showMessage(999,"Your maximum payment is ${AppConstant.CURRENCY.getCurrencyData() + totalValue}")
                return
            }
        }


        val struk = data?.struk
        val no_invoice = struk?.no_invoice
        val pay = view.getPayValue()

        val note = view.getNoteValue()

        val cashnon = totalValue - paynon

        req = RequestTransaction()
        req.payment_type = 2
        req.payment_amount = cashnon.toInt()
        req.id = no_invoice
        req.card = nonTunai?.name_link
        req.note = note
        if(discount != null){
            req.id_discount = discount?.id_discount
        }
        isNonTunai = true

        interactor.callPayOrderAPI(context,transactionRestModel,req,note = String(),id = String())

    }
}