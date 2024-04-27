package id.kasirvippro.android.feature.afiliate.detailAfiliate

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.User

interface DetailAfiliateContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun setInfo(user: User)
        fun openShare(code:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCopy()
        fun onShare()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun loadProfile(context: Context):User?
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onFailedAPI(code:Int,msg:String)
    }


}