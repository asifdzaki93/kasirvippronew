package id.kasirvippro.android.feature.transaction.detailReturn

import android.content.Context
import android.content.Intent
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface DetailReturnContract {

    interface View : BaseViewImpl {
        fun reloadData()
        fun onClose(status:Int)
        fun showMessage(code: Int, msg: String?)
        fun setInfo(id:String,subtotal:String,total:String,date:String,operator:String?, customer:String?,
                    payment:String,status:String,bayar:String?,kembalian:String?,service:String?,pajak:String?,diskon:String?,sisaHutang:String?,name_store:String?,address:String?,email:String?,nohp:String?,footer:String?,img:String?)
        fun setProducts(list:List<DetailTransaction.Data>)
        fun enableBtn(type:String?)
        fun openPrinterPage()
        fun openPaymentDialog()
        fun onClose()
        fun getParentLayout(): NestedScrollView
        fun hideShowActionButton(visibility:Int)
        fun onPremiumPage(isPremium:Boolean)
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
        fun isOpenMain():Boolean
        fun onCheckShare()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context): String?
        fun callGetDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callGetDetailSupplierAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callDeleteDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callSupplierDeleteDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callPayPiutangAPI(context: Context, restModel: TransactionRestModel, id:String, total:String)
        fun callPayHutangAPI(context: Context, restModel: TransactionRestModel, id:String, total:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetDetail(detail: DetailTransaction?)
        fun onSuccessDeleteDetail(message: String?)
        fun onSuccessPay(message: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}