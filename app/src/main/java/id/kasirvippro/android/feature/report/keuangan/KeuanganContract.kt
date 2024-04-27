package id.kasirvippro.android.feature.report.keuangan

import android.content.Context
import android.content.Intent
import androidx.core.widget.NestedScrollView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.report.ReportLabaRugi
import id.kasirvippro.android.models.report.ReportRestModel

interface KeuanganContract {

    interface View : BaseViewImpl {
        fun setData(data: ReportLabaRugi.Keuangan?)
        fun showErrorMessage(code: Int, msg: String)
        fun reloadData()
        fun openFilterDateDialog()
        fun getParentLayout(): NestedScrollView
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onDestroy()
        fun onViewCreated(intent: Intent)
        fun loadData()
        fun onChangeDate(firstDate: CalendarDay?, lastDate: CalendarDay?)
        fun getToday(): CalendarDay?
        fun getFirstDate(): CalendarDay?
        fun getLastDate(): CalendarDay?
        fun onCheckShare()
        fun onCheckDownload()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetReportsAPI(context: Context, restModel: ReportRestModel, awal:String, akhir:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetReports(data:ReportLabaRugi)
        fun onFailedAPI(code:Int,msg:String)
    }


}