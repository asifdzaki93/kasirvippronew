package id.kasirvippro.android.feature.afiliate.transaction

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.transaction.*

interface TransactionContract {

    interface View : BaseViewImpl {
        fun reloadData()
        fun setList(list:List<DetailCOmmision>)
        fun setCommision(totalcommision:String?)
        fun showErrorMessage(code: Int, msg: String)
        fun onFilterStatusSelected(data: DialogModel?)
        fun openFilter(data:FilterDialogDate?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadTransaction()
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
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetHistory(list:List<Comission>?)
        fun onFailedAPI(code:Int,msg:String)
    }


}