package id.kasirvippro.android.feature.report.transaction

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.models.report.ReportTransaksi


interface TransactionContract {

    interface View : BaseViewImpl {
        fun setData(list:List<ReportTransaksi>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openFilterDateDialog()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
        fun search(search:String)
        fun sort()
        fun onChangeDate(firstDate: CalendarDay?, lastDate: CalendarDay?)
        fun getToday():CalendarDay?
        fun getFirstDate():CalendarDay?
        fun getLastDate():CalendarDay?
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetReportsAPI(context: Context, restModel:ReportRestModel,awal:String,akhir:String)
        fun callSearchAPI(context: Context, restModel:ReportRestModel, search:String)
        fun callSortAPI(context: Context, restModel:ReportRestModel,awal:String,akhir:String)

    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetReports(list:List<ReportTransaksi>)
        fun onFailedAPI(code:Int,msg:String)
    }


}