package id.kasirvippro.android.feature.manage.category.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.category.CategoryRestModel

interface AddCategoryContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(name:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callAddCategoryAPI(context: Context, model:CategoryRestModel, name:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAddCategory(msg: String?)
        fun onFailedAddCategory(code:Int,msg:String)
    }


}