package id.kasirvippro.android.feature.manage.ongkir.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.ongkir.Ongkir
import id.kasirvippro.android.models.ongkir.OngkirRestModel
import id.kasirvippro.android.utils.AppConstant

class EditOngkirPresenter(val context: Context, val view: EditOngkirContract.View) : BasePresenter<EditOngkirContract.View>(),
    EditOngkirContract.Presenter, EditOngkirContract.InteractorOutput {


    private var interactor: EditOngkirInteractor = EditOngkirInteractor(this)
    private var categoryRestModel = OngkirRestModel(context)
    private var data:Ongkir? = null


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as Ongkir
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            view.setName(it.name_ongkir)
            view.setNominal(it.nominal)
        }
    }

    override fun onCheck(name_ongkir: String, nominal: String) {
        if(name_ongkir.isBlank() || name_ongkir.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_category))
            return
        }

        if(nominal.isBlank() || nominal.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_category))
            return
        }

        interactor.callEditCategoryAPI(context,categoryRestModel,data?.id_ongkir!!,name_ongkir,nominal)

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessEditCategory(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedEditCategory(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

}