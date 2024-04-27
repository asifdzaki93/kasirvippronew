package id.kasirvippro.android.feature.afiliate.subList

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.network.Network
import id.kasirvippro.android.models.network.NetworkRestModel

class NetworkSubListPresenter(val context: Context, val view: NetworkSubListContract.View) : BasePresenter<NetworkSubListContract.View>(),
    NetworkSubListContract.Presenter, NetworkSubListContract.InteractorOutput {

    private var interactor = NetworkSubListInteractor(this)
    private var restModel = NetworkRestModel(context)


    override fun onViewCreated() {
        loadDatas()
    }

    override fun loadDatas() {
        val key = view.getKey()
        interactor.callGetDataAPI(context,restModel,key!!)
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