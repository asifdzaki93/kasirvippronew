package id.kasirvippro.android.feature.pulsaPpob.paketData

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.pulsaPpob.PulsaPpob
import id.kasirvippro.android.models.pulsaPpob.PulsaPpobRestModel

interface PaketDataContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setData(list:List<PulsaPpob>)
        fun showErrorMessage(code: Int, msg: String?)
        fun reloadData()
        fun openOrderPage(data:PulsaPpob)
        fun getValue():String
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(name:String)
        fun onSearch(search:String)
        fun searchPrefix(search:String)
        fun loadData()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callPrefixAPI(context: Context, model: PulsaPpobRestModel, jenis:String, name:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessPrefix(list:List<PulsaPpob>)
        fun onFailedAPI(code:Int,msg:String)
    }


}