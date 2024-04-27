package id.kasirvippro.android.feature.manage.recipe.add

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.recipe.RecipeRestModel


class AddRecipePresenter(val context: Context, val view: AddRecipeContract.View) : BasePresenter<AddRecipeContract.View>(),
    AddRecipeContract.Presenter, AddRecipeContract.InteractorOutput {

    private var interactor = AddRecipeInteractor(this)
    private var restModel = RecipeRestModel(context)
    private var premium:Boolean = false
    private var rawmatrial: RawMaterial?=null
    override fun onViewCreated() {
        premium = "1" == interactor.getUserPaket(context)
        //view.onPremiumPage(premium)
        checkProduct()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
    override fun onCheck(stock: String) {
        if(stock.isBlank() || stock.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_stock))
            return
        }
        if(rawmatrial == null){
            view.showMessage(999,"Raw Material type must be selected")
            return
        }

        val idproduct = view.getIdProduct()
        interactor.callAddProductAPI(context,restModel,stock,rawmatrial!!.id_raw_material!!,idproduct!!)
    }

    override fun updateRawMaterial(data: RawMaterial?) {
        rawmatrial = data
        view.setRawMaterialName(data)
    }

    override fun onSuccessAddProduct(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    private fun checkProduct(){
        val detail = view.getDetail()
        view.setProduct()
    }

}