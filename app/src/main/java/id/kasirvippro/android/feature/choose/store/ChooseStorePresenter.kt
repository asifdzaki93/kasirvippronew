package id.kasirvippro.android.feature.choose.store

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel

class ChooseStorePresenter(val context: Context, val view: ChooseStoreContract.View) : BasePresenter<ChooseStoreContract.View>(),
    ChooseStoreContract.Presenter, ChooseStoreContract.InteractorOutput {

    private var interactor = ChooseStoreInteractor(this)
    private var restModel = StoreRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetsAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGets(list: List<Store>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}