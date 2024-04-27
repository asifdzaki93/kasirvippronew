package id.kasirvippro.android.feature.manage.recipe.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.recipe.Recipe
import id.kasirvippro.android.models.recipe.RecipeRestModel

interface AddRecipeContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openEditPage(product:Recipe)
        fun onPremiumPage(isPremium:Boolean)
        fun getIdProduct(): String?
        fun getDetail(): String?
        fun setProduct()
        fun openChooseRawmaterial()
        fun setRawMaterialName(data: RawMaterial?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(stock:String)
        fun updateRawMaterial(data: RawMaterial?)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context):String?
        fun callAddProductAPI(context: Context, model: RecipeRestModel, stock:String,id_raw_material:String,id_product: String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAddProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}