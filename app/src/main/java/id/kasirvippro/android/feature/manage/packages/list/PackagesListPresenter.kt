package id.kasirvippro.android.feature.manage.packages.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.packages.Packages
import id.kasirvippro.android.models.packages.PackagesRestModel

class PackagesListPresenter(val context: Context, val view: PackagesListContract.View) : BasePresenter<PackagesListContract.View>(),
    PackagesListContract.Presenter, PackagesListContract.InteractorOutput {

    private var interactor: PackagesListInteractor = PackagesListInteractor(this)
    private var categoryRestModel = PackagesRestModel(context)

    override fun onViewCreated() {
        loadCategories()
    }

    override fun loadCategories() {
        interactor.callGetCategoriesAPI(context,categoryRestModel)
    }

    fun setCategory(list: List<Packages>){
        view.setCategorys(list)
    }

    override fun deleteCategory(id: String,position:Int) {
        interactor.callDeleteCategoryAPI(context,categoryRestModel,id)
    }

    override fun searchCategory(search: String) {
        interactor.onRestartDisposable()
        if(search.isEmpty() || search.isBlank()){
           interactor.callGetCategoriesAPI(context,categoryRestModel)
        }
        else{
            interactor.callSearchCategoryAPI(context,categoryRestModel,search)

        }
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetCategories(list: List<Packages>) {
        view.setData(list)
    }

    override fun onSuccessDeleteCategory(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}