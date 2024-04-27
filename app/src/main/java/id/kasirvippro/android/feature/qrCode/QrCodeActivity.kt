package id.kasirvippro.android.feature.qrCode

import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_qrcode.*
import kotlinx.android.synthetic.main.activity_qrcode.btn_share
import kotlinx.android.synthetic.main.activity_qrcode.tv_code

class QrCodeActivity : BaseActivity<QrCodePresenter, QrCodeContract.View>(), QrCodeContract.View {

    private var qrImage : Bitmap? = null

    override fun createPresenter(): QrCodePresenter {
        return QrCodePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_qrcode
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){

        btn_share.setOnClickListener {
            getPresenter()?.onCheckShare()
        }

        btn_saves.setOnClickListener {
            getPresenter()?.onCheckDownload()
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
    }

    override fun getParentLayout(): NestedScrollView {
        return ns_scroll
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

    override fun loadProfile() {
        getPresenter()?.loadProfile()
    }


    override fun setProfile(subdomain: String?) {

        tv_code.text = ""
        subdomain?.let {
            tv_code.text = it
        }

        qrImage = net.glxn.qrgen.android.QRCode.from(subdomain).withColor(0xFF1C78C1.toInt(),
            0x00FFFFAA.toInt()
        ).withSize(600, 600).bitmap()

        if(qrImage != null)
        {
            imageView_qrCode.setImageBitmap(qrImage)
        }
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_qrcode)
            elevation = 0f
            setBackgroundDrawable(ContextCompat.getDrawable(this@QrCodeActivity,R.drawable.gradient_blue))
        }

    }
}