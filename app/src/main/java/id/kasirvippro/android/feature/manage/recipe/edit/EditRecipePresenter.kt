package id.kasirvippro.android.feature.manage.recipe.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.recipe.Recipe
import id.kasirvippro.android.models.recipe.RecipeRestModel
import id.kasirvippro.android.utils.AppConstant


class EditRecipePresenter(val context: Context, val view: EditRecipeContract.View) : BasePresenter<EditRecipeContract.View>(),
    EditRecipeContract.Presenter, EditRecipeContract.InteractorOutput {

    private var interactor = EditRecipeInteractor(this)
    private var restModel = RecipeRestModel(context)
    private var data : Recipe ?= null
    private var premium:Boolean = false
    private var rawmatrial: RawMaterial?=null

    override fun onViewCreated(intent:Intent) {


        data = intent.getSerializableExtra(AppConstant.DATA) as Recipe
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED,data)
            return
        }

        checkProduct()
        premium = "1" == interactor.getUserPaket(context)
        //view.onPremiumPage(premium)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }


    override fun onCheck(stock: String) {
        if(stock.isBlank() || stock.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_stock))
            return
        }

        interactor.callEditProductAPI(context,restModel, data?.id_recipe!!,stock)
    }

    override fun updateRawMaterial(data: RawMaterial?) {
        rawmatrial = data
        view.setRawMaterialName(data)
    }

    override fun onSuccessEditProduct(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK,data)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    private fun checkProduct(){
        data?.let {
            view.setStock(it.stock!!)
            view.setRawMaterial(it.name!!)
            view.setProduct(it.name_product!!)
        }
    }


}