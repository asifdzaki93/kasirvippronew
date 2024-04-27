package id.kasirvippro.android.feature.filterDate.daily

import android.content.Context
import android.content.Intent
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import org.threeten.bp.LocalDate

class DailyPresenter(val context: Context, val view: DailyContract.View) : BasePresenter<DailyContract.View>(),
    DailyContract.Presenter, DailyContract.InteractorOutput {

    private var interactor = DailyInteractor(this)
    private var selected:FilterDialogDate? = null

    override fun onViewCreated(intent: Intent) {
        val today = LocalDate.now()
        val nextday = today?.plusDays(360)
        view.setMaxdate(nextday)
        selected = intent.getParcelableExtra(AppConstant.DATA) as FilterDialogDate?
        if(selected == null){
            selected = FilterDialogDate()
            selected?.id = AppConstant.FilterDate.DAILY
        }
        else{
            if(AppConstant.FilterDate.DAILY == selected?.id){
                selected?.firstDate?.let {
                    view.setFirstDateText(Helper.getDateFormat(context, it.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy"))
                }
                selected?.lastDate?.let {
                    view.setLastDateText(Helper.getDateFormat(context, it.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy"))
                }

                view.setRange(selected?.firstDate,selected?.lastDate)
            }
            else{
                selected = FilterDialogDate()
                selected?.id = AppConstant.FilterDate.DAILY
            }

        }

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun getSelectedData(): FilterDialogDate? {
        return selected
    }

    override fun setFirstDate(data: CalendarDay?) {
        selected?.firstDate = data
        view.setFirstDateText("")
        selected?.firstDate?.let {
            view.setFirstDateText(Helper.getDateFormat(context, it.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy"))
        }
    }

    override fun setLastDate(data: CalendarDay?) {
        selected?.lastDate = data
        view.setLastDateText("")
        selected?.lastDate?.let {
            view.setLastDateText(Helper.getDateFormat(context, it.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy"))
        }
    }

}