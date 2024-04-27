package id.kasirvippro.android.feature.manage.recipe.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.recipe.Recipe
import id.kasirvippro.android.models.recipe.RecipeRestModel
import id.kasirvippro.android.utils.PermissionUtil

class RecipeListPresenter(val context: Context, val view: RecipeListContract.View) : BasePresenter<RecipeListContract.View>(),
    RecipeListContract.Presenter, RecipeListContract.InteractorOutput {

    private var interactor: RecipeListInteractor = RecipeListInteractor(this)
    private var restModel = RecipeRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var sort = false
    private var id = String()

    override fun onViewCreated() {
        checkProduct()
       loadProducts()
    }

    override fun loadProducts() {
        val idproduct = view.getIdProduct()
        interactor.callGetProductsAPI(context,restModel,idproduct!!)
    }

    override fun deleteProduct(id: String,position:Int) {
                    interactor.callDeleteProductAPI(context,restModel,id)
    }
    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetProducts(list: List<Recipe>) {
        sort = false
        view.setProducts(list)
    }

    fun setProduct(list: List<Recipe>){
        view.setProducts(list)
    }

    override fun onSuccessDeleteProduct(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
    override fun onAddPage() {
        view.openAddPage(id)
    }

    private fun checkProduct(){
        val detail = view.getDetail()
        view.setProduct(detail!!)
    }


}