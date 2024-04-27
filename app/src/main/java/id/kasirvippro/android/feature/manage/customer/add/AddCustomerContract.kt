package id.kasirvippro.android.feature.manage.customer.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.customer.CustomerRestModel

interface AddCustomerContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(name:String,email:String,phone:String,address:String,customercode:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callAddCustomerAPI(context: Context,model:CustomerRestModel,name:String,email:String,phone:String,address:String,customercode:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAddCustomer(msg: String?)
        fun onFailedAddCustomer(code:Int,msg:String)
    }


}