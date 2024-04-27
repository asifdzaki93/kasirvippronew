package id.kasirvippro.android.feature.manage.table.transaction

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

class TableTransactionPresenter(val context: Context, val view: TableTransactionContract.View) : BasePresenter<TableTransactionContract.View>(),
    TableTransactionContract.Presenter, TableTransactionContract.InteractorOutput {

    private var interactor = TableTransactionInteractor(this)
    private var restModel = TransactionRestModel(context)
    private var data:Table? = null

    override fun onViewCreated(table: Table?) {
        this.data = table
        loadTransactions()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadTransactions() {
        interactor.callGetTransactionsAPI(context,restModel,data?.id_table!!)
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