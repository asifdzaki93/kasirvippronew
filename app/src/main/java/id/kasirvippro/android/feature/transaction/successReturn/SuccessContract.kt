package id.kasirvippro.android.feature.transaction.successReturn

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface SuccessContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onSuccess(date:String,id:String)
        fun onClose()
        fun onErrorView(msg:String)
        fun onSuccessView()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun loadDetail()
        fun getDataStruk():DetailTransaction
        fun getTabPosition():Int
        fun getInvoice():String?

    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetDetail(detail: DetailTransaction?)
        fun onFailedAPI(code:Int,msg:String)
    }


}