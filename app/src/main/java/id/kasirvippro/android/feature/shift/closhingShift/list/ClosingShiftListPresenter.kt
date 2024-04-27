package id.kasirvippro.android.feature.shift.closingShift.list

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.closeShift.CloseShift
import id.kasirvippro.android.models.closeShift.CloseShiftRestModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.BluetoothUtil
import id.kasirvippro.android.utils.PermissionUtil
import org.threeten.bp.LocalDate


class ClosingShiftListPresenter(val context: Context, val view: ClosingShiftListContract.View) : BasePresenter<ClosingShiftListContract.View>(),
    ClosingShiftListContract.Presenter, ClosingShiftListContract.InteractorOutput {

    private var interactor = ClosingShiftListInteractor(this)
    private var restModel = CloseShiftRestModel(context)
    private var data:CloseShift? = null
    private var id: CalendarDay?= null
    private var selectedDate:FilterDialogDate?=null
    private lateinit var bluetoothPermission:PermissionCallback
    private val permissionUtil = PermissionUtil(context)

    override fun onViewCreated() {
        val now = LocalDate.now()
        id = CalendarDay.from(now)

        val lastDate = id
        selectedDate = FilterDialogDate()
        selectedDate?.id = AppConstant.FilterDate.DAILY
        selectedDate?.lastDate = lastDate
        loadData()


        bluetoothPermission = object : PermissionCallback {
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
    }

    override fun loadData() {
        interactor.callGetsAPI(context,restModel)
    }

    override fun dailyClose() {
        interactor.callDailyClosingAPI(context,restModel,selectedDate?.lastDate?.date.toString())
    }

    override fun getDataStruk(): CloseShift {
        return data!!
    }

    override fun onCheckBluetooth() {
        permissionUtil.checkBluetoothPermission(bluetoothPermission)
    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessDailyClosing(message: String?) {
        view.showMessage(999,message)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onSuccessGets(list: List<CloseShift>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}