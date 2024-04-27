package id.kasirvippro.android.feature.report.slip.chooseMonth

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.slip.Absent
import id.kasirvippro.android.models.slip.Slip
import id.kasirvippro.android.models.slip.SlipRestModel
import id.kasirvippro.android.models.staff.Staff

interface MonthContract {

    interface View : BaseViewImpl {
        fun showMessage(code: Int, msg: String?)
        fun setYear(year:String)
        fun setList(list:List<FilterDialogDate>,selected:FilterDialogDate)
        fun openSlipPage(date:FilterDialogDate,slip:Slip)
        fun openAbsentPage(date:FilterDialogDate,list:ArrayList<Absent>,data:Staff?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun getSelectedDate(): FilterDialogDate?
        fun setSelectedDate(date:FilterDialogDate?)
        fun getDates(year:Int):List<FilterDialogDate>
        fun onNextYear()
        fun onPrevYear()
        fun setDate()
        fun onCheck()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetSlipAPI(context: Context,restModel:SlipRestModel,id:String?,date:FilterDialogDate)
        fun callGetAbsenAPI(context: Context,restModel:SlipRestModel,id:String?,date:FilterDialogDate)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessPaySlip(list: List<Slip>?)
        fun onSuccessAbsent(list: List<Absent>?)
        fun onFailedAPI(code:Int,msg:String)
    }


}