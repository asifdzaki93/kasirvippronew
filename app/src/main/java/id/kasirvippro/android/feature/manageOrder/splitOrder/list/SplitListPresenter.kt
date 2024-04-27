package id.kasirvippro.android.feature.manageOrder.splitOrder.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

class SplitListPresenter(val context: Context, val view: SplitListContract.View) : BasePresenter<SplitListContract.View>(),
    SplitListContract.Presenter, SplitListContract.InteractorOutput {

    private var interactor: SplitListInteractor = SplitListInteractor(this)
    private var restModel = TransactionRestModel(context)

    override fun onViewCreated() {
        loadCategories()
    }

    override fun loadCategories() {
        interactor.callGetTransactionsAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
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