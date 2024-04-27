package id.kasirvippro.android.feature.choose.divisi

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.divisi.Divisi
import id.kasirvippro.android.models.divisi.DivisiRestModel

class ChooseDivisiPresenter(val context: Context, val view: ChooseDivisiContract.View) : BasePresenter<ChooseDivisiContract.View>(),
    ChooseDivisiContract.Presenter, ChooseDivisiContract.InteractorOutput {

    private var interactor  = ChooseDivisiInteractor(this)
    private var restModel = DivisiRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    fun setCustomer(list: List<Divisi>){
        view.setData(list)
    }

    override fun onSuccessGetData(list: List<Divisi>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

}