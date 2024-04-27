package id.kasirvippro.android.feature.manage.packages.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.packages.Packages
import id.kasirvippro.android.models.packages.PackagesRestModel


interface PackagesListContract {

    interface View : BaseViewImpl {
        fun setCategorys(list:List<Packages>)
        fun setData(list:List<Packages>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openAddCategoryPage()
        fun openEditCategoryPage(data:Packages)
        fun openAdd(data:Packages)
        fun openList(data:Packages)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadCategories()
        fun deleteCategory(id:String,position:Int)
        fun searchCategory(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetCategoriesAPI(context: Context, restModel:PackagesRestModel)
        fun callDeleteCategoryAPI(context: Context, restModel:PackagesRestModel, id:String)
        fun callSearchCategoryAPI(context: Context, restModel:PackagesRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetCategories(list:List<Packages>)
        fun onSuccessDeleteCategory(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}