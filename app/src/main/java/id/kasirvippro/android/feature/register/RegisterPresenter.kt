package id.kasirvippro.android.feature.register

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.models.typestore.TypeStore
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.currency.Currency
import id.kasirvippro.android.models.currency.CurrencyRestModel
import id.kasirvippro.android.models.currency.Decimal
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.utils.Helper

class RegisterPresenter(val context: Context, val view: RegisterContract.View) : BasePresenter<RegisterContract.View>(),
    RegisterContract.Presenter, RegisterContract.InteractorOutput {

    private var interactor = RegisterInteractor(this)
    private val restModel = UserRestModel(context)
    private var currencyRestModel = CurrencyRestModel(context)
    private var currencies:ArrayList<DialogModel> = ArrayList()
    private var currenci: DialogModel? = null

    //private var decimals:ArrayList<DialogModel> = ArrayList()
    //private var decimal: DialogModel? = null

    private var typestore:TypeStore?=null
    private var decimal:Decimal?=null

    override fun onViewCreated() {

    }

    override fun onCheck(toko:String,currency:String,name:String,email:String,telpon:String,password:String,alamat:String,referal:String,typestore:String,decimal:String) {
        if(toko.isBlank() || toko.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_store))
            return
        }

        if(currency.isBlank() || currency.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_currency))
            return
        }

        if(name.isBlank() || name.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_name))
            return
        }

/*        if(email.isBlank() || email.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_email))
            return
        }

        if(!Helper.isEmailValid(email)){
            view.showMessage(999,context.getString(R.string.err_email_format))
            return
        }*/

        if(telpon.isBlank() || telpon.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_phone))
            return
        }

        if(!Helper.isPhoneValid(telpon)){
            view.showMessage(999,context.getString(R.string.err_phone_format))
            return
        }

        if(alamat.isBlank() || alamat.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_address))
            return
        }


        if(password.isBlank() || password.isEmpty()){
            view.showMessage(999,"Password must be filled")
            return
        }

        if(password.length < 6){
            view.showMessage(999,"Password minimum 6 characters")
            return
        }

        interactor.callRegisterAPI(context,restModel,toko,currency,name,email,telpon,password,alamat,referal,typestore,decimal)
    }

    override fun onCheckCurrency() {
        if(currencies.isEmpty()){
            interactor.callGetCurrenciesAPI(context,currencyRestModel)
        }
        else{
            view.openCurrencies("Select Currency",currencies!!,currenci)
        }
    }


    override fun setSelectedCurrency(data: DialogModel) {
        currenci = data
        view.setCurrencyName(data.value!!)
    }

    override fun onSuccessGetCurrencies(list: List<Currency>) {
        if(list.isEmpty()){
            view.showMessage(999,"There are no Select Type Store yet")
            return
        }
        currencies = ArrayList()
        for(cat in list){
            val model = DialogModel()
            model.id = cat.id_currency
            model.value = cat.code_currency
            currencies.add(model)
        }
        view.openCurrencies("Select Currency",currencies!!,currenci)
    }


    override fun onSuccessAPI(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun updateTypestore(data: TypeStore?) {
        typestore = data
        view.setTypestoreName(data)
    }

    override fun updateDecimal(data: Decimal?) {
        decimal = data
        view.setTDecimalName(data)
    }



}