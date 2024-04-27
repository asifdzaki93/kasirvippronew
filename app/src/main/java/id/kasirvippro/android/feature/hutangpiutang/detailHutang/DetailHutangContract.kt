package id.kasirvippro.android.feature.hutangpiutang.detailHutang

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.hutangpiutang.DetailHutang
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.supplier.SupplierRestModel

interface DetailHutangContract {

    interface View : BaseViewImpl {
        fun onClose(status:Int)
        fun setSupplier(name: String?,email:String?,phone:String?,address:String?)
        fun showMessage(code: Int, msg: String?)
        fun setPiutang(tagihan:String,piutang:String,total:String,jatuhTempo:String)
        fun setList(list:List<DetailHutang.Data>)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun getTitleName():String
        fun loadDetailSupplier()
        fun loadHutang()

    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDetailSupplier(context: Context, restModel:SupplierRestModel,id:String)
        fun callGetHutang(context: Context, restModel:HutangPiutangRestModel,id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetDetailSupplier(data:Supplier)
        fun onSuccessGetHutang(data:DetailHutang)
        fun onFailedAPI(code:Int,msg:String)
    }


}