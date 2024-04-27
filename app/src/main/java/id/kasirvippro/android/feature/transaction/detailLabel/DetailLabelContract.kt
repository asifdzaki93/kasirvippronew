package id.kasirvippro.android.feature.transaction.detailLabel

import android.content.Context
import android.content.Intent
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.DetailLabel
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface DetailLabelContract {

    interface View : BaseViewImpl {
        fun reloadData()
        fun onClose(status:Int)
        fun showMessage(code: Int, msg: String?)
        fun setInfo(id:String)
        fun setProducts(list:List<DetailLabel.Data>)
        fun enableBtn(type:String?)
        fun openPrinterPage()
        fun onClose()
        fun getParentLayout(): NestedScrollView
        fun hideShowActionButton(visibility:Int)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun loadDetailLabel()
        fun onCheckBluetooth()
        fun getDataStruk():DetailLabel
        fun getTypeTRX():Int
        fun isOpenMain():Boolean
        fun onCheckShare()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context): String?
        fun callGetDetailLabelAPI(context: Context, restModel: TransactionRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetDetailLabel(detail: DetailLabel?)
        fun onFailedAPI(code:Int,msg:String)
    }


}