package id.kasirvippro.android.feature.choose.product

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel


interface ChooseProductContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun setData(list:List<Product>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun onSelected(data:Product)
        fun openAddProduct()
        fun checkStockProducts(isCheck:Boolean)
        fun openProducts(title:String, list: List<Product>, selected: Product?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun loadData()
        fun loadProducts(page: Int?)
        fun searchProduct(search:String)
        fun onCheckVariable(id_product:String)
        fun setSelectedProduct(data: Product)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetProductsAPI(context: Context, restModel:ProductRestModel,haveStok:String, page: Int?)
        fun callSearchProductAPI(context: Context, restModel:ProductRestModel, search:String,haveStok:String)
        fun callGetProductsVariableAPI(context: Context, restModel:ProductRestModel,id_product:String,haveStok:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetProducts(list:List<Product>)
        fun onSuccessGetProductsVariable(list:List<Product>)
        fun onFailedAPI(code:Int,msg:String)
    }


}