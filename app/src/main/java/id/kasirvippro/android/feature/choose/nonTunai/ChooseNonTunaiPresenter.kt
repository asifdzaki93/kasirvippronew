package id.kasirvippro.android.feature.choose.nonTunai

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.NonTunai
import id.kasirvippro.android.models.transaction.TransactionRestModel

class ChooseNonTunaiPresenter(val context: Context, val view: ChooseNonTunaiContract.View) : BasePresenter<ChooseNonTunaiContract.View>(),
    ChooseNonTunaiContract.Presenter, ChooseNonTunaiContract.InteractorOutput {

    private var interactor = ChooseNonTunaiInteractor(this)
    private var restModel = TransactionRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }


    fun setLink(list: List<NonTunai>){
        view.setData(list)
    }

    override fun onSuccessGetData(list: List<NonTunai>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}