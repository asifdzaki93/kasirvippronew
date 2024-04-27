package id.kasirvippro.android.feature.sell.main

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.addOn.AddOn
import id.kasirvippro.android.models.addOn.AddOnRestModel
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel


interface SellContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun openScanPage()
        fun openChooseProduct()
        fun showContentView()
        fun showErrorView(err:String)
        fun setCartText(count:String,nominal:String)
        fun addCart(data: Cart)
        fun updateCart(cart:Cart,position: Int)
        fun deleteCart(position: Int)
        fun deleteCartAll()
        fun openSuccessPage(carts: HashMap<String,Cart>)
        fun openNoteDialog(selected: Cart, pos: Int)
        fun openCountDialog(selected: Cart, pos: Int)
        fun openPriceDialog(selected: Cart, pos: Int)
        fun openStock(name: String?)
        fun openProducts(title:String, list: List<Product>, selected: Product?)
        fun onSelected(data: Cart, position: Int)
        fun onSelected2(data:Product)
        fun reloadData()
        fun setProducts(list:List<Product>)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheckScan()
        fun loadProducts(page: Int?)
        fun checkCart(data:Product)
        fun addCart(data:Product)
        fun increaseCart(data:Cart,position:Int)
        fun decreaseCart(data: Cart,position:Int)
        fun deleteCart(data: Cart,position:Int)
        fun updateCart(data: Cart,position:Int)
        fun countCart()
        fun searchByBarcode(search:String)
        fun getCartsSize():Int
        fun checkCart()
        fun onCheckVariable(data: Cart,position:Int)
        fun setSelectedProduct(data: AddOn, position: Int)
        fun setSelectedProduct2(data: Product)
        fun onCheckVariable2(id_product:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callSearchByBarcodeAPI(context: Context, restModel: ProductRestModel, search:String)
        fun callGetProductsAPI(context: Context, restModel:ProductRestModel,haveStok:String, page: Int?)
        fun callGetAddonAPI(context: Context, restModel: AddOnRestModel, id_product:String)
        fun callGetProductsVariableAPI(context: Context, restModel:ProductRestModel,id_product:String,haveStok:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetAddon(list:List<AddOn>)
        fun onSuccessByBarcode(list: List<Product>)
        fun onFailedBarcode(code:Int,msg:String)
        fun onSuccessGetProducts(list:List<Product>)
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessGetProductsVariable(list:List<Product>)
    }


}