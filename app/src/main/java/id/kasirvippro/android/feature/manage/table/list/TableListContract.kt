package id.kasirvippro.android.feature.manage.table.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel


interface TableListContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Table>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openAddTablePage()
        fun openEditTablePage(data:Table)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadCategories()
        fun deleteTable(id:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetAllTableAPI(context: Context, restModel:TableRestModel)
        fun callDeleteTableAPI(context: Context, restModel:TableRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetTable(list:List<Table>)
        fun onSuccessDeleteTable(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}