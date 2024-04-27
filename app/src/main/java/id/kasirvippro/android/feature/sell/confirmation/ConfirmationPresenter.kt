package id.kasirvippro.android.feature.sell.confirmation

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.widget.Toast
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.feature.transaction.success.SuccessActivity
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.price.Price
import id.kasirvippro.android.models.price.PriceRestModel
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.transaction.*
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.SalesDataSQL
import id.kasirvippro.android.sqlite.Model.SalesSQL
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.PermissionUtil
import mumayank.com.airlocationlibrary.AirLocation
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ConfirmationPresenter(val context: Context, val view: ConfirmationContract.View) : BasePresenter<ConfirmationContract.View>(),
    ConfirmationContract.Presenter, ConfirmationContract.InteractorOutput {

    private var interactor = ConfirmationInteractor(this)
    private var transactionRestModel = TransactionRestModel(context)
    private var priceRestModel = PriceRestModel(context)
    private var carts:HashMap<String,Cart> = HashMap()
    private var customer:Customer?=null
    private var discount:Discount?=null
    private var price:Price?=null
    private var nonTunai:NonTunai?=null
    private var supplier: Supplier?=null
    private var date:CalendarDay?=null
    private var total = 0.0
    private var req = RequestTransaction()
    private lateinit var locationPermission: PermissionCallback
    private var airLocation: AirLocation? = null
    private var permissionUtil = PermissionUtil(context)
    private var isNonTunai = false
    private var order:Order?=null
    private var table:Table?=null

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

                            var connectivity : ConnectivityManager? = null
                            var info : NetworkInfo? = null
                            connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                                    as ConnectivityManager

                            if ( connectivity != null) {
                                info = connectivity!!.activeNetworkInfo
                                if (info != null) {
                                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                                        interactor.callOrderAPI(context,transactionRestModel,req,note = String())
                                    }else{
                                        OrderOffline()
                                    }
                                }else{
                                    OrderOffline()
                                }
                            }else{
                                OrderOffline()
                            }
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
        updateDiscount(null)
        updatePrice(null)
    }

    fun OrderOffline(){
        val dataManager = DataManager (context)

        req.key = AppSession().getToken(context)

        val name_customer = req.name_customer
        val id_pelanggan = req.id_customer
        val jatuh_tempo = req.due_date
        val jumlah_pembayaran =  req.payment_amount
        val total_order = req.total_order
        val key = req.key
        val tipe_pembayaran = req.payment_type
        val service_charge = 0
        val pajak = 0
        val id_diskon = req.id_discount
        val card = req.card
        val latitude = req.latitude
        val longitude = req.longitude
        val keterangan = req.note

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)

        val tanggal = formatted

        val dt = dataManager.store("1")
        val user = dataManager.userKey(key!!)

        var hargasetelahdiskon =0
        var diskon = 0
        if(id_diskon != null){
            val d = dataManager.discountID(id_diskon!!)

            if (d!!.type === "prosentase") {
                val diskons = total_order!! * d!!.nominal.toInt() / 100
                diskon = diskons
            } else {
                diskon = d!!.nominal.toInt()
            }
            hargasetelahdiskon = total_order!! - diskon
        }else{
            hargasetelahdiskon = total_order!!
        }

        var nominalscc = hargasetelahdiskon!! * dt!!.service_charge.toInt() / 100
        var nominalsc = nominalscc
        var total_service_charge = hargasetelahdiskon + nominalsc

        var nominalpajaks = total_service_charge * dt.tax.toInt() / 100
        var nominalpajak = nominalpajaks
        var total_bayar = total_service_charge + nominalpajak

        if (user!! != null){
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("ddmmss")
            val formatted = current.format(formatter)

            var date = formatted

            var nama = dt.name_store
            var arr = nama.split(" ").toTypedArray()
            var singkatan = ""

            for (kata in arr) {
                singkatan = singkatan + kata.first().toString()
            }

            var kode = singkatan.toUpperCase()
            var no_invoice = kode + "-" + "OFF" + date

            var uniqueID = UUID.randomUUID().toString()

            if(tipe_pembayaran==1){
                var pembayaran= "cash"
                var status= "paid off"
                var dibayar= jumlah_pembayaran

                var kembalian    = dibayar!!-total_bayar
                var i = 1
                val list: ArrayList<SalesSQL> = ArrayList()
                for (pro in req!!.product!!){
                    var id_barang            = pro.id_product
                    var jumlah_barang        = pro.amount_product
                    var catatan            = pro.notes

                    var b = dataManager.productID(id_barang!!)

                    var totalharga = b!!.jual.toInt() * jumlah_barang!!
                    var stok = b!!.stok.toInt() - jumlah_barang!!

                    var sales = SalesSQL(
                        increment = i.toString(),
                        uuid_salesData = uniqueID.toString(),
                        id_sales = i.toString(),
                        id_customer = id_pelanggan.toString(),
                        id_product = id_barang,
                        codeproduct = b.kode,
                        name_product = b.nama,
                        id_store = user.id_store,
                        user = user.phone,
                        no_invoice = no_invoice,
                        amount = jumlah_barang.toString(),
                        price = b.jual,
                        totalprice = totalharga.toString(),
                        hpp = "",
                        totalcapital = "",
                        date = tanggal,
                        status = status,
                        note = "",
                        rest_stock = "",
                        sift = "",
                        station = ""
                    )
                    dataManager.addSales(sales);
                    list.add(sales)
                    i++
                }
                var salesData = SalesDataSQL(
                    increment = "0",
                    uuid = uniqueID.toString(),
                    id_sales_data = "0",
                    user = user.phone,
                    id_customer = id_pelanggan.toString(),
                    name_customer = name_customer.toString(),
                    id_store = user.id_store,
                    name_store = dt.name_store,
                    nohp_store = dt.nohp,
                    email_store = dt.email,
                    address_store = dt.address,
                    id_supplier = supplier?.id_supplier.toString(),
                    name_supplier = supplier?.name_supplier.toString(),
                    no_invoice = no_invoice.toString(),
                    date = tanggal, payment = pembayaran,
                    note = "",
                    totalorder = total_bayar.toString(),
                    totalprice = total_order.toString(),
                    totalpay = dibayar.toString(),
                    changepay = kembalian.toString(),
                    status = status.toString(),
                    footer = dt.footer,
                    due_date = jatuh_tempo.toString(),
                    tax = nominalpajak.toString(),
                    discount = diskon.toString(),
                    service_charge = nominalsc.toString(),
                    operator = user.name,
                    location = (latitude.toString() + " , "+ longitude.toString()),
                    total_sales_hpp = "",
                    sift = "", station = "", img = dt.photo, id_table = ""
                )
                dataManager.addSalesData(salesData)

                val intent = Intent(context, SuccessActivity::class.java)
                intent.putExtra(AppConstant.DATA,no_invoice)
                intent.putExtra(AppConstant.sales,list)
                intent.putExtra(AppConstant.salesData,salesData)
                context.startActivity(intent)
            }else if(tipe_pembayaran==2){
                var pembayaran= "non cash"
                var status= "paid off"
                var dibayar= jumlah_pembayaran

                var kembalian    = 0
                var i = 1
                val list: ArrayList<SalesSQL> = ArrayList()
                for (pro in req!!.product!!){
                    var id_barang            = pro.id_product
                    var jumlah_barang        = pro.amount_product
                    var catatan            = pro.notes

                    var b = dataManager.productID(id_barang!!)

                    var totalharga = b!!.jual.toInt() * jumlah_barang!!
                    var stok = b!!.stok.toInt() - jumlah_barang!!

                    var sales = SalesSQL(
                        increment = i.toString(),
                        uuid_salesData = uniqueID.toString(),
                        id_sales = i.toString(),
                        id_customer = id_pelanggan.toString(),
                        id_product = id_barang,
                        codeproduct = b.kode,
                        name_product = b.nama,
                        id_store = user.id_store,
                        user = user.phone,
                        no_invoice = no_invoice,
                        amount = jumlah_barang.toString(),
                        price = b.jual,
                        totalprice = totalharga.toString(),
                        hpp = "",
                        totalcapital = "",
                        date = tanggal,
                        status = status,
                        note = "",
                        rest_stock = "",
                        sift = "",
                        station = ""
                    )
                    dataManager.addSales(sales);
                    list.add(sales)
                    i++
                }
                var salesData = SalesDataSQL(
                    increment = "0",
                    uuid = uniqueID.toString(),
                    id_sales_data = "0",
                    user = user.phone,
                    id_customer = id_pelanggan.toString(),
                    name_customer = name_customer.toString(),
                    id_store = user.id_store,
                    name_store = dt.name_store,
                    nohp_store = dt.nohp,
                    email_store = dt.email,
                    address_store = dt.address,
                    footer = dt.footer,
                    id_supplier = supplier?.id_supplier.toString(),
                    name_supplier = supplier?.name_supplier.toString(),
                    no_invoice = no_invoice.toString(),
                    date = tanggal, payment = pembayaran,
                    note = card!! + " " + keterangan!!,
                    totalorder = total_bayar.toString(),
                    totalprice = total_order.toString(),
                    totalpay = total_bayar.toString(),
                    changepay = kembalian.toString(),
                    status = status.toString(),
                    due_date = jatuh_tempo.toString(),
                    tax = nominalpajak.toString(),
                    discount = diskon.toString(),
                    service_charge = nominalsc.toString(),
                    operator = user.name,
                    location = (latitude.toString() + " , "+ longitude.toString()),
                    total_sales_hpp = "",
                    sift = "", station = "", img = dt.photo, id_table = table?.id_table!!
                )
                dataManager.addSalesData(salesData)

                val intent = Intent(context, SuccessActivity::class.java)
                intent.putExtra(AppConstant.DATA,no_invoice)
                intent.putExtra(AppConstant.sales,list)
                intent.putExtra(AppConstant.salesData,salesData)
                context.startActivity(intent)
            }else if(tipe_pembayaran==3){
                var pembayaran= "debt"
                var status= "debt"
                var dibayar= jumlah_pembayaran

                var kembalian    = dibayar!!-total_bayar
                var i = 1
                val list: ArrayList<SalesSQL> = ArrayList()
                for (pro in req!!.product!!){
                    var id_barang            = pro.id_product
                    var jumlah_barang        = pro.amount_product
                    var catatan            = pro.notes

                    var b = dataManager.productID(id_barang!!)

                    var totalharga = b!!.jual.toInt() * jumlah_barang!!
                    var stok = b!!.stok.toInt() - jumlah_barang!!

                    var sales = SalesSQL(
                        increment = i.toString(),
                        uuid_salesData = uniqueID.toString(),
                        id_sales = i.toString(),
                        id_customer = id_pelanggan.toString(),
                        id_product = id_barang,
                        codeproduct = b.kode,
                        name_product = b.nama,
                        id_store = user.id_store,
                        user = user.phone,
                        no_invoice = no_invoice,
                        amount = jumlah_barang.toString(),
                        price = b.jual,
                        totalprice = totalharga.toString(),
                        hpp = "",
                        totalcapital = "",
                        date = tanggal,
                        status = status,
                        note = "",
                        rest_stock = "",
                        sift = "",
                        station = ""
                    )
                    dataManager.addSales(sales);
                    list.add(sales)
                    i++
                }
                var salesData = SalesDataSQL(
                    increment = "0",
                    uuid = uniqueID.toString(),
                    id_sales_data = "0",
                    user = user.phone,
                    id_customer = id_pelanggan.toString(),
                    name_customer = name_customer.toString(),
                    id_store = user.id_store,
                    name_store = dt.name_store,
                    nohp_store = dt.nohp,
                    email_store = dt.email,
                    address_store = dt.address,
                    footer = dt.footer,
                    id_supplier = supplier?.id_supplier.toString(),
                    name_supplier = supplier?.name_supplier.toString(),
                    no_invoice = no_invoice.toString(),
                    date = tanggal, payment = pembayaran,
                    note = "",
                    totalorder = total_bayar.toString(),
                    totalprice = total_order.toString(),
                    totalpay = dibayar.toString(),
                    changepay = kembalian.toString(),
                    status = status.toString(),
                    due_date = jatuh_tempo.toString(),
                    tax = nominalpajak.toString(),
                    discount = diskon.toString(),
                    service_charge = nominalsc.toString(),
                    operator = user.name,
                    location = (latitude.toString() + " , "+ longitude.toString()),
                    total_sales_hpp = "",
                    sift = "", station = "", img = dt.photo, id_table = table?.id_table!!
                )
                dataManager.addSalesData(salesData)

                val intent = Intent(context, SuccessActivity::class.java)
                intent.putExtra(AppConstant.DATA,no_invoice)
                intent.putExtra(AppConstant.sales,list)
                intent.putExtra(AppConstant.salesData,salesData)
                context.startActivity(intent)
            }else {
                Toast.makeText(context,"Not Connected",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context,"User Not Found",Toast.LENGTH_SHORT).show()
        }
    }

    override fun countCart() {

        for(cart:Cart in carts.values){
            if(cart.new_price == "0"){
                val sell = cart.product!!.selling_price!!.toDouble()
                val subtotal = cart.count!!*sell
                total += subtotal
            }else{
                val sell = cart.new_price!!.toDouble()
                val subtotal = cart.count!!*sell
                total += subtotal
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
    override fun setSelectedDate(date: CalendarDay?) {
        this.date = date
    }

    override fun getSelectedDate(): CalendarDay? {
        return date
    }

    override fun checkTunai() {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        val pay = view.getPayValue()
        if(pay == 0.0){
            view.showMessage(999,"The money received cannot be empty")
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

        req.payment_type = 1
        req.payment_amount = pay.toInt()
        req.total_order = total.toInt()
        req.product = getBarang()
        if(customer != null){
            req.id_customer = customer?.id_customer
            req.name_customer = customer?.name_customer
        }
        if(supplier != null){
            req.id_supplier = supplier?.id_supplier
            req.name_supplier = supplier?.name_supplier
        }
        req.name_customer = customer?.name_customer
        if(discount != null){
            req.id_discount = discount?.id_discount
        }
        isNonTunai = false
        permissionUtil.checkLocationPermission(locationPermission)

    }

    override fun checkNonTunai(note:String) {

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

        val cashnon = totalValue - paynon


       /* if(nonTunai == null){
            view.showMessage(999,"payment type must be selected")
            return
        }
        if(note.isBlank() || note.isEmpty()){
            view.showMessage(999,"payment details are required")
            return
        }

        */

        val dataManager = DataManager (context)

        req = RequestTransaction()
        val dt = dataManager.store("1")
        req.name_store = dt?.name_store
        req.email_store = dt?.email
        req.address_store = dt?.address
        req.nohp_store = dt?.nohp
        req.footer = dt?.footer
        req.id_table = table?.id_table

        req.payment_type = 2
        req.total_order = total.toInt()
        req.payment_amount = cashnon.toInt()
        req.product = getBarang()
        req.card = nonTunai?.name_link
        req.note = note
        if(customer != null){
            req.id_customer = customer?.id_customer
        }
        if(discount != null){
            req.id_discount = discount?.id_discount
        }
        isNonTunai = true
        permissionUtil.checkLocationPermission(locationPermission)
    }

    override fun checkPiutang() {
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

        val cashdebt = totalValue - paydebt


        if(customer == null){
            view.showMessage(999,"Customer Must be selected")
            return
        }
        if(date == null){
            view.showMessage(999,"Delivery Date is required")
            return
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

        req.payment_type = 3
        req.total_order = total.toInt()
        req.payment_amount = cashdebt.toInt()
        req.id_customer = customer?.id_customer
        req.due_date = date?.date.toString()
        req.product = getBarang()
        if(discount != null){
            req.id_discount = discount?.id_discount
        }
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
            if(cart.note == ""){
                barang.notes = cart.product?.description
            }else{
                barang.notes = cart.note.toString()
            }
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
            val url = AppConstant.URL.NONTUNAIPAY+interactor.getToken(context)+"&no_invoice="+order.invoice
            view.openPaymentNonTunai(url,order.invoice!!)
        }
        else{
            view.openSuccessPage(order.invoice!!)
        }

    }

    override fun onSuccessGetPrice(list: List<Price>) {
        view.setPrice(list)
    }

    override fun getCartsSize():Int {
        return carts.size
    }

    override fun onSuccessCheckDiscount(data: List<DetailPayment>?) {
        view.hideLoadingDialog()
        data?.let {list ->
            if(list.isNotEmpty()){
                val detail = list[0]
                view.setDetailText(detail.totalprice!!,detail.discount,detail.service_charge,detail.tax,detail.totalpay!!)
                countCashback()
                countNon()
                countDebt()
            }
        }
    }

    override fun checkDiscount() {
        view.showLoadingDialog()
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    interactor.callCheckDiscountAPI(context,transactionRestModel,total,discount?.id_discount)
                }else{
                    view.hideLoadingDialog()
                    DiscountOffline()
                }
            }else{
                view.hideLoadingDialog()
                DiscountOffline()
            }
        }else{
            view.hideLoadingDialog()
            DiscountOffline()
        }
    }

    override fun loadPrice() {
        interactor.callGetPriceAPI(context,priceRestModel, total)
    }

    fun DiscountOffline(){

        val dataManager = DataManager (context)
        var id_discount = ""
        if(discount != null){
            id_discount = discount?.id_discount.toString()
        }
        val discounts = dataManager.discountID(id_discount)
        val store = dataManager.store("1");
        val totalprice = total;

        val nominalsc=(total*10/100).toInt().toDouble();

        var discount = 0.0

        if(discounts != null){
            if (discounts.type == "prosentase") {
                discount = (totalprice.toDouble() * discounts.nominal.toDouble() / 100).toInt().toDouble();
            } else {
                discount = discounts!!.nominal.toDouble();
            }
        }
        val service_charge = (total-discount)* store!!.service_charge.toDouble()/100;

        val total_service_charge = ((total-discount) + service_charge).toInt().toDouble()

        val nominalpajak = total_service_charge*store!!.tax.toDouble()/100;
        val tax = (total_service_charge + nominalpajak).toInt().toDouble()
        val totalpay = total_service_charge+nominalpajak;

        view.setDetailText(totalprice,discount,service_charge,nominalpajak,totalpay)
        countCashback()
        countNon()
        countDebt()
    }

    override fun updateDiscount(data: Discount?) {
        discount = data
        view.setDiscount(data)
        checkDiscount()
    }

    override fun updatePrice(data: Price?) {
        price = data
        loadPrice()
    }

    override fun updateNonTunai(data: NonTunai?) {
        nonTunai = data
        view.setNonTunai(data)
    }

    override fun updateTable(data: Table?) {
        table = data
        view.setTableName(data)
    }

    override fun setSelectedPrice(data: Price) {
        view.updatePrice(data.nominal!!)
    }

}