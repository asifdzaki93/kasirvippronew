package id.kasirvippro.android.feature.manage.store.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.utils.AppConstant

class ListPresenter(val context: Context, val view: ListContract.View) : BasePresenter<ListContract.View>(),
    ListContract.Presenter, ListContract.InteractorOutput {

    private var interactor = ListInteractor(this)
    private var restModel = StoreRestModel(context)
    private var premium:Boolean = false

    override fun onViewCreated() {
        premium = "1" == interactor.getUserPaket(context)
        loadData()
    }

    override fun loadData() {
        interactor.callGetsAPI(context,restModel)
    }

    override fun delete(id: String) {
        interactor.callDeleteAPI(context,restModel,id)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGets(list: List<Store>) {
        view.setData(list)
    }

    override fun onSuccessDelete(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

    override fun onCheckPremium() {
        if(premium){
            view.openAddPage()
        }
        else{
            val key = interactor.getToken(context)
            val url = AppConstant.URL.PREMIUM+key
            view.openWebviewPage("Premium",url)
        }
    }
}