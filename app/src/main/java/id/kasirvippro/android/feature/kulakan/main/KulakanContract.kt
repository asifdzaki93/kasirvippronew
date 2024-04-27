package id.kasirvippro.android.feature.kulakan.main

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.transaction.Order
import id.kasirvippro.android.models.transaction.RequestKulakan
import id.kasirvippro.android.models.transaction.TransactionRestModel


interface KulakanContract {

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
        fun setSupplierName(data:Supplier?)
        fun openChooseSupplier()
        fun openSingleDatePickerDialog(selected: CalendarDay?)
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
        fun updateSupplier(data:Supplier?)
        fun setSelectedDate(date:CalendarDay?)
        fun getSelectedDate():CalendarDay?
        fun checkTunai()
        fun checkPiutang()
        fun getCartsSize():Int
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callSearchByBarcodeAPI(context: Context, restModel: ProductRestModel, search:String)
        fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestKulakan)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessByBarcode(list: List<Product>)
        fun onSuccessOrder(message: Order)
        fun onFailedAPI(code:Int,msg:String)
        fun onFailedBarcode(code: Int, msg: String)
    }


}