package id.kasirvippro.android.feature.purchaseMaterial.main

import android.content.Context
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.cartRaw.CartRaw
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.transaction.Order
import id.kasirvippro.android.models.transaction.RequestRawMaterial
import id.kasirvippro.android.models.transaction.TransactionRestModel


interface PurchaseMaterialContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun openScanPage()
        fun openChooseProduct()
        fun showContentView()
        fun showErrorView(err:String)
        fun setCartText(nominal:String)
        fun addCart(data: CartRaw)
        fun getTotalValue():Double
        fun updateCart(cart:CartRaw,position: Int)
        fun deleteCart(position: Int)
        fun showTunaiView()
        fun showPiutangView()
        fun setSupplierName(data:Supplier?)
        fun openChooseSupplier()
        fun openSingleDatePickerDialog(selected: CalendarDay?)
        fun openSuccessPage(id:String)
        fun openCountDialog(selected: CartRaw, pos: Int)
        fun openHistoryKulakan()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheckScan()
        fun checkCart(data:RawMaterial)
        fun addCart(data:RawMaterial)
        fun increaseCart(data:CartRaw,position:Int)
        fun decreaseCart(data: CartRaw,position:Int)
        fun deleteCart(data: CartRaw,position:Int)
        fun updateCart(data: CartRaw,position:Int)
        fun countCart()
        fun searchByBarcode(search:String)
        fun updateSupplier(data:Supplier?)
        fun setSelectedDate(date:CalendarDay?)
        fun getSelectedDate():CalendarDay?
        fun checkTunai()
        fun getCartsSize():Int
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestRawMaterial)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessByBarcode(list: List<RawMaterial>)
        fun onSuccessOrder(message: Order)
        fun onFailedAPI(code:Int,msg:String)
        fun onFailedBarcode(code: Int, msg: String)
    }


}