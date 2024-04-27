package id.kasirvippro.android.feature.hotNews.view

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.activity_view.*


class ViewNewsActivity : BaseActivity<ViewNewsPresenter, ViewNewsContract.View>(), ViewNewsContract.View {

    override fun createPresenter(): ViewNewsPresenter {
        return ViewNewsPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_view
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){

    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Detail"
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

    override fun setNews(title: String?, detail: String?, img: String?)
    {
        title?.let {
            et_name.setText(it)
        }

        detail?.let {
            val et_detail = findViewById<TextView>(R.id.et_detail)
            et_detail.setText(HtmlCompat.fromHtml(it, 0))
            et_detail.setMovementMethod(LinkMovementMethod.getInstance());
        }

        img?.let {
            GlideApp.with(iv_photo.context)
                .load(img)
                .error(R.drawable.ic_noimage)
                .placeholder(R.drawable.ic_noimage)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(iv_photo)
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
