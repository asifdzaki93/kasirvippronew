package id.kasirvippro.android.feature.manage.rawMaterial.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel

interface AddRawMaterialContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openChooseUnit()
        fun onPremiumPage(isPremium:Boolean)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(name:String,unit:String,sell:String,stok:String,desc:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context):String?
        fun callAddProductAPI(context: Context, model: RawMaterialRestModel, name:String,unit:String,jual:String,stok:String,
                              desk:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAddProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}