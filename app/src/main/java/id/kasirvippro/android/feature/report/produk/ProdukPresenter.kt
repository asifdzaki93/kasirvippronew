package id.kasirvippro.android.feature.report.produk

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.models.report.ReportProduct

class ProdukPresenter(val context: Context, val view: ProdukContract.View) : BasePresenter<ProdukContract.View>(),
    ProdukContract.Presenter, ProdukContract.InteractorOutput {

    private var interactor = ProdukInteractor(this)
    private var restModel = ReportRestModel(context)
    private var firstDate: CalendarDay?= null
    private var lastDate: CalendarDay?= null
    private var today: CalendarDay?= null

    override fun onViewCreated() {
        val now = org.threeten.bp.LocalDate.now()
        today = CalendarDay.from(now)
        val yesterday = today?.date!!.minusMonths(1)
        firstDate =  CalendarDay.from(yesterday)
        //firstDate =  CalendarDay.from(today?.year!!,today?.month!!-1,today?.day!!)
        lastDate = today
        loadData()
    }

    override fun loadData() {
        interactor.callGetReportsAPI(context,restModel,firstDate?.date.toString(),lastDate?.date.toString())
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetReports(data: ReportProduct) {
        view.setData(data)
        view.setInfo(data.info?.totallaba!!, data.info.rata2!!, data.info.amount!!)
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