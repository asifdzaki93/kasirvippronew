package id.kasirvippro.android.feature.choose.store

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel

interface ChooseStoreContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Store>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun onSelected(data:Store)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetsAPI(context: Context, restModel:StoreRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGets(list:List<Store>)
        fun onFailedAPI(code:Int,msg:String)
    }


}