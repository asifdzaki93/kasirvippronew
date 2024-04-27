package id.kasirvippro.android.feature.manageStock.stockOpname.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel


interface StockOpnameListContract {

    interface View : BaseViewImpl {
        fun setProducts(list:List<Product>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openEditPage(data: Product)
        fun openScanPage()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadProducts()
        fun searchProduct(search:String)
        fun searchByBarcode(search:String)
        fun onCheckScan()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetProductsAPI(context: Context, restModel:ProductRestModel)
        fun callSearchProductAPI(context: Context, restModel:ProductRestModel, search:String)
        fun callSearchByBarcodeAPI(context: Context, restModel:ProductRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetProducts(list:List<Product>)
        fun onSuccessByBarcode(list: List<Product>)
        fun onFailedAPI(code:Int,msg:String)
    }


}