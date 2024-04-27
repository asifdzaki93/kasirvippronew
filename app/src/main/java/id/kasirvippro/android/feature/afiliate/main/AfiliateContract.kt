package id.kasirvippro.android.feature.afiliate.main

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel

interface AfiliateContract {

    interface View : BaseViewImpl {
        fun openAfiliatePage()
        fun openNetworkPage()
        fun openCommisionPage()
        fun openWebviewPage(title:String,url:String)
        fun onMasterPage(isPremium:Boolean)
        fun onAdminPage()
        fun onSalesPage()
        fun showErrorMessage(code: Int, msg: String)
        fun loadProfile()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadProfile()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun getUserLevel(context: Context):String?
        fun getUserPaket(context: Context):String?
        fun callGetProfileAPI(context: Context, restModel: UserRestModel)
        fun saveUser(user: User)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetProfile(list:List<User>)
        fun onFailedAPI(code:Int,msg:String)
    }


}