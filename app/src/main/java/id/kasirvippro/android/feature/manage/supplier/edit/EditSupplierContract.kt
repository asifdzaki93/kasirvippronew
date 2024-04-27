package id.kasirvippro.android.feature.manage.supplier.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.supplier.SupplierRestModel

interface EditSupplierContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setSupplier(name:String?,email:String?,phone:String?,address:String?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheck(name:String,email:String,phone:String,address:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callEditSupplierAPI(context: Context, model: SupplierRestModel, id:String, name:String, email:String, phone:String, address:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditSupplier(msg: String?)
        fun onFailedEditSupplier(code:Int,msg:String)
    }


}