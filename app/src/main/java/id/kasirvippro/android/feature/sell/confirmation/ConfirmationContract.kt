package id.kasirvippro.android.feature.sell.confirmation

import android.content.Context
import android.content.Intent
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.price.Price
import id.kasirvippro.android.models.price.PriceRestModel
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.transaction.*
import org.threeten.bp.LocalDate


interface ConfirmationContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun getTotalValue():Double
        fun getPayNon():Double
        fun getPayDebt():Double
        fun openSuccessPage(id:String)
        fun showTunaiView()
        fun showNonTunaiView()
        fun showPiutangView()
        fun showPointView()
        fun setCustomerName(data:Customer?)
        fun openChooseCustomer()
        fun openChooseKurir()
        fun openChooseNonTunai()
        fun openAddCustomer()
        fun openAddDiscount()
        fun openPaymentNonTunai(url:String,id:String)
        fun getPayValue():Double
        fun setCashback(value:Double)
        fun setCashNon(value: Double)
        fun setCashDebt(value: Double)
        fun hideShowCashback(value:Int)
        fun hideShowNon(value:Int)
        fun hideShowDebt(value:Int)
        fun openSingleDatePickerDialog(selected: CalendarDay?, minDate: LocalDate?, maxDate: LocalDate?, type:Int)
        fun openChooseDiscount()
        fun setDetailText(subtotal:Double,discount:Double?,service:Double?,tax:Double?,total:Double)
        fun setDiscount(data:Discount?)
        fun openChooseTable()
        fun setNonTunai(data:NonTunai?)
        fun setCart(list: List<Cart>)
        fun setTableName(data:Table?)
        fun setPrice(list:List<Price>)
        fun updatePrice(nominal: String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun countCart()
        fun updateCustomer(data:Customer?)
        fun setSelectedDate(date:CalendarDay?)
        fun getSelectedDate():CalendarDay?
        fun checkTunai()
        fun checkPiutang()
        fun checkNonTunai(note:String)
        fun getCartsSize():Int
        fun countCashback()
        fun countNon()
        fun countDebt()
        fun updateDiscount(data:Discount?)
        fun updatePrice(data:Price?)
        fun checkDiscount()
        fun loadPrice()
        fun updateNonTunai(data:NonTunai?)
        fun updateTable(data: Table?)
        fun setSelectedPrice(data: Price)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestTransaction, note: String)
        fun callGetPriceAPI(context: Context, restModel: PriceRestModel, nominal:Double)
        fun callCheckDiscountAPI(context: Context, restModel: TransactionRestModel,total: Double,id:String?)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessOrder(order: Order)
        fun onSuccessGetPrice(list: List<Price>)
        fun onSuccessCheckDiscount(data: List<DetailPayment>?)
        fun onFailedAPI(code:Int,msg:String)
    }


}