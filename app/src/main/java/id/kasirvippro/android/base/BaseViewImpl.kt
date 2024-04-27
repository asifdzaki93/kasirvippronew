package id.kasirvippro.android.base

import androidx.annotation.StringRes

interface BaseViewImpl {
    fun showLoadingDialog()
    fun hideLoadingDialog()
    fun showToast(message: String)
    fun showToast(@StringRes resInt: Int)
    fun hideKeyboard()
    fun restartMainActivity()
    fun restartLoginActivity()
    fun restartMainActivity(menu:Int)
    fun restartMainActivity(menu:Int,position:Int)
    fun openMaintenanceActivity()
    fun openUpdateActivity()
}