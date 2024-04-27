package id.kasirvippro.android.feature.report.daily

import android.content.Context
import android.content.Intent
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.report.ReportDaily
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.utils.BluetoothUtil
import id.kasirvippro.android.utils.ImageHelper
import id.kasirvippro.android.utils.PermissionUtil

class DailyPresenter(val context: Context, val view: DailyContract.View) : BasePresenter<DailyContract.View>(),
    DailyContract.Presenter, DailyContract.InteractorOutput {

    private var interactor = DailyInteractor(this)
    private var restModel = ReportRestModel(context)
    private var data: ReportDaily? = null
    private var firstDate: CalendarDay?= null
    private var lastDate: CalendarDay?= null
    private var today: CalendarDay?= null
    private var store:DialogModel? = null
    private var alltoko:DialogModel? = null
    private var storeRestModel = StoreRestModel(context)
    private var stores:ArrayList<DialogModel> = ArrayList()
    private val permissionUtil = PermissionUtil(context)
    private lateinit var storagePermission: PermissionCallback
    private lateinit var downloadPermission: PermissionCallback
    private lateinit var bluetoothPermission:PermissionCallback
    private var level:String? = "master"
    private var idstore:String? = null

    override fun onViewCreated(intent: Intent) {
        level = interactor.getUserLevel(context)
        idstore = interactor.getIdstore(context)
        storagePermission = object :PermissionCallback{
            override fun onSuccess() {
                ImageHelper.takeScreenshot(context,view.getParentLayout())
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_write_external))
            }
        }

        downloadPermission = object :PermissionCallback{
            override fun onSuccess() {
                ImageHelper.downloadScreenshot(context,view.getParentLayout())
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_write_external))
            }
        }

        bluetoothPermission = object : PermissionCallback{
            override fun onSuccess() {

                if(BluetoothUtil.isBluetoothOn()){
                    getDataReport()
                }
                else{
                    BluetoothUtil.openBluetooth(context)
                }

            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_bluetooth))
            }

        }


        val now = org.threeten.bp.LocalDate.now()
        today = CalendarDay.from(now)
        val yesterday = today?.date!!.minusDays(1)
        firstDate =  CalendarDay.from(yesterday)
        lastDate = today
        alltoko = DialogModel()
        alltoko?.value = "All Store"
        store = alltoko
        view.setStoreName(store?.value!!)



        //view.openPrinterPage()

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onCheckBluetooth() {
        permissionUtil.checkBluetoothPermission(bluetoothPermission)
    }

    override fun getDataReport() {
        interactor.callGetReports2API(context,restModel,firstDate?.date.toString(),lastDate?.date.toString(),store?.id)
    }

    override fun loadData() {
        if(level=="master") {
           val id = store?.id
            interactor.callGetReportsAPI(context,restModel,firstDate?.date.toString(),lastDate?.date.toString(),id)
        }else{
            val id = idstore
            interactor.callGetReportsAPI(context,restModel,firstDate?.date.toString(),lastDate?.date.toString(),id)
        }
    }

    override fun onSuccessGetReports(data: ReportDaily) {
        view.setData(data)
    }

    override fun onSuccessGetReports2(data: ReportDaily) {
        view.openPrinterPage(data)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

    override fun onChangeDate(firstDate: CalendarDay?, lastDate: CalendarDay?) {
        firstDate?.let {
            this.firstDate = it
        }

        lastDate?.let {
            this.lastDate = it
        }

        loadData()
    }

    override fun getToday(): CalendarDay? {
        return today
    }

    override fun getFirstDate(): CalendarDay? {
        return firstDate
    }

    override fun getLastDate(): CalendarDay? {
        return lastDate
    }

    override fun onCheckStore() {
        if(stores.isEmpty()){
            interactor.callGetStoressAPI(context,storeRestModel)
        }
        else{
            view.openStores("Select Store",stores,store)
        }
    }

    override fun setSelectedStore(data: DialogModel) {
        store = data
        view.setStoreName(data.value!!)
        loadData()
    }

    override fun onCheckShare() {
        permissionUtil.checkWriteExternalPermission(storagePermission)
    }

    override fun onCheckDownload() {
        permissionUtil.checkWriteExternalPermission(downloadPermission)
    }

    override fun onSuccessGetStore(list: List<Store>) {
        if(list.isEmpty()){
            view.showErrorMessage(999,"There are no stores yet")
            return
        }
        stores = ArrayList()
        for(cat in list){
            val model = DialogModel()
            model.id = cat.id_store
            model.value = cat.name_store
            stores.add(model)
        }
        if(level=="master") {
            stores.add(0, alltoko!!)
        }else{

        }
        view.openStores("Select Store",stores,store)
    }
}