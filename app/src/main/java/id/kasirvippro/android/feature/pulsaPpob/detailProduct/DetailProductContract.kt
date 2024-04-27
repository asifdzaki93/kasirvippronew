package id.kasirvippro.android.feature.pulsaPpob.detailProduct
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.pulsaPpob.DetailPpob
import id.kasirvippro.android.models.pulsaPpob.PulsaPpobRestModel

interface DetailProductContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun setData(list:List<DetailPpob>)
        fun showErrorMessage(code: Int, msg: String?)
        fun reloadData()
        fun openOrderPage(data:DetailPpob)
        fun getValue():String
        fun getJenis(): String
        fun getBrand(): String
        fun getPhone():String
        fun getSku():String
        fun getRef():String
        fun getHa():String
        fun getPay():Double
        fun openSuccessPage(id:String)
        fun setData(category:String?,brand:String?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheck(pin:String)
        fun searchPrefix(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callPrefixAPI(context: Context, model: PulsaPpobRestModel, brand:String, name:String)
        fun callOrderAPI(context: Context, model: PulsaPpobRestModel, phone:String, sku:String, hargajual:String, pin:String, ref_id:String, hargaagent:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessOrder(msg: String?)
        fun onSuccessPrefix(list:List<DetailPpob>)
        fun onFailedAPI(code:Int,msg:String)
    }


}