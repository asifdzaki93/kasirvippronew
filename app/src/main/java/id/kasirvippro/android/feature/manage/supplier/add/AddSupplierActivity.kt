package id.kasirvippro.android.feature.manage.supplier.add

import android.os.Bundle
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_add_supplier.*
import id.kasirvippro.android.rest.entity.RestException



class AddSupplierActivity : BaseActivity<AddSupplierPresenter, AddSupplierContract.View>(),
    AddSupplierContract.View {


    override fun createPresenter(): AddSupplierPresenter {
        return AddSupplierPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_add_supplier
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
            val email = et_mail.text.toString().trim()
            val telpon = et_phone.text.toString().trim()
            val alamat = et_address.text.toString().trim()
            getPresenter()?.onCheck(name,email,telpon,alamat)
        }



    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_add_supplier)
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

    public fun hideLoading(){
        hideLoadingDialog()
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


    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }




}
