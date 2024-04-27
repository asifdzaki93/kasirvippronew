package id.kasirvippro.android.feature.report.mutasi

import android.content.Context
import android.content.Intent
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.report.ReportMutasi
import id.kasirvippro.android.models.report.ReportRestModel

interface MutasiContract {

    interface View : BaseViewImpl {
        fun setData(data:ReportMutasi)
        fun setDate(firstDate:String,lastDate:String)
        fun showErrorMessage(code: Int, msg: String)
        fun reloadData()
        fun openFilterDateDialog()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onDestroy()
        fun onViewCreated(intent: Intent)
        fun loadData()
        fun onChangeDate(firstDate: CalendarDay?, lastDate: CalendarDay?)
        fun getToday(): CalendarDay?
        fun getFirstDate(): CalendarDay?
        fun getLastDate(): CalendarDay?
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetReportsAPI(context: Context, restModel: ReportRestModel, awal:String, akhir:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetReports(data:ReportMutasi)
        fun onFailedAPI(code:Int,msg:String)
    }


}