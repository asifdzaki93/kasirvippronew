package id.kasirvippro.android.feature.main

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.User

interface MainContract {

    interface View : BaseViewImpl {
        fun selectMenu(resId:Int)
        fun setInfo(user: User)
        fun loadStore()
        fun showErrorMessage(code: Int, msg: String)
        fun openCS()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun setSelectedIdMenu(id:Int)
        fun getSelectedIdMenu():Int
        fun onDestroy()
        fun onLogout()
    }

    interface Interactor : BaseInteractorImpl{
        fun onDestroy()
        fun onRestartDisposable()
        fun loadProfile(context: Context):User?
        fun onLogout()
    }

    interface InteractorOutput : BaseInteractorOutputImpl{
        fun onFailedAPI(code:Int,msg:String)
        fun onLogoutSuccess()
    }


}