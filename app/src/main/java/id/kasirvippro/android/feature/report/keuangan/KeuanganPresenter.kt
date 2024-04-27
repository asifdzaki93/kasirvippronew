package id.kasirvippro.android.feature.report.keuangan

import android.content.Context
import android.content.Intent
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.report.ReportLabaRugi
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.utils.ImageHelper
import id.kasirvippro.android.utils.PermissionUtil

class KeuanganPresenter(val context: Context, val view: KeuanganContract.View) : BasePresenter<KeuanganContract.View>(),
    KeuanganContract.Presenter,
    KeuanganContract.InteractorOutput {

    private var interactor = KeuanganInteractor(this)
    private var restModel = ReportRestModel(context)
    private var firstDate: CalendarDay?= null
    private var lastDate: CalendarDay?= null
    private var today: CalendarDay?= null
    private val permissionUtil = PermissionUtil(context)
    private lateinit var storagePermission: PermissionCallback
    private lateinit var downloadPermission: PermissionCallback


    override fun onViewCreated(intent: Intent) {

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

        val now = org.threeten.bp.LocalDate.now()
        today = CalendarDay.from(now)
        val yesterday = today?.date!!.minusDays(1)
        firstDate =  CalendarDay.from(yesterday)
        lastDate = today
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadData() {
        interactor.callGetReportsAPI(context,restModel,firstDate?.date.toString(),lastDate?.date.toString())
    }

    override fun onSuccessGetReports(data: ReportLabaRugi) {
        view.setData(data.financial_statements)
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

    override fun onCheckShare() {
        permissionUtil.checkWriteExternalPermission(storagePermission)
    }

    override fun onCheckDownload() {
        permissionUtil.checkWriteExternalPermission(downloadPermission)
    }
}