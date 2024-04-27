package id.kasirvippro.android.feature.report.mutasi

import android.content.Context
import android.content.Intent
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.report.ReportMutasi
import id.kasirvippro.android.models.report.ReportRestModel

class MutasiPresenter(val context: Context, val view: MutasiContract.View) : BasePresenter<MutasiContract.View>(),
    MutasiContract.Presenter,
    MutasiContract.InteractorOutput {

    private var interactor = MutasiInteractor(this)
    private var restModel = ReportRestModel(context)
    private var firstDate: CalendarDay?= null
    private var lastDate: CalendarDay?= null
    private var today: CalendarDay?= null


    override fun onViewCreated(intent: Intent) {
        val now = org.threeten.bp.LocalDate.now()
        today = CalendarDay.from(now)
        val yesterday = today?.date!!.minusDays(1)
        firstDate =  CalendarDay.from(yesterday)
        lastDate = today
        view.setDate(firstDate?.date.toString(),lastDate?.date.toString())
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadData() {
        interactor.callGetReportsAPI(context,restModel,firstDate?.date.toString(),lastDate?.date.toString())
    }

    override fun onSuccessGetReports(data: ReportMutasi) {
        view.setData(data)
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
}