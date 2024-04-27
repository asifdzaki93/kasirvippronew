package id.kasirvippro.android.feature.manage.supplier.credit

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

class SupplierCreditPresenter(val context: Context, val view: SupplierCreditContract.View) : BasePresenter<SupplierCreditContract.View>(),
    SupplierCreditContract.Presenter, SupplierCreditContract.InteractorOutput {


    private var interactor = SupplierCreditInteractor(this)
    private var restModel = TransactionRestModel(context)
    private var data: Supplier? = null

    override fun onViewCreated(supplier: Supplier?) {
        this.data = supplier
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