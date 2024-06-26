package id.kasirvippro.android.feature.manageOrder.kitchen.transactionSuccess

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.transaction.HistoryTransaction
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface TransactionSuccessContract {

    interface View : BaseViewImpl {
        fun reloadData()
        fun setList(list:List<Transaction>)
        fun showErrorMessage(code: Int, msg: String)
        fun openDetail(id:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadTransaction()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetHistorySuccessAPI(context: Context, restModel: TransactionRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetHistoryApi(list:List<HistoryTransaction>?)
        fun onFailedAPI(code:Int,msg:String)
    }


}