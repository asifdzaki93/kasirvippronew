package id.kasirvippro.android.feature.choose.sift

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.sift.Sift
import id.kasirvippro.android.models.sift.SiftRestModel

class ChooseSiftPresenter(val context: Context, val view: ChooseSiftContract.View) : BasePresenter<ChooseSiftContract.View>(),
    ChooseSiftContract.Presenter, ChooseSiftContract.InteractorOutput {

    private var interactor = ChooseSiftInteractor(this)
    private var restModel = SiftRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetsAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGets(list: List<Sift>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}