package id.kasirvippro.android.feature.manage.priceVariant.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.priceVariant.PriceVariant
import id.kasirvippro.android.models.priceVariant.PriceVariantRestModel

interface EditPriceVariantContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int,data: PriceVariant?)
        fun setProductName(value:String)
        fun setBuyPrice(value:String)
        fun setProduct(value:String)
        fun onPremiumPage(isPremium:Boolean)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheck(minimal:String,nominal:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context):String?
        fun callEditProductAPI(context: Context, model: PriceVariantRestModel, id:String,minimal:String,nominal:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}