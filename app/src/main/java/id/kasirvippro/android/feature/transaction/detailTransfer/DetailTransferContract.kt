package id.kasirvippro.android.feature.transaction.detailTransfer

import android.content.Context
import android.content.Intent
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.*

interface DetailTransferContract {

    interface View : BaseViewImpl {
        fun reloadData()
        fun onClose(status:Int)
        fun showMessage(code: Int, msg: String?)
        fun setInfo(id:String,subtotal:String,total:String,date:String,operator:String?,
                    payment:String,status:String,bayar:String?,kembalian:String?,sisaHutang:String?,name_store:String?,address:String?,email:String?,nohp:String?,img:String?,store_destination:String?)
        fun setProducts(list:List<DetailTransfer.Data>)
        fun enableBtn(type:String?)
        fun openPrinterPage()
        fun onClose()
        fun getParentLayout(): NestedScrollView
        fun hideShowActionButton(visibility:Int)
        fun onPremiumPage(isPremium:Boolean)
        fun openSuccessPage(id:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun loadDetail()
        fun deleteDetail()
        fun onCheckBluetooth()
        fun getDataStruk():DetailTransfer
        fun getTypeTRX():Int
        fun isOpenMain():Boolean
        fun onCheckShare()
        fun finishDetail()
    }

    interface Interactor : BaseInteractorImpl {
        fun getUserLevel(context: Context):String?
        fun onDestroy()
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
        fun onSuccessGetDetail(detail: DetailTransfer?)
        fun onSuccessFinishDetail(message: String?)
        fun onSuccessDeleteDetail(message: String?)
        fun onSuccessPay(message: String?)
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessCheckDiscount(data: List<DetailPayment>?)
    }


}