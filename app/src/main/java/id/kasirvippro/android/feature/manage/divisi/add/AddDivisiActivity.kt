package id.kasirvippro.android.feature.manage.divisi.add

import android.os.Bundle
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import kotlinx.android.synthetic.main.activity_add_divisi.btn_save
import kotlinx.android.synthetic.main.activity_add_divisi.et_name

class AddDivisiActivity : BaseActivity<AddDivisiPresenter, AddDivisiContract.View>(), AddDivisiContract.View {

    override fun createPresenter(): AddDivisiPresenter {
        return AddDivisiPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_add_divisi
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    public fun hideLoading(){
        hideLoadingDialog()
    }


    private fun renderView(){
        btn_save.setOnClickListener {
            hideKeyboard()
            showLoadingDialog()
            val name_divisi = et_name.text.toString().trim()
            getPresenter()?.onCheck(name_divisi)
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_name_divisi)
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
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


}
