package id.kasirvippro.android.feature.manage.packages.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.transaction.Order
import id.kasirvippro.android.models.transaction.RequestPackages
import id.kasirvippro.android.models.transaction.TransactionRestModel


interface AddPackagesContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun openScanPage()
        fun openChooseProduct()
        fun showContentView()
        fun showErrorView(err:String)
        fun setCartText(nominal:String)
        fun addCart(data: Cart)
        fun updateCart(cart:Cart,position: Int)
        fun deleteCart(position: Int)
        fun showTunaiView()
        fun setStoreName(data:Store?)
        fun openChooseStore()
        fun openSuccessPage(id:String)
        fun openCountDialog(selected: Cart, pos: Int)
        fun openHistoryKulakan()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheckScan()
        fun checkCart(data:Product)
        fun addCart(data:Product)
        fun increaseCart(data:Cart,position:Int)
        fun decreaseCart(data: Cart,position:Int)
        fun deleteCart(data: Cart,position:Int)
        fun updateCart(data: Cart,position:Int)
        fun countCart()
        fun searchByBarcode(search:String)
        fun updateStore(data:Store?)
        fun checkAdd(id:String, price:String)
        fun getCartsSize():Int
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callSearchByBarcodeAPI(context: Context, restModel: ProductRestModel, search:String)
        fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestPackages)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessByBarcode(list: List<Product>)
        fun onSuccessOrder(message: Order)
        fun onFailedAPI(code:Int,msg:String)
        fun onFailedBarcode(code: Int, msg: String)
    }


}