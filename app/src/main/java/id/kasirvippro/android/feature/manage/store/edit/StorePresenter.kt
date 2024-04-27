package id.kasirvippro.android.feature.manage.store.edit

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.currency.Currency
import id.kasirvippro.android.models.currency.CurrencyRestModel
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.models.store.Store
import id.kasirvippro.android.models.store.StoreRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.PermissionUtil


class StorePresenter(val context: Context, val view: StoreContract.View) : BasePresenter<StoreContract.View>(),
    StoreContract.Presenter,
    StoreContract.InteractorOutput {

    private var interactor = StoreInteractor(this)
    private var restModel = StoreRestModel(context)
    private var data:Store ?= null
    private var newdata : Store ?= null
    private var shift:String? = ""
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var photoPermission: PermissionCallback
    private var photoPath:String? = null
    private var currencyRestModel = CurrencyRestModel(context)
    private var currencies:ArrayList<DialogModel> = ArrayList()
    private var currency: DialogModel? = null

    override fun onViewCreated(intent: Intent) {
        photoPermission = object : PermissionCallback{
            override fun onSuccess() {
                view.openImageChooser()
            }

            override fun onFailed() {
                view.showMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }

        data = intent.getSerializableExtra(AppConstant.DATA) as Store
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }
        // view.setInfo(data!!)
        checkStore()
    }

    override fun onCheckPhoto() {
        permissionUtil.checkCameraPermission(photoPermission)
    }


    override fun setImagePhotoPath(path: String?) {
        photoPath = path
    }



    override fun setActive(value: String?) {
        value?.let {
            shift = it
        }
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

        newdata = Store()
        newdata?.shift = shift


        val taxString = pajak
        val serviceString = service
        interactor.callUpdateAPI(context,restModel,data?.id_store!!,name,email,phone,address,taxString,serviceString,currency,footer,shift!!,photoPath)
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

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessUpdate(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
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

    override fun onSuccessGetStore(list: List<Store>?) {
        if(list == null){
            view.showMessage(999,"Store not found")
        }

        list?.let {
            if(it.isEmpty()){
                view.showMessage(999,"Store not found")
            }

            data = it[0]
        }

    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    private fun checkStore(){
        data?.let {
            view.loadPhoto(it.img!!)
            view.setStoreName(it.name_store!!)
            view.setNohp(it.nohp!!)
            view.setAdress(it.address!!)
            view.setEmail(it.email!!)
            view.setTaxt(it.tax!!)
            view.setSc(it.service_charge!!)
            view.setCurrency(it.currency!!)
            view.setFooter(it.footer!!)
            view.setShift(it.shift!!)
        }
    }

}