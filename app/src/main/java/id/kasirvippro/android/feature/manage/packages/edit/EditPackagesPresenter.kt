package id.kasirvippro.android.feature.manage.packages.edit

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.packages.Packages
import id.kasirvippro.android.models.packages.PackagesRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.PermissionUtil

class EditPackagesPresenter(val context: Context, val view: EditPackagesContract.View) : BasePresenter<EditPackagesContract.View>(),
    EditPackagesContract.Presenter, EditPackagesContract.InteractorOutput {


    private var interactor: EditPackagesInteractor = EditPackagesInteractor(this)
    private var packagesRestModel = PackagesRestModel(context)
    private var data:Packages? = null
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var photoPermission:PermissionCallback
    private var photoPath:String? = null

    override fun onViewCreated(intent: Intent) {

        photoPermission = object : PermissionCallback {
            override fun onSuccess() {
                view.openImageChooser()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }
        data = intent.getSerializableExtra(AppConstant.DATA) as Packages
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            view.setPackagesName(it.name_packages, it.price)
            view.loadPhoto(it.img!!)
        }
    }

    override fun onCheck(name: String, price: String) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_category))
            return
        }
        if(price.isBlank() || price.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_sell))
            return
        }

        interactor.callEditPackagesAPI(context,packagesRestModel,data?.id_product!!,name,price,photoPath)

    }

    override fun onCheckPhoto() {
        permissionUtil.checkCameraPermission(photoPermission)
    }

    override fun setImagePhotoPath(path: String?) {
        photoPath = path
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