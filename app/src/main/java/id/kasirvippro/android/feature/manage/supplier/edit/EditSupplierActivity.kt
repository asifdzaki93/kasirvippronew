package id.kasirvippro.android.feature.manage.supplier.edit

import android.os.Bundle
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_edit_supplier.*
import id.kasirvippro.android.rest.entity.RestException



class EditSupplierActivity : BaseActivity<EditSupplierPresenter, EditSupplierContract.View>(),
    EditSupplierContract.View {

    override fun createPresenter(): EditSupplierPresenter {
        return EditSupplierPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_supplier
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
            val email = et_mail.text.toString().trim()
            val phone = et_phone.text.toString().trim()
            val address = et_address.text.toString().trim()
            getPresenter()?.onCheck(name,email,phone,address)
        }

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_edit_supplier)
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
    override fun setSupplier(
        name: String?,
        email: String?,
        phone: String?,
        address: String?
    )
    {
        name?.let {
            et_name.setText(it)
        }

        email?.let {
            et_mail.setText(it)
        }

        phone?.let {
            et_phone.setText(it)
        }

        address?.let {
            et_address.setText(it)
        }

    }






}