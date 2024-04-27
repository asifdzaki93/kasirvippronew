package id.kasirvippro.android.feature.transaction.detailLabel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.DetailLabel
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.PermissionUtil
import id.kasirvippro.android.R
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.sqlite.Model.SalesDataSQL
import id.kasirvippro.android.sqlite.Model.SalesSQL
import id.kasirvippro.android.utils.BluetoothUtil
import id.kasirvippro.android.utils.*



class DetailLabelPresenter(val context: Context, val view: DetailLabelContract.View) : BasePresenter<DetailLabelContract.View>(),
    DetailLabelContract.Presenter, DetailLabelContract.InteractorOutput {

    private var interactor = DetailLabelInteractor(this)
    private var restModel = TransactionRestModel(context)
    private val permissionUtil = PermissionUtil(context)
    private var data: DetailLabel? = null
    private var id: String? = null
    private lateinit var bluetoothPermission:PermissionCallback
    private var typeTRX = AppConstant.Code.CODE_TRANSACTION_CUSTOMER
    private var openMain = false
    private lateinit var storagePermission: PermissionCallback
    private var premium:Boolean = false

    private var sales : ArrayList<SalesSQL> = ArrayList()
    private lateinit var salesdata : SalesDataSQL

    override fun onViewCreated(intent: Intent) {
        premium = "1" == interactor.getUserPaket(context)
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

        if(intent.getSerializableExtra(AppConstant.sales) != null){
            this.sales = intent.getSerializableExtra(AppConstant.sales) as ArrayList<SalesSQL>
            this.salesdata = intent.getSerializableExtra(AppConstant.salesData) as SalesDataSQL
        }else{
            loadDetailLabel()
        }
    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadDetailLabel() {
        interactor.callGetDetailLabelAPI(context,restModel,id!!)
    }

    override fun onSuccessGetDetailLabel(detail: DetailLabel?) {
        if(detail == null){
            onFailedAPI(999,"no data")
            return
        }
        this.data = detail
        val struk = detail.struk
        val data = detail.data

        view.setInfo(context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!)


        view.setProducts(data!!)

        view.hideShowActionButton(View.VISIBLE)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onCheckBluetooth() {
        permissionUtil.checkBluetoothPermission(bluetoothPermission)
    }

    override fun onCheckShare() {
        permissionUtil.checkWriteExternalPermission(storagePermission)
    }

    override fun getDataStruk():DetailLabel {
        return data!!
    }

    override fun getTypeTRX(): Int {
        return typeTRX
    }
    override fun isOpenMain(): Boolean {
        return openMain
    }
}