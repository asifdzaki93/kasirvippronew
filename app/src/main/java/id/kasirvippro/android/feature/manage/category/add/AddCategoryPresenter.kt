package id.kasirvippro.android.feature.manage.category.add

import android.app.Activity
import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.category.CategoryRestModel
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.CategorySQL
import id.kasirvippro.android.sqlite.Model.CategorySQLAdd
import id.kasirvippro.android.utils.AppSession

class AddCategoryPresenter(val context: Context, val view: AddCategoryContract.View) : BasePresenter<AddCategoryContract.View>(),
    AddCategoryContract.Presenter, AddCategoryContract.InteractorOutput {


    private var interactor: AddCategoryInteractor = AddCategoryInteractor(this)
    private var categoryRestModel = CategoryRestModel(context)


    override fun onViewCreated() {

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
                    interactor.callAddCategoryAPI(context,categoryRestModel,name)
                    return
                }
            }
        }
        val categoryAdd =
            CategorySQLAdd(
                "0",
                key!!,
                name
            )
        val category =
            CategorySQL(
                "0",
                "0",
                key!!,
                name
            )
        var result = dataManager.addCategoryAdd(categoryAdd!!)
        var result2 = dataManager.addCategory(category!!)
        Toast.makeText(context,"Add Category Local", Toast.LENGTH_SHORT).show()
        (context as AddCategoryActivity).hideLoading()
        view.onClose(Activity.RESULT_OK)
        (context as Activity).finish()

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