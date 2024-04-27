package id.kasirvippro.android.feature.manage.supplier.transaction

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

interface TransactionContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Transaction>)
        fun showErrorMessage(code: Int, msg: String)
        fun openDetail(id:String)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(data:Supplier?)
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