package id.kasirvippro.android.feature.afiliate.detailAfiliate

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import kotlinx.android.synthetic.main.activity_afiliate_link.*


class DetailAfiliateActivity : BaseActivity<DetailAfiliatePresenter, DetailAfiliateContract.View>(), DetailAfiliateContract.View {

    override fun createPresenter(): DetailAfiliatePresenter {
        return DetailAfiliatePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_afiliate_link
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){
        btn_copy.setOnClickListener {
            getPresenter()?.onCopy()
        }

        btn_share.setOnClickListener {
            getPresenter()?.onShare()
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Code Refferal"
            elevation = 0f
            setBackgroundDrawable(ContextCompat.getDrawable(this@DetailAfiliateActivity,R.drawable.gradient_blue))
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun setInfo(user: User) {
        tv_code.text = ""
        user.afiliasi?.let {
            tv_code.text = it
        }
    }

    override fun openShare(code: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT, "Have a shop/business? Let's install this application, so that your store is more modern, enter the referral code: $code at registration")
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "Send to"))
    }



}
