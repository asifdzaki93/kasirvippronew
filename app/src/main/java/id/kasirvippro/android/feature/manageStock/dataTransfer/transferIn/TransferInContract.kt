package id.kasirvippro.android.feature.manageStock.dataTransfer.transferIn

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.transaction.HistoryTransfer
import id.kasirvippro.android.models.transaction.Transfer
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface TransferInContract {

    interface View : BaseViewImpl {
        fun reloadData()
        fun setList(list: List<Transfer>?)
        fun showErrorMessage(code: Int, msg: String)
        fun openDetail(id:String)
        fun onFilterStatusSelected(data: DialogModel?)
        fun openFilter(data:FilterDialogDate?)
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
        fun onSuccessGetHistory(list:List<HistoryTransfer>?)
        fun onSuccessGetSearch(list:List<Transfer>?)
        fun onFailedAPI(code:Int,msg:String)
    }


}