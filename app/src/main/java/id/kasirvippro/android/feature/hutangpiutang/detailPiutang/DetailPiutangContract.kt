package id.kasirvippro.android.feature.hutangpiutang.detailPiutang

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.models.hutangpiutang.DetailPiutang
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel

interface DetailPiutangContract {

    interface View : BaseViewImpl {
        fun onClose(status:Int)
        fun setCustomer(name: String?,email:String?,phone:String?,address:String?)
        fun showMessage(code: Int, msg: String?)
        fun setPiutang(tagihan:String,piutang:String,total:String,jatuhTempo:String)
        fun setList(list:List<DetailPiutang.Data>)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun getTitleName():String
        fun loadDetailCustomer()
        fun loadHutang()


    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDetailCustomer(context: Context, restModel:CustomerRestModel, id:String)
        fun callGetHutang(context: Context, restModel: HutangPiutangRestModel, id:String)

    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetDetailCustomer(data:Customer)
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessGetHutang(data:DetailPiutang)

    }


}