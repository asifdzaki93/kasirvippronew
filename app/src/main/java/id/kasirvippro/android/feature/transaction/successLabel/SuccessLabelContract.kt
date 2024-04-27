package id.kasirvippro.android.feature.transaction.successLabel

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.DetailLabel
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface SuccessLabelContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onSuccessLabel(id:String)
        fun onClose()
        fun onErrorView(msg:String)
        fun onSuccessLabelView()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun loadDetail()
        fun getDataStruk():DetailLabel
        fun getTabPosition():Int
        fun getInvoice():String?

    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDetailAPI(context: Context, restModel: TransactionRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessLabelGetDetail(detail: DetailLabel?)
        fun onFailedAPI(code:Int,msg:String)
    }


}