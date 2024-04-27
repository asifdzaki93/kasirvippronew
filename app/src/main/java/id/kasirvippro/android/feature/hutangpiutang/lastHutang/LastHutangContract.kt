package id.kasirvippro.android.feature.hutangpiutang.lastHutang

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.hutangpiutang.Hutang
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel


interface LastHutangContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Hutang.Data>)
        fun showErrorMessage(code: Int, msg: String)
        fun reloadData()
        fun openDetailStruk(id: String)

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
        fun onSuccessGetHutang(list:List<Hutang.Data>)
        fun onFailedAPI(code:Int,msg:String)
    }


}