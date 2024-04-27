package id.kasirvippro.android.feature.filterDate.main

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.utils.AppConstant

class MainPresenter(val context: Context, val view: MainContract.View) : BasePresenter<MainContract.View>(),
    MainContract.Presenter, MainContract.InteractorOutput {

    private var interactor = MainInteractor(this)
    private var selected:FilterDialogDate? = null
    private var menu = AppConstant.FilterDate.DAILY

    override fun onViewCreated(intent: Intent) {
        selected = intent.getParcelableExtra(AppConstant.DATA) as FilterDialogDate??

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun setSelectedData(data: FilterDialogDate?) {
        selected = data
    }

    override fun getSelectedData(): FilterDialogDate? {
        return selected
    }

    override fun setSelectedMenu(data: Int) {
        menu = data
    }

    override fun onCheckDaily() {
        if(menu != AppConstant.FilterDate.DAILY){
            view.showDaily()
        }
    }

    override fun onCheckWeekly() {
        if(menu != AppConstant.FilterDate.WEEKLY){
            view.showWeekly()
        }
    }

    override fun onCheckMonthly() {
        if(menu != AppConstant.FilterDate.MONTHLY){
            view.showMonthly()
        }
    }

}