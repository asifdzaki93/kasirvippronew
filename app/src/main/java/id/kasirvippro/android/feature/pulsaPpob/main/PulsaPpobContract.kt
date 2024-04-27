package id.kasirvippro.android.feature.pulsaPpob.main

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel

interface PulsaPpobContract {

    interface View : BaseViewImpl {
        fun openPulsa()
        fun openSmsTelpon()
        fun openPaketData()
        fun openTokenPln()
        fun openEmoney()
        fun openVoucher()
        fun openGame()
        fun openMultifinance()
        fun openPascabayar()
        fun openInternet()
        fun openPln()
        fun openBpjs()
        fun openWebviewPage(title:String,url:String)
        fun onPremiumPage(isPremium:Boolean)
        fun loadProfile()
        fun setProfile(saldo: String?)
        fun showMessage(code: Int, msg: String?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun getPremiumUrl():String
        fun loadProfile()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun getUserPaket(context: Context):String?
        fun loadProfile(context: Context): User?
        fun callGetProfileAPI(context: Context, restModel: UserRestModel)
        fun saveUser(user: User)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessGetProfile(list:List<User>)
    }


}