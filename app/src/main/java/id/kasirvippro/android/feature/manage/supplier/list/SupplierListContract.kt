package id.kasirvippro.android.feature.manage.supplier.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.supplier.SupplierRestModel


interface SupplierListContract {

    interface View : BaseViewImpl {
        fun setSuppliers(list:List<Supplier>)
        fun setData(list:List<Supplier>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openAddPage()
        fun openEditPage(data:Supplier)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadSuppliers()
        fun deleteSupplier(id:String,position:Int,increment:String)
        fun searchSupplier(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetSuppliersAPI(context: Context, restModel:SupplierRestModel)
        fun callDeleteSupplierAPI(context: Context, restModel:SupplierRestModel, id:String)
        fun callSearchSupplierAPI(context: Context, restModel:SupplierRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetSuppliers(list:List<Supplier>)
        fun onSuccessDeleteSupplier(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}