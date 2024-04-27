package id.kasirvippro.android.feature.manageStock.stockHistoryRawMaterial.data

import android.content.Context
import androidx.core.widget.NestedScrollView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.DetailHistory
import id.kasirvippro.android.models.transaction.TransactionRestModel


interface DataHistoryContract {

    interface View : BaseViewImpl {
        fun setProducts(list:List<DetailHistory>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openFilterDateDialog()
        fun getIdProduct(): String?
        fun setInfo(name:String?,date:String)
        fun getParentLayout(): NestedScrollView
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadProducts()
        fun onChangeDate(firstDate: CalendarDay?, lastDate: CalendarDay?)
        fun getToday(): CalendarDay?
        fun getFirstDate(): CalendarDay?
        fun getLastDate(): CalendarDay?
        fun onCheckDownload()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetProductsAPI(context: Context, restModel:TransactionRestModel, id:String, awal:String, akhir:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetProducts(list:List<DetailHistory>)
        fun onSuccessDeleteProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}