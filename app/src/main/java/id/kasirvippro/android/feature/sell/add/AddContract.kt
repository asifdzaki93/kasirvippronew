package id.kasirvippro.android.feature.sell.add

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.cart.CartRestModel
import id.kasirvippro.android.models.product.Product

interface AddContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onSuccess(data:Product)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheck(name:String,buy:String,sell:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callAddAPI(context: Context,restModel:CartRestModel,name:String,buy:String,sell:String)
        fun callAddWithBarodeAPI(context: Context,restModel:CartRestModel,name:String,barcode:String,buy:String,sell:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAdd(data:List<Product>)
        fun onFailedAPI(code:Int,msg:String)
    }


}