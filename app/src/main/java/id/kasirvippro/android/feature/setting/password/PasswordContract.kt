package id.kasirvippro.android.feature.setting.password

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.user.UserRestModel

interface PasswordContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(lama:String,baru:String,konfirmasi:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callUpdateAPI(context: Context, model:UserRestModel,lama:String,baru:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAPI(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}