package id.kasirvippro.android.feature.manage.customer.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.customer.CustomerRestModel

interface EditCustomerContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setCustomer(name: String?,email:String?,phone:String?,address:String?,customercode:String?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun onCheck(name:String,email:String,phone:String,address:String,customercode:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callEditCustomerAPI(context: Context, model: CustomerRestModel, id:String, name:String, email:String, phone:String, address:String, customercode:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditCustomer(msg: String?)
        fun onFailedEditCustomer(code:Int,msg:String)
    }


}