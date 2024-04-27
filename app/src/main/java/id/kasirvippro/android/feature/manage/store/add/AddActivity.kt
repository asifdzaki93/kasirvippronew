package id.kasirvippro.android.feature.manage.store.add

import android.os.Bundle
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.BottomDialog
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import kotlinx.android.synthetic.main.activity_add_store.*


class AddActivity : BaseActivity<AddPresenter, AddContract.View>(),
    AddContract.View, BottomDialog.Listener {

    private val currencyDialog = BottomDialog.newInstance()

    override fun createPresenter(): AddPresenter {
        return AddPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_add_store
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){

        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val name = et_name.text.toString().trim()
            val email = et_email.text.toString().trim()
            val phone = et_phone.text.toString().trim()
            val address = et_address.text.toString().trim()
            val tax = et_tax.text.toString().trim()
            val service = et_service.text.toString().trim()
            val currency = et_currency.text.toString().trim()
            val footer = et_footer.text.toString().trim()
            getPresenter()?.onCheck(name,email,phone,address,tax,service,currency,footer)
        }

        et_currency.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.onCheckCurrency()
        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Add Branch"
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
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

    override fun setCurrencyName(name: String) {
        et_currency.text = name
    }



}
