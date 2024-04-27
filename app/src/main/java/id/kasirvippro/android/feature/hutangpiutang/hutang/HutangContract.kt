package id.kasirvippro.android.feature.hutangpiutang.hutang

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.hutangpiutang.Hutang
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel

interface HutangContract {

    interface View : BaseViewImpl {
        fun setInfo(sum:String,sumRupiah:String,jatuhTempo:String,belumLunas:String)
        fun setList(list:List<Hutang.Data>)
        fun showErrorMessage(code: Int, msg: String)
        fun openLastHutangPage()
        fun openHutangPage()
        fun openDetail(id: String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadHutang()

    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetHutangAPI(context: Context, restModel: HutangPiutangRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetHutang(data:Hutang)
        fun onFailedAPI(code:Int,msg:String)
    }


}