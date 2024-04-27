package id.kasirvippro.android.feature.addOn.recipe.main

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.transaction.Order
import id.kasirvippro.android.models.transaction.RequestTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel


interface RecipeContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun openScanPage()
        fun openChooseProduct()
        fun showContentView()
        fun showErrorView(err:String)
        fun setCartText(nominal:String)
        fun addCart(data: Cart)
        fun getTotalValue():Double
        fun updateCart(cart:Cart,position: Int)
        fun deleteCart(position: Int)
        fun showTunaiView()
        fun setCustomerName(data:Customer?)
        fun openChooseCustomer()
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
        fun updateCustomer(data: Customer?)
        fun checkTunai(complaint:String, advice:String)
        fun getCartsSize():Int
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callSearchByBarcodeAPI(context: Context, restModel: ProductRestModel, search:String)
        fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestTransaction)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessByBarcode(list: List<Product>)
        fun onSuccessOrder(message: Order)
        fun onFailedAPI(code:Int,msg:String)
        fun onFailedBarcode(code: Int, msg: String)
    }


}