package id.kasirvippro.android.feature.manage.divisi.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.divisi.DivisiRestModel

interface EditDivisiContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setName(value:String?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun onCheck(name_divisi:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callEditCategoryAPI(context: Context, model:DivisiRestModel, id:String, name_divisi:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditCategory(msg: String?)
        fun onFailedEditCategory(code:Int,msg:String)
    }


}