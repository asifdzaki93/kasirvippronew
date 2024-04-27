package id.kasirvippro.android.feature.manage.packages.item

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.packages.PackagesRestModel


interface PackagesItemContract {

    interface View : BaseViewImpl {
        fun setCategorys(list:List<Product>)
        fun setData(list:List<Product>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openAddCategoryPage()
        fun getSesi(): String?
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadProduct()
        fun deleteProduct(id:String,position:Int)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetProductAPI(context: Context, restModel:PackagesRestModel, sesi:String)
        fun callDeleteProductAPI(context: Context, restModel:PackagesRestModel, id:String,sesi:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetCategories(list:List<Product>)
        fun onSuccessDeleteCategory(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}