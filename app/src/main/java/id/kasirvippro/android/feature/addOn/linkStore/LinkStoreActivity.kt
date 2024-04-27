package id.kasirvippro.android.feature.addOn.linkStore

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_afiliate.*


class LinkStoreActivity : BaseActivity<LinkStorePresenter, LinkStoreContract.View>(), LinkStoreContract.View {

    override fun createPresenter(): LinkStorePresenter {
        return LinkStorePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_afiliate
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
        btn_copy2.setOnClickListener {
            getPresenter()?.onCopy2()
        }

        btn_share2.setOnClickListener {
            getPresenter()?.onShare2()
        }
        btn_copy3.setOnClickListener {
            getPresenter()?.onCopy3()
        }
        btn_share3.setOnClickListener {
            getPresenter()?.onShare3()
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_link_tracking_title)
            elevation = 0f
            setBackgroundDrawable(ContextCompat.getDrawable(this@LinkStoreActivity,R.drawable.gradient_blue))
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
        user.subdomain?.let {
            tv_code.text = it
        }
        tv_code2.text = ""
        user.signup?.let {
            tv_code2.text = it
        }
        tv_code3.text = ""
        user.screen?.let {
            tv_code3.text = it
        }
    }

    override fun openShare(code: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT, "Want cheap and easy shopping? open $code to get the best product")
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "Send to"))
    }


    override fun openShare2(code: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT, "For an easier administrative process, please register at the link: $code For new patients")
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "Send to"))
    }


    override fun openShare3(code: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT, "Order screen link: $code")
        sendIntent.type = "text/plain"
        startActivity(Intent.createChooser(sendIntent, "Send to"))
    }

    override fun loadProfile() {
        getPresenter()?.loadProfile()
    }

    override fun onMasterPage(isPremium: Boolean) {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store") {
            ll_link_order_screen.visibility = View.GONE
            ll_link_signup.visibility = View.GONE
        }else if(typestore=="Service products") {
            ll_link_order_screen.visibility = View.VISIBLE
            ll_link_signup.visibility = View.GONE
        }else if(typestore=="Healthcare") {
            ll_link_order_screen.visibility = View.VISIBLE
            ll_link_signup.visibility = View.VISIBLE
        }else{
            ll_link_order_screen.visibility = View.VISIBLE
            ll_link_signup.visibility = View.GONE
        }

    }

    override fun onAdminPage() {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store") {
            ll_link_order_screen.visibility = View.GONE
            ll_link_signup.visibility = View.GONE
        }else if(typestore=="Service products") {
            ll_link_order_screen.visibility = View.VISIBLE
            ll_link_signup.visibility = View.GONE
        }else if(typestore=="Healthcare") {
            ll_link_order_screen.visibility = View.VISIBLE
            ll_link_signup.visibility = View.VISIBLE
        }else{
            ll_link_order_screen.visibility = View.VISIBLE
            ll_link_signup.visibility = View.GONE
        }
    }

    override fun onSalesPage() {
        val typestore = AppConstant.TYPESTORE.getTypeData()
        if(typestore=="General store") {
            ll_link_order_screen.visibility = View.GONE
            ll_link_signup.visibility = View.GONE
        }else if(typestore=="Service products") {
            ll_link_order_screen.visibility = View.VISIBLE
            ll_link_signup.visibility = View.GONE
        }else if(typestore=="Healthcare") {
            ll_link_order_screen.visibility = View.VISIBLE
            ll_link_signup.visibility = View.VISIBLE
        }else{
            ll_link_order_screen.visibility = View.VISIBLE
            ll_link_signup.visibility = View.GONE
        }

    }


    override fun setProfile(subdomain: String?,signup: String?,screen: String?) {

        tv_code.text = ""
        subdomain?.let {
            tv_code.text = it
        }
        tv_code2.text = ""
        signup?.let {
            tv_code2.text = it
        }
        tv_code3.text = ""
        screen?.let {
            tv_code3.text = it
        }

    }



}
