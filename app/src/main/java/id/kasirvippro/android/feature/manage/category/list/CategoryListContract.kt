package id.kasirvippro.android.feature.manage.category.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.models.category.CategoryRestModel


interface CategoryListContract {

    interface View : BaseViewImpl {
        fun setCategorys(list:List<Category>)
        fun setData(list:List<Category>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openAddCategoryPage()
        fun openEditCategoryPage(data:Category)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadCategories()
        fun deleteCategory(id:String,position:Int,increment:String)
        fun searchCategory(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetCategoriesAPI(context: Context, restModel:CategoryRestModel)
        fun callDeleteCategoryAPI(context: Context, restModel:CategoryRestModel, id:String)
        fun callSearchCategoryAPI(context: Context, restModel:CategoryRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetCategories(list:List<Category>)
        fun onSuccessDeleteCategory(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}