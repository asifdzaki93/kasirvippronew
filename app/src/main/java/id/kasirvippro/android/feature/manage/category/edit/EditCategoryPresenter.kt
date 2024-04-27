package id.kasirvippro.android.feature.manage.category.edit

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.category.Category
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.CategorySQL
import id.kasirvippro.android.sqlite.Model.CategorySQLUpdate
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession

class EditCategoryPresenter(val context: Context, val view: EditCategoryContract.View) : BasePresenter<EditCategoryContract.View>(),
    EditCategoryContract.Presenter, EditCategoryContract.InteractorOutput {


    private var interactor: EditCategoryInteractor = EditCategoryInteractor(this)
    private var categoryRestModel = CategoryRestModel(context)
    private var data:Category? = null


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as Category
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            view.setCategoryName(it.name_category)
        }
    }

    override fun onCheck(name: String) {
        if(name.isNullOrBlank() || name.isNullOrEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_category))
            return
        }

        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context?.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val dataManager = DataManager (context)
        val appSession = AppSession()
        val key = appSession.getToken(context)

        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    interactor.callEditCategoryAPI(context,categoryRestModel,data?.id_category!!,name)
                    return
                }
            }
        }
        val categoryUpdate = CategorySQLUpdate(
            data?.inc!!,
            key!!,
            data?.id_category!!,
            name
        )
        val category =
            CategorySQL(
                data?.inc!!,
                key!!,
                data?.id_category!!,
                name
            )
        var result = dataManager.addCategoryUpdate(categoryUpdate!!)
        var result2 = dataManager.updateCategory(category!!)
        Toast.makeText(context,"Update Category Local", Toast.LENGTH_SHORT).show()
        (context as EditCategoryActivity)
        view.onClose(Activity.RESULT_OK)
        (context as Activity).finish()

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