package id.kasirvippro.android.feature.initial

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.initial.InitialRestModel

interface InitialContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setData(modal_awal: String?,sift: String?)
        fun openChooseSift()
        fun openSuccessPage()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(modal_awal: String,sift:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetInitialAPI(context: Context, restModel: InitialRestModel)
       // fun callEditAPI(context: Context, model:InitialRestModel, id:String,modal_awal:String,sift:String)
        fun callInitialAPI(context: Context, model: InitialRestModel, modal_awal:String,sift:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEdit(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}