package id.kasirvippro.android.feature.transaction.detail

import android.content.Context
import android.content.Intent
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.transaction.*

interface DetailContract {

    interface View : BaseViewImpl {
        fun reloadData()
        fun onClose(status:Int)
        fun showMessage(code: Int, msg: String?)
        fun setInfo(id:String,subtotal:String,total:String,date:String,operator:String?, customer:String?,table:String?,supplier:String?,
                    payment:String,status:String,bayar:String?,kembalian:String?,service:String?,pajak:String?,diskon:String?,sisaHutang:String?,name_store:String?,address:String?,email:String?,nohp:String?,footer:String?,img:String?,id_table:String?,link_order:String?,due_date:String?,note:String?,ongkir:String?,divisi:String?)
        fun setProducts(list:List<DetailTransaction.Data>)
        fun enableBtn(type:String?)
        fun openPrinterPage()
        fun openPaymentDialog()
        fun onClose()
        fun getParentLayout(): NestedScrollView
        fun hideShowActionButton(visibility:Int)
        fun onPremiumPage(isPremium:Boolean)
        fun setDiscount(data:Discount?)
        fun setNonTunai(data:NonTunai?)
        fun getPayValue():Double
        fun getTotalValue():Double
        fun getNoteValue():String
        fun onWaiterPage()
        fun onKitchenPage()
        fun showTunaiView()
        fun showNonTunaiView()
        fun openChooseDiscount()
        fun openChooseNonTunai()
        fun openSuccessPage(id:String)
        fun getPayNon():Double
        fun hideShowNon(value:Int)
        fun setCashNon(value: Double)
        fun openShare(code:String)
        fun openPaymentNonTunai(url:String,id:String)
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
        fun checkNonTunai(detail: DetailTransaction?)
        fun isOpenMain():Boolean
        fun onCheckShare()
        fun onShare()
        fun finishDetail()
        fun updateDiscount(data: Discount?)
        fun checkDiscount()
        fun updateNonTunai(data: NonTunai?)
        fun countNon()
    }

    interface Interactor : BaseInteractorImpl {
        fun getUserLevel(context: Context):String?
        fun onDestroy()
        fun getToken(context: Context):String?
        fun onRestartDisposable()
        fun getUserPaket(context: Context): String?
        fun callGetDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callGetDetailSupplierAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callDeleteDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callSupplierDeleteDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callPayPiutangAPI(context: Context, restModel: TransactionRestModel, id:String, total:String)
        fun callPayHutangAPI(context: Context, restModel: TransactionRestModel, id:String, total:String)
        fun callFinishDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callCheckDiscountAPI(context: Context, restModel: TransactionRestModel,total: Double,id:String?)
        fun callPayOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestTransaction, note: String, id: String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessOrder(order: Order)
        fun onSuccessGetDetail(detail: DetailTransaction?)
        fun onSuccessFinishDetail(message: String?)
        fun onSuccessDeleteDetail(message: String?)
        fun onSuccessPay(message: String?)
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessCheckDiscount(data: List<DetailPayment>?)
    }


}