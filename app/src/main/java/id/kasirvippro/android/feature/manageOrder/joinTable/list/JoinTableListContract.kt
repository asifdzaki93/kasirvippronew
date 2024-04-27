package id.kasirvippro.android.feature.manageOrder.joinTable.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel


interface JoinTableListContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Table>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openEditTablePage(data:Table)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadCategories()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetTableAPI(context: Context, restModel:TableRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetTable(list:List<Table>)
        fun onFailedAPI(code:Int,msg:String)
    }


}