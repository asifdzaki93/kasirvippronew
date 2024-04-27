package id.kasirvippro.android.feature.manage.product.addCategory

import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.models.category.CategoryRestModel


class AddCategoryPresenter(val context: Context, val view: AddCategoryContract.View) : BasePresenter<AddCategoryContract.View>(),
    AddCategoryContract.Presenter, AddCategoryContract.InteractorOutput {

    private var interactor = AddCategoryInteractor(this)
    private var restModel = CategoryRestModel(context)

    override fun onViewCreated() {

    }

    override fun onCheck(name:String) {
        if(name.isNullOrBlank() || name.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_category))
            return
        }


        interactor.callAddCategoryAPI(context,restModel,name)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessAdd(data:List<Category>) {
        if(data.isEmpty()){
            onFailedAPI(999,"There is an error")
            return
        }
        view.onSuccess(data[0])
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }
}