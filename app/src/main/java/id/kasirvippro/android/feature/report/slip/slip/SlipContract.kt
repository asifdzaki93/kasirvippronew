package id.kasirvippro.android.feature.report.slip.slip

import android.content.Context
import android.content.Intent
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl

interface SlipContract {

    interface View : BaseViewImpl {
        fun setStore(name:String?, address: String?, phone:String?)
        fun setInfo(name:String,job:String,date:String)
        fun setDetail(penjualan:String,gaji:String,komisi:String?,tunjangan:String?,potongan:String?,kehadiran:String?)
        fun setTotal(pendapatan:String,pengurang:String,total:String)
        fun getParentLayout(): NestedScrollView
        fun showMessage(code: Int, msg: String?)
        fun onPremiumPage(isPremium:Boolean)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun setData()
        fun onCheckShare()
        fun onCheckDownload()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getUserPaket(context: Context): String?
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onFailedAPI(code:Int,msg:String)
    }


}