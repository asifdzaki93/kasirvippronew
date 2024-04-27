package id.kasirvippro.android.feature.manage.supplier.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.supplier.SupplierRestModel


interface AddSupplierContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(name:String,email:String,phone:String,address:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callAddSupplierAPI(
            context: Context,
            model: SupplierRestModel,
            name: String,
            email: String,
            telpon: String,
            alamat: String
        )
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAddSupplier(msg: String?)
        fun onFailedAddSupplier(code:Int,msg:String)
    }


}