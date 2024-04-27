package id.kasirvippro.android.feature.pulsaPpob.order

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.pulsaPpob.PulsaPpobRestModel

interface OrderContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun getPhone():String
        fun getSku():String
        fun getProductname():String
        fun getPay():Double
        fun openSuccessPage(id:String)
        fun setData(product_name: String?,category:String?,brand:String?,price:String?,buyer_sku_code:String?,desc:String?,phone:String?,gbr:String?,buyer_product_status:String?,seller_product_status:String?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent:Intent)
        fun onDestroy()
        fun onCheck(pin: String)
        fun onCheckToken(phone: String, brand: String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callOrderAPI(context: Context, model: PulsaPpobRestModel, phone:String, sku:String, hargajual:String, pin:String, product_name:String)
        fun callTokenAPI(context: Context, model: PulsaPpobRestModel, phone:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessOrder(msg: String?)
        fun onSuccessCek(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}