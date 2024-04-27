package id.kasirvippro.android.feature.addOn.workManagement.transaction

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.DetailJob
import id.kasirvippro.android.models.transaction.TransactionRestModel

class TransactionListPresenter(val context: Context, val view: TransactionListContract.View) : BasePresenter<TransactionListContract.View>(),
    TransactionListContract.Presenter, TransactionListContract.InteractorOutput {

    private var interactor = TransactionListInteractor(this)
    private var restModel = TransactionRestModel(context)


    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        val operator = view.getIdProduct()
        interactor.callGetDataAPI(context,restModel,operator!!)
    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetData(list: List<DetailJob>) {
        view.setData(list)
    }


    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}