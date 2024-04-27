package id.kasirvippro.android.feature.pulsaPpob.dataProduct
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.pulsaPpob.PulsaPpob
import id.kasirvippro.android.models.pulsaPpob.PulsaPpobRestModel

interface DataProductContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setData(list:List<PulsaPpob>)
        fun showErrorMessage(code: Int, msg: String?)
        fun reloadData()
        fun openOrderPage(data:PulsaPpob)
        fun getValue():String
        fun getJenis(): String
        fun getBrand(): String
        fun setData(category:String?,brand:String?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheck(name:String)
        fun onSearch(search:String)
        fun searchPrefix(search:String)
        fun loadData()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callPrefixAPI(context: Context, model: PulsaPpobRestModel, category:String, brand:String, name:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessPrefix(list:List<PulsaPpob>)
        fun onFailedAPI(code:Int,msg:String)
    }


}