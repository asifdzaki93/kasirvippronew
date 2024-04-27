package id.kasirvippro.android.feature.filterDate.main

import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.FilterDialogDate

interface MainContract {

    interface View : BaseViewImpl {
        fun onSelected(data:FilterDialogDate?)
        fun showDaily()
        fun showWeekly()
        fun showMonthly()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun setSelectedData(data:FilterDialogDate?)
        fun getSelectedData():FilterDialogDate?
        fun setSelectedMenu(data:Int)
        fun onCheckDaily()
        fun onCheckWeekly()
        fun onCheckMonthly()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()

    }

    interface InteractorOutput : BaseInteractorOutputImpl


}