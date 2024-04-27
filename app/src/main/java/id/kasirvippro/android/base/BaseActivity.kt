package id.kasirvippro.android.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import id.kasirvippro.android.R
import id.kasirvippro.android.feature.login.LoginActivity
import id.kasirvippro.android.feature.main.MainActivity
import id.kasirvippro.android.feature.maintenance.MaintenanceActivity
import id.kasirvippro.android.feature.update.UpdateActivity
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper

abstract class BaseActivity<P : BasePresenter<V>, V : BaseViewImpl> : AppCompatActivity() {

    private lateinit var presenter: P
    private lateinit var progressDialog: AlertDialog

    fun setPresenter() {
        presenter = createPresenter()
    }

    fun getPresenter(): P? {
        return presenter
    }

    abstract fun createPresenter(): P

    abstract fun createLayout(): Int

    abstract fun startingUpActivity(savedInstanceState: Bundle?)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content view, create layout implementation
        setContentView(createLayout())
        setupProgressDialog()
        if (this is BaseViewImpl) {
            setPresenter()
            if (getPresenter() != null) {
                getPresenter()?.attachView(this as V)
            }
        }

        // init action
        startingUpActivity(savedInstanceState)
    }

    override fun onDestroy() {
        hideLoadingDialog()
        super.onDestroy()
        if (getPresenter() != null) {
            getPresenter()?.detachView()
        }
    }

    private fun setupProgressDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.layout_progress_dialog)
        progressDialog = builder.create()
        progressDialog.setCancelable(false)
    }

    fun showLoadingDialog() {
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }
    }

    fun hideLoadingDialog() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    fun showToast(message: String) {
        toast(this, message)
    }

    fun showToast(resInt: Int) {
        showToast(getString(resInt))
    }

    fun hideKeyboard() {
        Helper.hideKeyboard(this)
    }

    fun restartMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun restartLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun restartMainActivity(menu:Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(AppConstant.DATA,menu)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun restartMainActivity(menu:Int,position:Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(AppConstant.DATA,menu)
        intent.putExtra(AppConstant.POSITION,position)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun openMaintenanceActivity() {
        val intent = Intent(this, MaintenanceActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    fun openUpdateActivity() {
        val intent = Intent(this, UpdateActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }


}