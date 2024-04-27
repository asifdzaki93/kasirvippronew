package id.kasirvippro.android.feature.manage.rawMaterial.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel

interface EditRawMaterialContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setProductName(value:String)
        fun setUnitName(value:String)
        fun setStock(value:String)
        fun setSellPrice(value:String)
        fun setDescription(value:String)
        fun onPremiumPage(isPremium:Boolean)
        fun openChooseUnit()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheck(name:String,unit:String,sell:String,stok:String,desc:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context):String?
        fun callEditProductAPI(context: Context, model: RawMaterialRestModel, id:String,name:String,unit:String,jual:String,stok:String,
                               desk:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}