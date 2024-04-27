package id.kasirvippro.android.feature.qrCode

import android.content.Context
import android.graphics.Bitmap
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.utils.ImageHelper
import id.kasirvippro.android.utils.PermissionUtil


class QrCodePresenter(val context: Context, val view: QrCodeContract.View) : BasePresenter<QrCodeContract.View>(),
    QrCodeContract.Presenter, QrCodeContract.InteractorOutput {

    private var interactor = QrCodeInteractor(this)
    private var restModel = UserRestModel(context)
    private var userRestModel = UserRestModel(context)
    private var user:User?=null
    private var qrImage : Bitmap? = null
    private val permissionUtil = PermissionUtil(context)
    private lateinit var storagePermission: PermissionCallback
    private lateinit var downloadPermission: PermissionCallback

    override fun onViewCreated() {

        user = interactor.loadProfile(context)
        user?.let {
            view.setInfo(it)
        }

        storagePermission = object :PermissionCallback{
            override fun onSuccess() {
                ImageHelper.takeScreenshot(context,view.getParentLayout())
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_write_external))
            }
        }

        downloadPermission = object :PermissionCallback{
            override fun onSuccess() {
                ImageHelper.downloadScreenshot(context,view.getParentLayout())
            }

            override fun onFailed() {
                onFailedAPI(999,context.getString(R.string.reason_permission_write_external))
            }
        }

        loadProfile()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun loadProfile() {
        interactor.callGetProfileAPI(context,userRestModel)
    }

    override fun onSuccessGetProfile(list: List<User>) {
        if(list.isEmpty()){
            onFailedAPI(999,"Akun tidak ditemukan")
            return
        }

        val user = list[0]
        interactor.saveUser(user)
        view.setProfile(user.subdomain)
    }


    override fun onCheckShare() {
        permissionUtil.checkWriteExternalPermission(storagePermission)
    }

    override fun onCheckDownload() {
        permissionUtil.checkWriteExternalPermission(downloadPermission)
    }



}