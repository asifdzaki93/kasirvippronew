package id.kasirvippro.android.feature.manage.ongkir.add

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.ongkir.OngkirRestModel

class AddOngkirPresenter(val context: Context, val view: AddOngkirContract.View) : BasePresenter<AddOngkirContract.View>(),
    AddOngkirContract.Presenter, AddOngkirContract.InteractorOutput {


    private var interactor: AddOngkirInteractor = AddOngkirInteractor(this)
    private var categoryRestModel = OngkirRestModel(context)


    override fun onViewCreated() {

    }

    override fun onCheck(name_ongkir: String, nominal: String) {
        if(name_ongkir.isBlank() || name_ongkir.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_category))
            return
        }

        if(nominal.isBlank() || nominal.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_sell))
            return
        }

        interactor.callAddCategoryAPI(context,categoryRestModel,name_ongkir, nominal)
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