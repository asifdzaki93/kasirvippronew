package id.kasirvippro.android.feature.addOn.linkStore

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel

interface LinkStoreContract {

    interface View : BaseViewImpl {
        fun setProfile(subdomain: String?,signup: String?,screen: String?)
        fun showMessage(code: Int, msg: String?)
        fun setInfo(user: User)
        fun openShare(code:String)
        fun openShare2(code:String)
        fun openShare3(code:String)
        fun loadProfile()
        fun onMasterPage(isPremium:Boolean)
        fun onAdminPage()
        fun onSalesPage()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCopy()
        fun onCopy2()
        fun onCopy3()
        fun onShare()
        fun onShare2()
        fun onShare3()
        fun loadProfile()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String
        fun callGetProfileAPI(context: Context, restModel: UserRestModel)
        fun loadProfile(context: Context):User?
        fun saveUser(user: User)
        fun getUserLevel(context: Context):String?
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessGetProfile(list:List<User>)
    }


}