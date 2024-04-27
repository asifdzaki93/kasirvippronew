package id.kasirvippro.android.feature.report.rawMaterial.daily

import android.content.Context
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.report.HistoryRawMaterial
import id.kasirvippro.android.models.report.ReportRawMaterial
import id.kasirvippro.android.models.report.ReportRestModel

interface ListRawMaterialContract {

    interface View : BaseViewImpl {
        fun reloadData()
        fun setList(list:List<ReportRawMaterial>)
        fun showErrorMessage(code: Int, msg: String)
        fun openDetail(id:String)
        fun onFilterStatusSelected(data: DialogModel?)
        fun openFilter(data:FilterDialogDate?)
        fun getParentLayout(): NestedScrollView
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadTransaction()
        fun onSearch(id:String)
        fun getFilterSelected():DialogModel
        fun getFilters():ArrayList<DialogModel>
        fun onChangeStatus(selected: DialogModel?)
        fun setFilterDateSelected(data: FilterDialogDate?)
        fun getFilterDateSelected(): FilterDialogDate?
        fun onCheckShare()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetHistoryAPI(context: Context, restModel: ReportRestModel,awal:String,akhir:String,status:String)
        fun callGetSearchAPI(context: Context, restModel: ReportRestModel,id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetHistory(list:List<HistoryRawMaterial>?)
        fun onSuccessGetSearch(list:List<ReportRawMaterial>?)
        fun onFailedAPI(code:Int,msg:String)
    }


}