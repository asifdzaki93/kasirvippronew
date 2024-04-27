package id.kasirvippro.android.feature.manage.recipe.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.recipe.Recipe
import id.kasirvippro.android.models.recipe.RecipeRestModel

interface EditRecipeContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int,data: Recipe?)
        fun setStock(value:String)
        fun setRawMaterial(value:String)
        fun setProduct(value:String)
        fun onPremiumPage(isPremium:Boolean)
        fun openChooseRawmaterial()
        fun setRawMaterialName(data: RawMaterial?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheck(stock:String)
        fun updateRawMaterial(data: RawMaterial?)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context):String?
        fun callEditProductAPI(context: Context, model: RecipeRestModel, id:String,stock:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEditProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}