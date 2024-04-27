package id.kasirvippro.android.feature.transaction.detailReturnTransfer

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.PermissionUtil
import id.kasirvippro.android.R
import id.kasirvippro.android.callback.PermissionCallback
import org.greenrobot.eventbus.EventBus
import id.kasirvippro.android.events.onReloadTransaction
import id.kasirvippro.android.utils.BluetoothUtil
import id.kasirvippro.android.utils.*



class DetailReturnPresenter(val context: Context, val view: DetailReturnContract.View) : BasePresenter<DetailReturnContract.View>(),
    DetailReturnContract.Presenter, DetailReturnContract.InteractorOutput {

    private var interactor = DetailReturnInteractor(this)
    private var restModel = TransactionRestModel(context)
    private val permissionUtil = PermissionUtil(context)
    private var data: DetailTransaction? = null
    private var id: String? = null
    private lateinit var bluetoothPermission:PermissionCallback
    private var typeTRX = AppConstant.Code.CODE_TRANSACTION_CUSTOMER
    private var openMain = false
    private lateinit var storagePermission: PermissionCallback
    private var premium:Boolean = false


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
                operator,pelanggan,struk.payment!!,struk.status!!,bayar,kembalian,service,tax,discount,sisaHutang,struk.name_store!!,struk.address!!,struk.email!!,struk.nohp!!,struk.footer!!,img)
        }else{
            view.setInfo(context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!,AppConstant.CURRENCY.getCurrencyData() + struk.totalorder!!,
                AppConstant.CURRENCY.getCurrencyData() + struk.totallast!!, Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd hh:mm","dd MMMM yyyy hh:mm"),
                operator,pelanggan,struk.payment!!,struk.status!!,bayar,kembalian,service,tax,discount,sisaHutang,struk.name_store!!,struk.address!!,struk.email!!,struk.nohp!!,struk.footer!!,img)
        }



        if (struk != null) {
            view.enableBtn(struk.status)
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
}