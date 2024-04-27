package id.kasirvippro.android.feature.maintenance

import android.content.Context
import id.kasirvippro.android.base.BasePresenter

class MaintenencePresenter(val context: Context, val view: MaintenanceContract.View) : BasePresenter<MaintenanceContract.View>(),
    MaintenanceContract.Presenter, MaintenanceContract.InteractorOutput {

    override fun onViewCreated() {

    }
}