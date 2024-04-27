package id.kasirvippro.android.feature.report.penjualan

import android.content.Context
import androidx.core.widget.NestedScrollView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.report.ReportPenjualan
import id.kasirvippro.android.models.report.ReportRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel

interface PenjualanContract {

    interface View : BaseViewImpl {
        fun setData(data:ReportPenjualan)
        fun showErrorMessage(code: Int, msg: String)
        fun reloadData()
        fun openFilterDateDialog()
        fun setList(list:List<ReportPenjualan.Penjualan>?)
        fun setStoreName(value:String)
        fun openStores(title:String, list: List<DialogModel>, selected: DialogModel?)
        fun getParentLayout(): NestedScrollView
        fun openPrinterPage(data: ReportPenjualan)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onDestroy()
        fun onViewCreated()
        fun loadData()
        fun onChangeDate(firstDate: CalendarDay?, lastDate: CalendarDay?)
        fun getToday(): CalendarDay?
        fun getFirstDate(): CalendarDay?
        fun getLastDate(): CalendarDay?
        fun onCheckStore()
        fun setSelectedStore(data: DialogModel)
        fun onCheckShare()
        fun onCheckDownload()
        fun onCheckBluetooth()
        fun getData()
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
        fun onSuccessGetReports(data:ReportPenjualan)
        fun onSuccessGetReports2(data:ReportPenjualan)
        fun onSuccessGetStore(list:List<Store>)

        fun onFailedAPI(code:Int,msg:String)
    }


}