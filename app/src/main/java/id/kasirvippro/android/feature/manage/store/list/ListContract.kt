package id.kasirvippro.android.feature.manage.store.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel

interface ListContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Store>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openAddPage()
        fun openEditPage(data:Store)
        fun openWebviewPage(title:String,url:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
        fun delete(id:String)
        fun onCheckPremium()

    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun getUserPaket(context: Context):String?
        fun callGetsAPI(context: Context, restModel:StoreRestModel)
        fun callDeleteAPI(context: Context, restModel:StoreRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGets(list:List<Store>)
        fun onSuccessDelete(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}