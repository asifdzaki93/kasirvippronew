package id.kasirvippro.android.feature.choose.decimal

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.currency.CurrencyRestModel
import id.kasirvippro.android.models.currency.Decimal

class ChooseDecimalPresenter(val context: Context, val view: ChooseDecimalContract.View) : BasePresenter<ChooseDecimalContract.View>(),
    ChooseDecimalContract.Presenter, ChooseDecimalContract.InteractorOutput {

    private var interactor  = ChooseDecimalInteractor(this)
    private var restModel = CurrencyRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
    }
    override fun onDestroy() {
        interactor.onDestroy()
    }

    fun setCustomer(list: List<Decimal>){
        view.setData(list)
    }

    override fun onSuccessGetData(list: List<Decimal>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}