package id.kasirvippro.android.feature.choose.supplier

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.supplier.SupplierRestModel


interface ChooseSupplierContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Supplier>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun onSelected(data:Supplier)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadSuppliers()
        fun searchSupplier(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetSuppliersAPI(context: Context, restModel:SupplierRestModel)
        fun callSearchSupplierAPI(context: Context, restModel:SupplierRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetSuppliers(list:List<Supplier>)
        fun onFailedAPI(code:Int,msg:String)
    }


}