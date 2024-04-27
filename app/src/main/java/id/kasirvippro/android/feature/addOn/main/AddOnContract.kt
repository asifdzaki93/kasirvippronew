package id.kasirvippro.android.feature.addOn.main

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel

interface AddOnContract {

    interface View : BaseViewImpl {
        fun openLinkPage()
        fun openWorkPage()
        fun openMedicalHistoryPage()
        fun openRecipePage()
        fun onMasterPage(isPremium:Boolean)
        fun onAdminPage()
        fun onSalesPage()
        fun loadProfile()
        fun showErrorMessage(code: Int, msg: String)
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