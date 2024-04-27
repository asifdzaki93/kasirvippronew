package id.kasirvippro.android.feature.manage.discount.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.discount.DiscountRestModel

interface EditContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun chooseRupiah()
        fun choosePercent()
        fun onClose(status:Int)
        fun setData(data:Discount?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheck(code: String,desc:String,nominal:String)
        fun setJenis(jenis:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callEditAPI(context: Context, model:DiscountRestModel,id:String,code:String,desc:String,jenis:String,nominal:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessEdit(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}