package id.kasirvippro.android.feature.manageOrder.kitchen.transactionSuccess

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.HistoryTransaction
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.models.transaction.TransactionRestModel

class TransactionSuccessPresenter(val context: Context, val view: TransactionSuccessContract.View) : BasePresenter<TransactionSuccessContract.View>(),
    TransactionSuccessContract.Presenter, TransactionSuccessContract.InteractorOutput {


    private var interactor = TransactionSuccessInteractor(this)
    private var transactionRestModel = TransactionRestModel(context)


    override fun onViewCreated() {
        loadTransaction()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadTransaction() {
        interactor.callGetHistorySuccessAPI(context,transactionRestModel)

    }

    override fun onSuccessGetHistoryApi(list:List<HistoryTransaction>?) {
        list?.let {
            if(it.isEmpty()){
                view.showErrorMessage(999,"Data not found")
                return
            }
            val transactions = ArrayList<Transaction>()
            for(history in it){
                history.detail?.let {detail->
                    if(detail.isNotEmpty()){
                        val header = Transaction()
                        header.date = history.date
                        header.type = "header"
                        header.totalorder = history.totalorderall
                        for(trx in detail){
                            transactions.add(0,trx)
                        }

                        transactions.add(0,header)

                    }
                }
            }
            if(transactions.isEmpty()){
                view.showErrorMessage(999,"No data available")
                return
            }
            view.setList(transactions)
        }
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }





}