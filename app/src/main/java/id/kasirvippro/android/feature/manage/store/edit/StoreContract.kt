package id.kasirvippro.android.feature.manage.store.edit

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.currency.Currency
import id.kasirvippro.android.models.currency.CurrencyRestModel
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel

interface StoreContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun onClose(status:Int)
        fun openImageChooser()
        fun loadPhoto(path:String)
        fun setStoreName(value: String?)
        fun setNohp(value: String?)
        fun setAdress(value: String?)
        fun setEmail(value: String?)
        fun setTaxt(value: String?)
        fun setSc(value: String?)
        fun setShift(value: String?)
        fun setCurrency(value: String?)
        fun setFooter(value: String?)
        fun openCurrencies(title:String, list: List<DialogModel>, selected: DialogModel?)
        fun setCurrencyName(name:String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun onCheck(name:String,email:String,phone:String,address:String,pajak:String,service:String,currency:String,footer:String)
        fun setActive(value:String?)
        fun setImagePhotoPath(path:String?)
        fun onCheckPhoto()
        fun onCheckCurrency()
        fun setSelectedCurrency(data:DialogModel)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetStoreAPI(context: Context,model:StoreRestModel)
        fun callGetCurrenciesAPI(context: Context, restModel: CurrencyRestModel)
        fun callUpdateAPI(context: Context,model:StoreRestModel,id:String,name:String,email:String,phone:String,address:String,pajak:String,service:String,currency:String,footer:String,shift:String,img:String?)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetStore(list:List<Store>?)
        fun onSuccessGetCurrencies(list:List<Currency>)
        fun onSuccessUpdate(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}