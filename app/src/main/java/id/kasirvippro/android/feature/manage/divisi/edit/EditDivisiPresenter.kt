package id.kasirvippro.android.feature.manage.divisi.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.divisi.Divisi
import id.kasirvippro.android.models.divisi.DivisiRestModel
import id.kasirvippro.android.utils.AppConstant

class EditDivisiPresenter(val context: Context, val view: EditDivisiContract.View) : BasePresenter<EditDivisiContract.View>(),
    EditDivisiContract.Presenter, EditDivisiContract.InteractorOutput {


    private var interactor: EditDivisiInteractor = EditDivisiInteractor(this)
    private var categoryRestModel = DivisiRestModel(context)
    private var data:Divisi? = null


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as Divisi
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            view.setName(it.name_divisi)
        }
    }

    override fun onCheck(name_divisi: String) {
        if(name_divisi.isBlank() || name_divisi.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_category))
            return
        }

        interactor.callEditCategoryAPI(context,categoryRestModel,data?.id_divisi!!,name_divisi)

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