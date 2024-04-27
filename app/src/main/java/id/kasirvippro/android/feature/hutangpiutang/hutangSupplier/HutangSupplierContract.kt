package id.kasirvippro.android.feature.hutangpiutang.hutangSupplier

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel
import id.kasirvippro.android.models.supplier.Supplier


interface HutangSupplierContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Supplier>)
        fun showErrorMessage(code: Int, msg: String)
        fun reloadData()
        fun openDetailHutangPage(data:Supplier)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadHutang()
        fun searchHutang(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetHutangAPI(context: Context, restModel:HutangPiutangRestModel)
        fun callSearchHutangAPI(context: Context, restModel:HutangPiutangRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetHutang(list:List<Supplier>)
        fun onFailedAPI(code:Int,msg:String)
    }


}