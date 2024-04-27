package id.kasirvippro.android.feature.filterDate.monthly

import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.FilterDialogDate

interface MonthlyContract {

    interface View : BaseViewImpl {
        fun setYear(year:String)
        fun setList(list:List<FilterDialogDate>,selected:FilterDialogDate)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun getSelectedData(): FilterDialogDate?
        fun getDates(year:Int):List<FilterDialogDate>
        fun onNextYear()
        fun onPrevYear()
        fun setDate()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
    }

    interface InteractorOutput : BaseInteractorOutputImpl {

    }


}