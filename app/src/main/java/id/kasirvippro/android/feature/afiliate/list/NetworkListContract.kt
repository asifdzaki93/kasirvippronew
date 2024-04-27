package id.kasirvippro.android.feature.afiliate.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.network.Network
import id.kasirvippro.android.models.network.NetworkRestModel


interface NetworkListContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Network>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openNetworkPage(data:Network)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
        fun search(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDataAPI(context: Context, restModel:NetworkRestModel)
        fun callSearchAPI(context: Context, restModel:NetworkRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetData(list:List<Network>)
        fun onFailedAPI(code:Int,msg:String)
    }


}