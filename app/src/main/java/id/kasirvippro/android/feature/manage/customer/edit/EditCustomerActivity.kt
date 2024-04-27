package id.kasirvippro.android.feature.manage.customer.edit

import android.os.Bundle
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_edit_customer.*
import id.kasirvippro.android.rest.entity.RestException


class EditCustomerActivity : BaseActivity<EditCustomerPresenter, EditCustomerContract.View>(),
    EditCustomerContract.View {

    override fun createPresenter(): EditCustomerPresenter {
        return EditCustomerPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_customer
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){

        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val name = et_name.text.toString().trim()
            val email = et_email.text.toString().trim()
            val phone = et_phone.text.toString().trim()
            val address = et_address.text.toString().trim()
            val customercode = et_customercode.text.toString().trim()
            getPresenter()?.onCheck(name,email,phone, address, customercode)
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_edit_customer)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }
    override fun setCustomer(name: String?, email: String?, phone: String?, address: String?, customercode: String?)
    {
        name?.let {
            et_name.setText(it)
        }

        email?.let {
            et_email.setText(it)
        }

        phone?.let {
            et_phone.setText(it)
        }

        address?.let {
            et_address.setText(it)
        }

        customercode?.let {
            et_customercode.setText(it)
        }


    }
}
