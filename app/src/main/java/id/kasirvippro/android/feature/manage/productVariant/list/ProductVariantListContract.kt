package id.kasirvippro.android.feature.manage.productVariant.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel


interface ProductVariantListContract {

    interface View : BaseViewImpl {
        fun setProducts(list:List<Product>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openEditPage(data: Product)
        fun openScanPage()
        fun getIdProduct(): String?
        fun openAddPage(id: String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadProducts()
        fun deleteProduct(id:String,position:Int,increment:String)
        fun searchProduct(search:String)
        fun searchByBarcode(search:String)
        fun onCheckScan()
        fun onCheckSort()
        fun onAddPage()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetProductsAPI(context: Context, restModel:ProductRestModel, id_product:String)
        fun callDeleteProductAPI(context: Context, restModel:ProductRestModel, id:String)
        fun callSearchProductAPI(context: Context, restModel:ProductRestModel, search:String)
        fun callSearchByBarcodeAPI(context: Context, restModel:ProductRestModel, search:String)
        fun callSortProductsAPI(context: Context, restModel:ProductRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetProducts(list:List<Product>)
        fun onSuccessDeleteProduct(msg: String?)
        fun onSuccessByBarcode(list: List<Product>)
        fun onSuccessSort(list: List<Product>)
        fun onFailedAPI(code:Int,msg:String)
    }


}