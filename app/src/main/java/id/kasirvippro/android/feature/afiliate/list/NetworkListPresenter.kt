package id.kasirvippro.android.feature.afiliate.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.network.Network
import id.kasirvippro.android.models.network.NetworkRestModel

class NetworkListPresenter(val context: Context, val view: NetworkListContract.View) : BasePresenter<NetworkListContract.View>(),
    NetworkListContract.Presenter, NetworkListContract.InteractorOutput {

    private var interactor = NetworkListInteractor(this)
    private var restModel = NetworkRestModel(context)


    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
    }


    override fun search(search: String) {
        interactor.onRestartDisposable()
        if(search.isEmpty() || search.isBlank()){
            interactor.callGetDataAPI(context,restModel)
        }
        else{
            interactor.callSearchAPI(context,restModel,search)
        }
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetData(list: List<Network>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}