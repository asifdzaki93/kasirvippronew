package id.kasirvippro.android.feature.choose.rawMaterial

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel

class ChooseRawMaterialPresenter(val context: Context, val view: ChooseRawMaterialContract.View) : BasePresenter<ChooseRawMaterialContract.View>(),
    ChooseRawMaterialContract.Presenter, ChooseRawMaterialContract.InteractorOutput {

    private var interactor = ChooseRawMaterialInteractor(this)
    private var restModel = RawMaterialRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetsAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGets(list: List<RawMaterial>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}