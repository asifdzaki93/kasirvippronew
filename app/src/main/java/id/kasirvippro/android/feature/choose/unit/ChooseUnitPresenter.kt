package id.kasirvippro.android.feature.choose.unit

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.unit.Unit
import id.kasirvippro.android.models.unit.UnitRestModel

class ChooseUnitPresenter(val context: Context, val view: ChooseUnitContract.View) : BasePresenter<ChooseUnitContract.View>(),
    ChooseUnitContract.Presenter, ChooseUnitContract.InteractorOutput {

    private var interactor = ChooseUnitInteractor(this)
    private var restModel = UnitRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetsAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGets(list: List<Unit>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}