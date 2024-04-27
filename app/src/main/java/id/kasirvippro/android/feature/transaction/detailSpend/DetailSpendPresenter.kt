package id.kasirvippro.android.feature.transaction.detailSpend

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.PermissionUtil
import id.kasirvippro.android.R
import id.kasirvippro.android.callback.PermissionCallback
import org.greenrobot.eventbus.EventBus
import id.kasirvippro.android.events.onReloadTransaction
import id.kasirvippro.android.models.transaction.DetailSpend
import id.kasirvippro.android.utils.BluetoothUtil
import id.kasirvippro.android.utils.*


class DetailSpendPresenter(val context: Context, val view: DetailSpendContract.View) : BasePresenter<DetailSpendContract.View>(),
    DetailSpendContract.Presenter, DetailSpendContract.InteractorOutput {

    private var interactor = DetailSpendInteractor(this)
    private var restModel = TransactionRestModel(context)
    private val permissionUtil = PermissionUtil(context)
    private var data: DetailSpend? = null
    private var id: String? = null
    private lateinit var bluetoothPermission:PermissionCallback
    private lateinit var storagePermission: PermissionCallback

    override fun onViewCreated(intent: Intent) {

        bluetoothPermission = object : PermissionCallback{
            override fun onSuccess() {

                if(BluetoothUtil.isBluetoothOn()){

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
        loadDetail()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadDetail() {
        interactor.callGetDetailAPI(context,restModel,id!!)
    }

    override fun onSuccessGetDetail(detail: DetailSpend?) {
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(detail == null){
            onFailedAPI(999,"No data available")
            return
        }
        this.data = detail
        val struk = detail.info
        val data = detail.data
        val user = struk?.oleh
        val img = struk?.img

        if(decimal=="No Decimal") {
            view.setInfo(context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!,AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(struk.totalnominal!!),
                Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd","dd MMMM yyyy"), user,img)

        }else{
            view.setInfo(context.getString(R.string.lbl_detail_id)+" "+ struk?.no_invoice!!,AppConstant.CURRENCY.getCurrencyData() + struk.totalnominal!!,
                Helper.getDateFormat(context,struk.date!!,"yyyy-MM-dd","dd MMMM yyyy"), user,img)

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
        interactor.callDeleteDetailAPI(context,restModel,id!!)
    }

    override fun onCheckBluetooth() {
        permissionUtil.checkBluetoothPermission(bluetoothPermission)
    }

    override fun onCheckShare() {
        permissionUtil.checkWriteExternalPermission(storagePermission)
    }

    override fun getDataStruk():DetailSpend {
        return data!!
    }
}