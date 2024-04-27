package id.kasirvippro.android.feature.setting.account

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel

interface AccountContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openImageChooser()
        fun loadPhoto(path:String)
        fun setInfo(user: User)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(name:String,email:String,phone:String,address:String)
        fun onCheckPhoto()
        fun setImagePhotoPath(path:String?)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun loadProfile(context: Context):User?
        fun callUpdateAPI(context: Context,model:UserRestModel,name:String,email:String,phone:String,address:String,img:String?)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessUpdate(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}