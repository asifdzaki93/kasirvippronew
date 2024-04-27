package id.kasirvippro.android.feature.register

import android.content.Context
import id.kasirvippro.android.models.currency.Currency
import id.kasirvippro.android.models.currency.CurrencyRestModel
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.currency.Decimal
import id.kasirvippro.android.models.typestore.TypeStore
import id.kasirvippro.android.models.user.UserRestModel

interface RegisterContract {

    interface View : BaseViewImpl {
        fun onClose(status:Int)
        fun showMessage(code: Int, msg: String?)
        fun openCurrencies(title:String, list: List<DialogModel>, selected: DialogModel?)
        fun openDecimal(title:String, list: List<DialogModel>, selected: DialogModel?)
        fun setCurrencyName(name:String)
        fun setTypestoreName(data:TypeStore?)
        fun setTDecimalName(data:Decimal?)
        fun openChooseTypestore()
        fun openChooseDecimal()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheck(toko:String,currency:String,name:String,email:String,telpon:String,password:String,alamat:String,referal:String,typestore:String,decimal:String)
        fun onCheckCurrency()
        fun setSelectedCurrency(data:DialogModel)
        fun updateTypestore(data: TypeStore?)
        fun updateDecimal(data: Decimal?)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetCurrenciesAPI(context: Context, restModel: CurrencyRestModel)
        fun callRegisterAPI(context: Context, model: UserRestModel, toko:String,currency:String,name:String,email:String,telpon:String,password:String,alamat:String,referal:String,typestore:String,decimal:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessAPI(msg: String?)
        fun onSuccessGetCurrencies(list:List<Currency>)
        fun onFailedAPI(code:Int,msg:String)
    }


}