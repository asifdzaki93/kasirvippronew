package id.kasirvippro.android.feature.transaction.detailSpend

import android.content.Context
import android.content.Intent
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.DetailSpend
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface DetailSpendContract {

    interface View : BaseViewImpl {
        fun reloadData()
        fun onClose(status:Int)
        fun showMessage(code: Int, msg: String?)
        fun setInfo(id:String,total:String,date:String,user:String?,img:String?)
        fun setProducts(list:List<DetailSpend.Data>)
        fun onClose()
        fun getParentLayout(): NestedScrollView
        fun hideShowActionButton(visibility:Int)


    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun loadDetail()
        fun deleteDetail()
        fun onCheckBluetooth()
        fun getDataStruk():DetailSpend
        fun onCheckShare()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
        fun callDeleteDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetDetail(detail: DetailSpend?)
        fun onSuccessDeleteDetail(message: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}