package id.kasirvippro.android.feature.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.choose.decimal.ChooseDecimalActivity
import id.kasirvippro.android.feature.choose.typestore.ChooseTypestoreActivity
import id.kasirvippro.android.feature.dialog.BottomDialog
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.currency.Decimal
import id.kasirvippro.android.models.typestore.TypeStore
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : BaseActivity<RegisterPresenter, RegisterContract.View>(), RegisterContract.View, BottomDialog.Listener {
    private val currencyDialog = BottomDialog.newInstance()
    private val decimalDialog = BottomDialog.newInstance()

    private val CODE_OPEN_CHOOSE_TYPE = 1005
    private val CODE_OPEN_CHOOSE_DECIMAL = 1006
    override fun createPresenter(): RegisterPresenter {
        return RegisterPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_register
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){
        btn_login.setOnClickListener {
            finish()
        }

        btn_register.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val store = et_store.text.toString().trim()
            val currency = et_currency.text.toString().trim()
            val name = et_name.text.toString().trim()
            val email = et_mail.text.toString().trim()
            val phone = et_phone.text.toString().trim()
            val address = et_address.text.toString().trim()
            val password = et_password.text.toString().trim()
            val referal = et_referal.text.toString().trim()
            val typestore = et_typestore.text.toString().trim()
            val decimal = "No Decimal"
            getPresenter()?.onCheck(store,currency,name,email,phone,password,address,referal,typestore,decimal)
        }

        btn_password.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else{
                et_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        et_currency.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.onCheckCurrency()
        }

        et_typestore.setOnClickListener {
            openChooseTypestore()
        }

        //et_decimal.setOnClickListener {
        //    openChooseDecimal()
        //}

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) finish()
        return super.onOptionsItemSelected(item!!)
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            title = "Signup"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }


    override fun showMessage(code: Int, msg: String?) {
        hideLoadingDialog()
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                msg?.let {
                    toast(this,it)}
            }

        }

    }

    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_OPEN_CHOOSE_TYPE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val typestore = data.getSerializableExtra(AppConstant.DATA) as TypeStore
            if (typestore.id_typestore == null) {
                toast(this,"Data not found")
            } else {
                getPresenter()?.updateTypestore(typestore)
            }
        }
        else if (requestCode == CODE_OPEN_CHOOSE_DECIMAL && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                toast(this,"Data not found")
                return
            }
            if (data.getSerializableExtra(AppConstant.DATA) == null) {
                toast(this,"Data not found")
                return
            }
            val decimal = data.getSerializableExtra(AppConstant.DATA) as Decimal
            if (decimal.code_decimal == null) {
                toast(this,"Data not found")
            } else {
                getPresenter()?.updateDecimal(decimal)
            }
        }
    }

    override fun setTypestoreName(data: TypeStore?) {
        et_typestore.text = ""
        data?.let {
            et_typestore.text = it.name
        }
    }

    override fun setTDecimalName(data: Decimal?) {
        //et_decimal.text = ""
       // data?.let {
        //    et_decimal.text = it.code_decimal
       // }
    }

    override fun onItemClicked(data: DialogModel, type: Int) {
        getPresenter()?.setSelectedCurrency(data)
    }

    override fun openCurrencies(title: String, list: List<DialogModel>, selected: DialogModel?) {
        hideLoadingDialog()
        if (currencyDialog.dialog != null && currencyDialog.dialog!!.isShowing) {
        } else {
            currencyDialog.setData(title,1, list,selected)
            currencyDialog.show(supportFragmentManager, "currency")
        }
    }

    override fun openDecimal(title: String, list: List<DialogModel>, selected: DialogModel?) {
        hideLoadingDialog()
        if (decimalDialog.dialog != null && decimalDialog.dialog!!.isShowing) {
        } else {
            decimalDialog.setData(title,1, list,selected)
            decimalDialog.show(supportFragmentManager, "format_currency")
        }
    }

    override fun setCurrencyName(name: String) {
        et_currency.text = name
    }

    override fun openChooseTypestore() {
        val intent = Intent(this, ChooseTypestoreActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_TYPE)
    }

    override fun openChooseDecimal() {
        val intent = Intent(this, ChooseDecimalActivity::class.java)
        startActivityForResult(intent, CODE_OPEN_CHOOSE_DECIMAL)
    }




}
