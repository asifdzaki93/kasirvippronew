package id.kasirvippro.android.feature.manage.recipe.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.recipe.Recipe
import id.kasirvippro.android.models.recipe.RecipeRestModel


interface RecipeListContract {

    interface View : BaseViewImpl {
            fun setProducts(list:List<Recipe>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openEditPage(data: Recipe)
        fun getIdProduct(): String?
        fun getDetail(): String?
        fun openAddPage(id: String)
        fun setProduct(value:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadProducts()
        fun deleteProduct(id:String,position:Int)
        fun onAddPage()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetProductsAPI(context: Context, restModel:RecipeRestModel, id_product:String)
        fun callDeleteProductAPI(context: Context, restModel:RecipeRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetProducts(list:List<Recipe>)
        fun onSuccessDeleteProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}