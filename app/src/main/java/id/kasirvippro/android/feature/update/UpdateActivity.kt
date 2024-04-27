package id.kasirvippro.android.feature.update

import android.os.Bundle
import id.kasirvippro.android.MyApplication
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import kotlinx.android.synthetic.main.activity_update.*
import android.content.Intent
import android.net.Uri


class UpdateActivity : BaseActivity<UpdatePresenter, UpdateContract.View>(), UpdateContract.View {

    override fun createPresenter(): UpdatePresenter {
        return UpdatePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_update
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        btn_end.setOnClickListener {
            onClose()
        }
        btn_update.setOnClickListener {
            openPlaystore()
        }
        getPresenter()?.onViewCreated()
    }

    override fun onClose() {
        MyApplication.exit(this)
    }

    override fun onBackPressed() {
        onClose()
    }

    override fun openPlaystore() {
        val appPackageName = packageName // getPackageName() from Context or Activity object
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (ex: android.content.ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }

    }


}
