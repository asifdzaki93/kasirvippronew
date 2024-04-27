package id.kasirvippro.android.feature.manage.customer.detail

import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.customer.Customer

interface CustomerDetailContract {

    interface View : BaseViewImpl {
        fun onClose(status:Int)
        fun setCustomer(name: String?,email:String?,phone:String?,address:String?)
        fun hideShowToolbar(isShow:Boolean)
        fun openEditPage()

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onDestroy()
        fun onViewCreated(intent: Intent)
        fun getTitleName():String
        fun setCustomerData(data:Customer)
        fun getCustomerData():Customer?
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()

    }

    interface InteractorOutput : BaseInteractorOutputImpl {

    }


}