package id.kasirvippro.android.feature.manage.divisi.add

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.divisi.DivisiRestModel

class AddDivisiPresenter(val context: Context, val view: AddDivisiContract.View) : BasePresenter<AddDivisiContract.View>(),
    AddDivisiContract.Presenter, AddDivisiContract.InteractorOutput {
    private var interactor: AddDivisiInteractor = AddDivisiInteractor(this)
    private var categoryRestModel = DivisiRestModel(context)


    override fun onViewCreated() {

    }

    override fun onCheck(name_ongkir: String) {
        if(name_ongkir.isBlank() || name_ongkir.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_category))
            return
        }

        interactor.callAddCategoryAPI(context,categoryRestModel,name_ongkir)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessAddCategory(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAddCategory(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

}