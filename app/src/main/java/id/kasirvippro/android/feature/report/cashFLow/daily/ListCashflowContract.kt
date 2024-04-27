package id.kasirvippro.android.feature.report.listCashflow

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.transaction.HistoryFlowCash
import id.kasirvippro.android.models.transaction.FlowCash
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface ListCashflowContract {

    interface View : BaseViewImpl {
        fun reloadData()
        fun setList(list:List<FlowCash>)
        fun showErrorMessage(code: Int, msg: String)
        fun openDetail(id:String)
        fun onFilterStatusSelected(data: DialogModel?)
        fun openFilter(data:FilterDialogDate?)
        fun openMap(data: FlowCash)
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
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetHistoryAPI(context: Context, restModel: TransactionRestModel,awal:String,akhir:String,status:String)
        fun callGetSearchAPI(context: Context, restModel: TransactionRestModel,id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetHistory(list:List<HistoryFlowCash>?)
        fun onSuccessGetSearch(list:List<FlowCash>?)
        fun onFailedAPI(code:Int,msg:String)
    }


}