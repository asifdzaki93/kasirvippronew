package id.kasirvippro.android.feature.label.confirmation

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.transaction.*


interface ConfirmationLabelContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun openSuccessPage(id:String)
        fun setCart(list: List<Cart>)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun checkLabel()
        fun getCartsSize():Int
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestTransaction, note: String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessOrder(order: Order)
        fun onFailedAPI(code:Int,msg:String)
    }


}