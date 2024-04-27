package id.kasirvippro.android.feature.setting.password

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import kotlinx.android.synthetic.main.activity_change_password.*

class PasswordActivity : BaseActivity<PasswordPresenter, PasswordContract.View>(), PasswordContract.View {

    override fun createPresenter(): PasswordPresenter {
        return PasswordPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_change_password
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){
        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val lama = et_password.text.toString().trim()
            val baru = et_new_password.text.toString().trim()
            val konfirmasi = et_confirm_password.text.toString().trim()
            getPresenter()?.onCheck(lama,baru,konfirmasi)
        }

        btn_password.setOnCheckedChangeListener { view, isChecked ->
            if(isChecked){
                et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else{
                et_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        btn_new_password.setOnCheckedChangeListener { view, isChecked ->
            if(isChecked){
                et_new_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else{
                et_new_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        btn_confirm_password.setOnCheckedChangeListener { view, isChecked ->
            if(isChecked){
                et_confirm_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }
            else{
                et_confirm_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.change_password)
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


}
