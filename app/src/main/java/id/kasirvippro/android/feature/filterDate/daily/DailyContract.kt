package id.kasirvippro.android.feature.filterDate.daily

import android.content.Intent
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.FilterDialogDate
import org.threeten.bp.LocalDate

interface DailyContract {

    interface View : BaseViewImpl {
        fun setMaxdate(data:LocalDate?)
        fun setFirstDateText(text:String?)
        fun setLastDateText(text:String?)
        fun setRange(first:CalendarDay?,last:CalendarDay?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun getSelectedData(): FilterDialogDate?
        fun setFirstDate(data:CalendarDay?)
        fun setLastDate(data:CalendarDay?)
        fun onDestroy()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
    }

    interface InteractorOutput : BaseInteractorOutputImpl {

    }


}