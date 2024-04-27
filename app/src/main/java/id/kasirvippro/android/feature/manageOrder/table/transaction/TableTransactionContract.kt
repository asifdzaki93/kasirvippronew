package id.kasirvippro.android.feature.manageOrder.table.transaction

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface TableTransactionContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Transaction>)
        fun showErrorMessage(code: Int, msg: String)
        fun openDetail(id:String)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(table:Table?)
        fun onDestroy()
        fun loadTransactions()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetTransactionsAPI(context: Context, restModel:TransactionRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetTransactions(list:List<Transaction>)
        fun onFailedAPI(code:Int,msg:String)
    }


}