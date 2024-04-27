package id.kasirvippro.android.feature.order.main

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.divisi.Divisi
import id.kasirvippro.android.models.ongkir.Ongkir
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.transaction.DetailPayment
import id.kasirvippro.android.models.transaction.Order
import id.kasirvippro.android.models.transaction.RequestTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel


interface OrderContract {

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
        fun showPiutangView()
        fun openAddCustomer()
        fun setCustomerName(data:Customer?)
        fun setTableName(data:Table?)
        fun setOngkirName(data: Ongkir?)
        fun setDivisiName(data: Divisi?)
        fun setStaffName(data:Staff?)
        fun openChooseCustomer()
        fun openChooseTable()
        fun openChooseOngkir()
        fun openChooseDivisi()
        fun openChooseStaff()
        fun openSingleDatePickerDialog(selected: CalendarDay?)
        fun openSuccessPage()
        fun openNoteDialog(selected: Cart, pos: Int)
        fun openCountDialog(selected: Cart, pos: Int)
        fun getPayDebt():Double
        fun hideShowDebt(value:Int)
        fun setCashDebt(value: Double)
        fun openImageChooser()
        fun loadPhoto(path:String)
        fun deleteCartAll()
        fun openProducts(title:String, list: List<Product>, selected: Product?)
        fun onSelected2(data:Product)
        fun setDetailText(subtotal:Double,discount:Double?,service:Double?,tax:Double?,total:Double)
        fun reloadData()
        fun setProducts(list:List<Product>)
        fun openAddDiscount()
        fun openChooseDiscount()
        fun openChooseSift()
        fun setDiscount(data: Discount?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheckScan()
        fun loadProducts(page: Int?)
        fun checkCart(data:Product)
        fun addCart(data:Product)
        fun increaseCart(data:Cart,position:Int)
        fun decreaseCart(data: Cart,popenSingleDatePickerDialogosition:Int)
        fun deleteCart(data: Cart,position:Int)
        fun updateCart(data: Cart,position:Int)
        fun countCart()
        fun searchByBarcode(search:String)
        fun updateCustomer(data:Customer?)
        fun updateTable(data:Table?)
        fun updateOngkir(data:Ongkir?)
        fun updateDivisi(data:Divisi?)
        fun updateStaff(data: Staff?)
        fun setSelectedDate(date:CalendarDay?)
        fun getSelectedDate():CalendarDay?
        fun checkTunai()
        fun checkPiutang(note:String)
        fun getCartsSize():Int
        fun countDebt()
        fun onCheckPhoto()
        fun setImagePhotoPath(path:String?)
        fun onCheckVariable2(id_product:String)
        fun setSelectedProduct2(data: Product)
        fun updateDiscount(data:Discount?)
        fun checkDiscount()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun callSearchByBarcodeAPI(context: Context, restModel: ProductRestModel, search:String)
        fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestTransaction,img:String?)
        fun callGetProductsAPI(context: Context, restModel:ProductRestModel,haveStok:String,page: Int?)
        fun callGetProductsVariableAPI(context: Context, restModel:ProductRestModel,id_product:String,haveStok:String)
        fun callCheckDiscountAPI(context: Context, restModel: TransactionRestModel,total: Double,id:String?)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessByBarcode(list: List<Product>)
        fun onSuccessOrder()
        fun onFailedAPI(code:Int,msg:String)
        fun onFailedBarcode(code: Int, msg: String)
        fun onSuccessGetProducts(list:List<Product>)
        fun onSuccessCheckDiscount(data: List<DetailPayment>?)
        fun onSuccessGetProductsVariable(list:List<Product>)
    }


}