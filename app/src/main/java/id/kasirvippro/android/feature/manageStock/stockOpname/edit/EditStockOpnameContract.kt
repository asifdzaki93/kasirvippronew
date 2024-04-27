package id.kasirvippro.android.feature.manageStock.stockOpname.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel

interface EditStockOpnameContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int,data: Product?)
        fun setStock(value:String)
        fun onPremiumPage(isPremium:Boolean)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheck(stok:String, reason:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context):String?
        fun callEditProductAPI(context: Context, model: ProductRestModel, id:String,stok:String,reason:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}