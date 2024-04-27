package id.kasirvippro.android.feature.manage.supplier.transaction

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

class TransactionPresenter(val context: Context, val view: TransactionContract.View) : BasePresenter<TransactionContract.View>(),
    TransactionContract.Presenter, TransactionContract.InteractorOutput {

    private var interactor = TransactionInteractor(this)
    private var restModel = TransactionRestModel(context)
    private var data:Supplier? = null

    override fun onViewCreated(data: Supplier?) {
        this.data = data
        loadTransactions()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadTransactions() {
        interactor.callGetTransactionsAPI(context,restModel,data?.id_supplier!!)
    }


    override fun onSuccessGetTransactions(list: List<Transaction>) {
        if(list.isEmpty()){
            view.showErrorMessage(999,"No transactions yet")
            return
        }
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}