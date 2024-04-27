package id.kasirvippro.android.feature.transaction.detailTransfer

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


class DetailTransferPresenter(val context: Context, val view: DetailTransferContract.View) : BasePresenter<DetailTransferContract.View>(),
    DetailTransferContract.Presenter, DetailTransferContract.InteractorOutput {

    private var interactor = DetailTransferInteractor(this)
    private var restModel = TransactionRestModel(context)
    private val permissionUtil = PermissionUtil(context)
    private var data: DetailTransfer? = null
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

    override fun onSuccessOrder(order: Order) {
        view.hideLoadingDialog()
        if(order.invoice == null){
            view.showMessage(999,"Invoice number not found")
            return
        }
        this.order = order
        if(isNonTunai){
            view.openSuccessPage(order.invoice!!)
        }
        else{
            view.openSuccessPage(order.invoice!!)
        }

    }
    override fun onSuccessGetDetail(detail: DetailTransfer?) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(detail == null){
            onFailedAPI(999,"no data")
            return
        }
        this.data = detail
        val struk = detail.struk
        val data = detail.data
        var bayar = struk?.totalpay
        var kembalian = struk?.changepay
        var sisaHutang:String? = null
        val img = struk?.img
        when {
            "cancel" == struk?.status -> {
                bayar = null
                kembalian = null
            }
            "pending" == struk?.status -> {
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
        if(decimal=="No Decimal") {
            view.setInfo(context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!,AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(struk.totalorder!!),
                AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(struk.totallast!!), Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd hh:mm","dd MMMM yyyy hh:mm"),
                struk.operator!!, struk.payment!!,struk.status!!,bayar,kembalian,sisaHutang,struk.name_store!!,struk.address!!,struk.email!!,struk.nohp!!,img, struk.store_destination)

        }else{
            view.setInfo(context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!,AppConstant.CURRENCY.getCurrencyData() + struk.totalorder!!,
                AppConstant.CURRENCY.getCurrencyData() + struk.totallast!!, Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd hh:mm","dd MMMM yyyy hh:mm"),
                struk.operator!!, struk.payment!!,struk.status!!,bayar,kembalian,sisaHutang,struk.name_store!!,struk.address!!,struk.email!!,struk.nohp!!,img, struk.store_destination)
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
        interactor.callFinishDetailAPI(context,restModel,id!!)
    }

    override fun onSuccessCheckDiscount(data: List<DetailPayment>?) {
        view.hideLoadingDialog()
        data?.let {list ->
            if(list.isNotEmpty()){
                val detail = list[0]

            }
        }
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

    override fun getDataStruk():DetailTransfer {
        return data!!
    }

    override fun getTypeTRX(): Int {
        return typeTRX
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