package id.kasirvippro.android.feature.qrCode

import android.content.Context
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel

interface QrCodeContract {

    interface View : BaseViewImpl {
        fun setProfile(subdomain: String?)
        fun showMessage(code: Int, msg: String?)
        fun setInfo(user: User)
        fun getParentLayout(): NestedScrollView
        fun loadProfile()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheckShare()
        fun loadProfile()
        fun onCheckDownload()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun getToken(context: Context):String
        fun callGetProfileAPI(context: Context, restModel: UserRestModel)
        fun onRestartDisposable()
        fun loadProfile(context: Context):User?
        fun saveUser(user: User)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessGetProfile(list:List<User>)
    }


}