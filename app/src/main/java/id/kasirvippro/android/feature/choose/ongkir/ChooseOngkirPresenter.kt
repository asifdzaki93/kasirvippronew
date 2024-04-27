package id.kasirvippro.android.feature.choose.ongkir

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.ongkir.Ongkir
import id.kasirvippro.android.models.ongkir.OngkirRestModel

class ChooseOngkirPresenter(val context: Context, val view: ChooseOngkirContract.View) : BasePresenter<ChooseOngkirContract.View>(),
    ChooseOngkirContract.Presenter, ChooseOngkirContract.InteractorOutput {

    private var interactor  = ChooseOngkirInteractor(this)
    private var restModel = OngkirRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    fun setCustomer(list: List<Ongkir>){
        view.setData(list)
    }

    override fun onSuccessGetData(list: List<Ongkir>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

}