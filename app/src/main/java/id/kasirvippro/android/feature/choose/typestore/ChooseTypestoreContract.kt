package id.kasirvippro.android.feature.choose.typestore

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.typestore.TypeStore
import id.kasirvippro.android.models.typestore.TypeStoreRestModel


interface ChooseTypestoreContract {

    interface View : BaseViewImpl {
        fun setData(list:List<TypeStore>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun onSelected(data:TypeStore)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDataAPI(context: Context, restModel:TypeStoreRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetData(list:List<TypeStore>)
        fun onFailedAPI(code:Int,msg:String)
    }


}