package id.kasirvippro.android.feature.manage.customer.credit

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

class CustomerCreditPresenter(val context: Context, val view: CustomerCreditContract.View) : BasePresenter<CustomerCreditContract.View>(),
    CustomerCreditContract.Presenter,
    CustomerCreditContract.InteractorOutput {


    private var interactor = CustomerCreditInteractor(this)
    private var restModel = TransactionRestModel(context)
    private var data: Customer? = null

    override fun onViewCreated(customer: Customer?) {
        this.data = customer
        loadTransactions()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadTransactions() {
        interactor.callGetTransactionsAPI(context,restModel,data?.id_customer!!)
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