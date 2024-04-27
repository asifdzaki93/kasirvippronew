package id.kasirvippro.android.feature.report.daily

import android.content.Context
import android.content.Intent
import androidx.core.widget.NestedScrollView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.report.ReportDaily
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel

interface DailyContract {

    interface View : BaseViewImpl {
        fun setData(data:ReportDaily)
        fun showErrorMessage(code: Int, msg: String)
        fun reloadData()
        fun openFilterDateDialog()
        fun setList(list:List<ReportDaily.Daily>?)
        fun setStoreName(value:String)
        fun openStores(title:String, list: List<DialogModel>, selected: DialogModel?)
        fun getParentLayout(): NestedScrollView
        fun openPrinterPage(data:ReportDaily)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onDestroy()
        fun onViewCreated(intent: Intent)
        fun loadData()
        fun onChangeDate(firstDate: CalendarDay?, lastDate: CalendarDay?)
        fun getToday(): CalendarDay?
        fun getFirstDate(): CalendarDay?
        fun getLastDate(): CalendarDay?
        fun onCheckStore()
        fun setSelectedStore(data: DialogModel)
        fun onCheckShare()
        fun onCheckBluetooth()
        fun onCheckDownload()
        fun getDataReport()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserLevel(context: Context):String?
        fun getIdstore(context: Context):String?
        fun callGetReportsAPI(context: Context, restModel: ReportRestModel, awal:String, akhir:String,id:String?)
        fun callGetReports2API(context: Context, restModel: ReportRestModel, awal:String, akhir:String,id:String?)
        fun callGetStoressAPI(context: Context, restModel: StoreRestModel)

    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetReports2(data:ReportDaily)
        fun onSuccessGetReports(data:ReportDaily)
        fun onSuccessGetStore(list:List<Store>)

        fun onFailedAPI(code:Int,msg:String)
    }


}