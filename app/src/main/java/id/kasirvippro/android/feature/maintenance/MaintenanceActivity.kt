package id.kasirvippro.android.feature.maintenance

import android.os.Bundle
import id.kasirvippro.android.MyApplication
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import kotlinx.android.synthetic.main.activity_maintenance.*


class MaintenanceActivity : BaseActivity<MaintenencePresenter, MaintenanceContract.View>(), MaintenanceContract.View {

    override fun createPresenter(): MaintenencePresenter {
        return MaintenencePresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_maintenance
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        btn_end.setOnClickListener {
            onClose()
        }
        getPresenter()?.onViewCreated()
    }

    override fun onClose() {
        MyApplication.exit(this)
    }

    override fun onBackPressed() {
        onClose()
    }


}
