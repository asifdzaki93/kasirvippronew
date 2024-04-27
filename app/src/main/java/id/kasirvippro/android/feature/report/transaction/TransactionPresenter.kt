package id.kasirvippro.android.feature.report.transaction

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.models.report.ReportTransaksi

class TransactionPresenter(val context: Context, val view: TransactionContract.View) : BasePresenter<TransactionContract.View>(),
    TransactionContract.Presenter, TransactionContract.InteractorOutput {

    private var interactor = TransactionInteractor(this)
    private var restModel = ReportRestModel(context)
    private var firstDate: CalendarDay?= null
    private var lastDate: CalendarDay?= null
    private var today: CalendarDay?= null

    override fun onViewCreated() {
        val now = org.threeten.bp.LocalDate.now()
        today = CalendarDay.from(now)
        val yesterday = today?.date!!.minusDays(1)
        firstDate =  CalendarDay.from(yesterday)
        lastDate = today
        loadData()
    }

    override fun loadData() {
        interactor.callGetReportsAPI(context,restModel,firstDate?.date.toString(),lastDate?.date.toString())
    }

    override fun search(search: String) {
        interactor.onRestartDisposable()
        if(search.isEmpty()){
            interactor.callGetReportsAPI(context,restModel,firstDate?.date.toString(),lastDate?.date.toString())
        }
        else{
            interactor.callSearchAPI(context,restModel,search)
        }

    }

    override fun sort() {
        interactor.onRestartDisposable()
        interactor.callSortAPI(context,restModel,firstDate?.date.toString(),lastDate?.date.toString())
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetReports(list: List<ReportTransaksi>) {
        view.setData(list)
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