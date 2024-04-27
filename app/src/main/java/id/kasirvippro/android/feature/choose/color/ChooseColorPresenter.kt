package id.kasirvippro.android.feature.choose.color

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.color.Color
import id.kasirvippro.android.models.color.ColorRestModel

class ChooseColorPresenter(val context: Context, val view: ChooseColorContract.View) : BasePresenter<ChooseColorContract.View>(),
    ChooseColorContract.Presenter, ChooseColorContract.InteractorOutput {

    private var interactor = ChooseColorInteractor(this)
    private var restModel = ColorRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetsAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGets(list: List<Color>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}