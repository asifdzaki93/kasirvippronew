package id.kasirvippro.android.feature.report.produk

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.models.report.ReportProduct

interface ProdukContract {

    interface View : BaseViewImpl {
        fun setData(data: ReportProduct)
        fun setInfo(labarugi: String, rata2: String, amount: String)
        fun showErrorMessage(code: Int, msg: String)
        fun setList(list:List<ReportProduct.Sales>?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openFilterDateDialog()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
        fun onChangeDate(firstDate: CalendarDay?, lastDate: CalendarDay?)
        fun getToday():CalendarDay?
        fun getFirstDate():CalendarDay?
        fun getLastDate():CalendarDay?
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetReportsAPI(context: Context, restModel:ReportRestModel,awal:String,akhir:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetReports(data:ReportProduct)
        fun onFailedAPI(code:Int,msg:String)
    }


}