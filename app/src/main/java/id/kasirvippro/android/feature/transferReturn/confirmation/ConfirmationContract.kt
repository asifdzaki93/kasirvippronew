package id.kasirvippro.android.feature.transferReturn.confirmation

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.models.transaction.*


interface ConfirmationContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun getTotalValue():Double
        fun openSuccessPage()
        fun showTunaiView()
        fun setCashback(value:Double)
        fun hideShowCashback(value:Int)
        fun setCart(list: List<Cart>)
        fun setDetailText(subtotal:Double,total:Double)
        fun openImageChooser()
        fun loadPhoto(path:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun countCart()
        fun checkTunai(note:String)
        fun getCartsSize():Int
        fun countCashback()
        fun onCheckPhoto()
        fun setImagePhotoPath(path:String?)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun callOrderAPI(context: Context, restModel: TransactionRestModel, req: RequestTransaction, note: String,img:String?)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessOrder()
        fun onFailedAPI(code:Int,msg:String)
    }


}