package id.kasirvippro.android.feature.manage.store.add

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.currency.Currency
import id.kasirvippro.android.models.currency.CurrencyRestModel
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.models.store.StoreRestModel


class AddPresenter(val context: Context, val view: AddContract.View) : BasePresenter<AddContract.View>(),
    AddContract.Presenter, AddContract.InteractorOutput {

    private var interactor = AddInteractor(this)
    private var restModel = StoreRestModel(context)
    private var currencyRestModel = CurrencyRestModel(context)
    private var currencies:ArrayList<DialogModel> = ArrayList()
    private var currency: DialogModel? = null

    override fun onViewCreated() {

    }

    override fun onCheck(name:String,email:String,phone:String,address:String,pajak:String,service:String,currency:String,footer:String) {
        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name))
            return
        }

/*        if(email.isNotBlank() && email.isNotEmpty()){
            if(!Helper.isEmailValid(email)){
                view.showMessage(999,context.getString(R.string.err_email_format))
                return
            }
        }*/

        if(phone.isBlank() || phone.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_phone))
            return
        }

        if(!Helper.isPhoneValid(phone)){
            view.showMessage(999,context.getString(R.string.err_phone_format))
            return
        }

        if(address.isBlank() || address.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_address))
            return
        }

        if(currency.isBlank() || currency.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_currency))
            return
        }


        val taxString = pajak
        val serviceString = service

        interactor.callAddAPI(context,restModel,name,email,phone,address,taxString,serviceString,currency,footer)
    }

    override fun onCheckCurrency() {
        if(currencies.isEmpty()){
            interactor.callGetCurrenciesAPI(context,currencyRestModel)
        }
        else{
            view.openCurrencies("Select Currency",currencies!!,currency)
        }
    }

    override fun setSelectedCurrency(data: DialogModel) {
        currency = data
        view.setCurrencyName(data.value!!)
    }

    override fun onSuccessGetCurrencies(list: List<Currency>) {
        if(list.isEmpty()){
            view.showMessage(999,"There are no categories yet")
            return
        }
        currencies = ArrayList()
        for(cat in list){
            val model = DialogModel()
            model.id = cat.id_currency
            model.value = cat.code_currency
            currencies.add(model)
        }
        view.openCurrencies("Select Currency",currencies!!,currency)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessAdd(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }


}