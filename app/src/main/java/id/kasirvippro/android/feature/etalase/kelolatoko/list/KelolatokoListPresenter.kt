package id.kasirvippro.android.feature.etalase.kelolatoko.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.kelolatoko.Kelolatoko
import id.kasirvippro.android.models.kelolatoko.KelolatokoRestModel

class KelolatokoListPresenter(val context: Context, val view: KelolatokoListContract.View) : BasePresenter<KelolatokoListContract.View>(),
    KelolatokoListContract.Presenter, KelolatokoListContract.InteractorOutput {

    private var interactor = KelolatokoListInteractor(this)
    private var restModel = KelolatokoRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetsAPI(context,restModel)
    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGets(list: List<Kelolatoko>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}