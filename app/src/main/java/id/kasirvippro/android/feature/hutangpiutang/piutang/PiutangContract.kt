package id.kasirvippro.android.feature.hutangpiutang.piutang

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel
import id.kasirvippro.android.models.hutangpiutang.Piutang

interface PiutangContract {

    interface View : BaseViewImpl {
        fun setInfo(sum:String,sumRupiah:String,jatuhTempo:String,belumLunas:String)
        fun setList(list:List<Piutang.Data>)
        fun showErrorMessage(code: Int, msg: String)
        fun openLastPiutangPage()
        fun openPiutangPage()
        fun openDetailStruk(id: String)

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
        fun onSuccessGetHutang(data:Piutang)
        fun onFailedAPI(code:Int,msg:String)
    }


}