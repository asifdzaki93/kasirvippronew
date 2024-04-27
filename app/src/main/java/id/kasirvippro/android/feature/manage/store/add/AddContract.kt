package id.kasirvippro.android.feature.manage.store.add

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.currency.Currency
import id.kasirvippro.android.models.currency.CurrencyRestModel
import id.kasirvippro.android.models.store.StoreRestModel

interface AddContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openCurrencies(title:String, list: List<DialogModel>, selected: DialogModel?)
        fun setCurrencyName(name:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheckCurrency()
        fun setSelectedCurrency(data: DialogModel)
        fun onCheck(name:String,email:String,phone:String,address:String,pajak:String,service:String,currency:String,footer:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetCurrenciesAPI(context: Context, restModel: CurrencyRestModel)
        fun callAddAPI(context: Context,model:StoreRestModel,name:String,email:String,phone:String,address:String,pajak:String,service:String,currency:String,footer:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAdd(msg: String?)
        fun onSuccessGetCurrencies(list:List<Currency>)
        fun onFailedAPI(code:Int,msg:String)
    }


}