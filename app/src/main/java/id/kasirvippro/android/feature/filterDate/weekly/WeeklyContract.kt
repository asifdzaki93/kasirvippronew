package id.kasirvippro.android.feature.filterDate.weekly

import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.FilterDialogDate
import org.threeten.bp.LocalDate

interface WeeklyContract {

    interface View : BaseViewImpl {
        fun setMonthYear(date:String)
        fun setList(list:List<FilterDialogDate>,selected:FilterDialogDate)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun getSelectedData(): FilterDialogDate?
        fun getDates(date:LocalDate):List<FilterDialogDate>
        fun onNextMonth()
        fun onPrevMonth()
        fun setDate()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
    }

    interface InteractorOutput : BaseInteractorOutputImpl {

    }


}