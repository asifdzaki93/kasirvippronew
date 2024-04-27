package id.kasirvippro.android.feature.manage.ongkir.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.ongkir.OngkirRestModel

interface AddOngkirContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(name_ongkir:String, nominal: String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callAddCategoryAPI(context: Context, model:OngkirRestModel, name_ongkir:String, nominal: String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAddCategory(msg: String?)
        fun onFailedAddCategory(code:Int,msg:String)
    }


}