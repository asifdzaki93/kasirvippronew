package id.kasirvippro.android.feature.manage.priceVariant.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.priceVariant.PriceVariant
import id.kasirvippro.android.models.priceVariant.PriceVariantRestModel

interface AddPriceVariantContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openEditPage(product:PriceVariant)
        fun onPremiumPage(isPremium:Boolean)
        fun getIdProduct(): String?
        fun getDetail(): String?
        fun setProduct(value:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(minimal:String,nominal:String)

    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context):String?
        fun callAddProductAPI(context: Context, model: PriceVariantRestModel, minimal:String,nominal:String,id_product: String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAddProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}