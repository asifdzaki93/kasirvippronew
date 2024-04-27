package id.kasirvippro.android.feature.manage.table.edit

import android.os.Bundle
import android.view.MenuItem
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_edit_table.*
import id.kasirvippro.android.rest.entity.RestException



class EditTableActivity : BaseActivity<EditTablePresenter, EditTableContract.View>(), EditTableContract.View {

    override fun createPresenter(): EditTablePresenter {
        return EditTablePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_edit_table
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
            getPresenter()?.onCheck(name)
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_edit_table)
            elevation = 0f

        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun showMessage(code:Int, msg:String?) {
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

    override fun setTableName(name: String?) {
        name?.let {
            et_name.setText(it)
        }
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
