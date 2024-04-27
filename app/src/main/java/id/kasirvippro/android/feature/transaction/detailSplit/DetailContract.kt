package id.kasirvippro.android.feature.transaction.detailSplit

import android.content.Context
import android.content.Intent
import androidx.core.widget.NestedScrollView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.models.transaction.*

interface DetailContract {

    interface View : BaseViewImpl {
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun onClose(status:Int)
        fun openSuccessPage(id:String)
        fun openAddOrderPage(id: String)
        fun showMessage(code: Int, msg: String?)
        fun setInfo(id:String,date:String, status:String, totalorder:String, operator:String,service:String?,pajak:String?,diskon:String?,subtotal:String)
        fun setProducts(list:List<DetailTransaction.Data>)
        fun openPrinterPage()
        fun onClose()
        fun showTunaiView()
        fun getParentLayout(): NestedScrollView
        fun hideShowActionButton(visibility:Int)
        fun onPremiumPage(isPremium:Boolean)
        fun getTotalValue():Double
        fun openSingleDatePickerDialog(selected: CalendarDay?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun loadDetail()
        fun deleteDetail()
        fun onCheckBluetooth()
        fun getDataStruk():DetailTransaction
        fun getTypeTRX():Int
        fun onPay(value:String)
        fun checkTunai(detail: DetailTransaction?)
        fun isOpenMain():Boolean
        fun onCheckShare()
        fun deleteProduct(no_invoice:String,id:String)
        fun plusProduct(no_invoice:String,id:String)
        fun minusProduct(no_invoice:String,id:String)
        fun addOrder()
        fun setSelectedDate(date: CalendarDay?)
        fun getSelectedDate():CalendarDay?
        fun checkPiutang(note:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context): String?
        fun callGetDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callGetDetailSupplierAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callDeleteDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callSupplierDeleteDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callPayOrderAPI(context: Context, restModel: TransactionRestModel, req: ReqTrans, note: String, id: String)
        fun callPayPiutangAPI(context: Context, restModel: TransactionRestModel, id:String, total:String)
        fun callPayHutangAPI(context: Context, restModel: TransactionRestModel, id:String, total:String)
        fun callDeleteProductAPI(context: Context, restModel: TransactionRestModel, no_invoice:String, id:String)
        fun callPlusProductAPI(context: Context, restModel: TransactionRestModel, no_invoice:String, id:String)
        fun callMinusProductAPI(context: Context, restModel: TransactionRestModel, no_invoice:String, id:String)
        fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestTransaction)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessOrder(order: Order)
        fun onSuccessUpdate()
        fun onSuccessGetDetail(detail: DetailTransaction?)
        fun onSuccessDeleteDetail(message: String?)
        fun onSuccessPay(message: String?)
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessCheckDiscount(data: List<DetailPayment>?)
        fun onSuccessDeleteProduct(msg: String?)
    }


}