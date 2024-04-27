package id.kasirvippro.android.feature.setting.account

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.utils.PermissionUtil


class AccountPresenter(val context: Context, val view: AccountContract.View) : BasePresenter<AccountContract.View>(),
    AccountContract.Presenter, AccountContract.InteractorOutput {

    private var interactor = AccountInteractor(this)
    private var restModel = UserRestModel(context)
    private var photoPath:String? = null
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var photoPermission:PermissionCallback

    override fun onViewCreated() {
        photoPermission = object : PermissionCallback {
            override fun onSuccess() {
                view.openImageChooser()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }

        val user = interactor.loadProfile(context)
        user?.let {
            view.setInfo(it)
        }
    }

    override fun onCheck(name:String,email:String,phone:String,address:String) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name))
            return
        }

/*        if(email.isBlank() || email.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_email))
            return
        }

        if(!Helper.isEmailValid(email)){
            view.showMessage(999,context.getString(R.string.err_email_format))
            return
        }*/

        if(phone.isBlank() || phone.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_phone))
            return
        }

        if(!Helper.isPhoneValid(phone)){
            view.showMessage(999,context.getString(R.string.err_phone_format))
            return
        }

        if(address.isBlank() || address.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_address))
            return
        }

        interactor.callUpdateAPI(context,restModel,name,email,phone,address,photoPath)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessUpdate(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onCheckPhoto() {
        permissionUtil.checkCameraPermission(photoPermission)
    }

    override fun setImagePhotoPath(path: String?) {
        photoPath = path
    }
}