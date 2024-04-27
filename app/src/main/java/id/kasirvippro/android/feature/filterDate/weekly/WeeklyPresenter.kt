package id.kasirvippro.android.feature.filterDate.weekly

import android.content.Context
import android.content.Intent
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import org.threeten.bp.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class WeeklyPresenter(val context: Context, val view: WeeklyContract.View) : BasePresenter<WeeklyContract.View>(),
    WeeklyContract.Presenter, WeeklyContract.InteractorOutput {

    private var interactor = WeeklyInteractor(this)
    private var selected:FilterDialogDate? = null
    private val today = LocalDate.now()
    private val maxDate = today
    private var selectedDate = today


    override fun onViewCreated(intent: Intent) {
        selected = intent.getParcelableExtra(AppConstant.DATA) as FilterDialogDate?
        if(selected == null){
            selected = FilterDialogDate()
            selected?.id = AppConstant.FilterDate.MONTHLY
        }
        else{
            if(AppConstant.FilterDate.MONTHLY != selected?.id){
                selected = FilterDialogDate()
                selected?.id = AppConstant.FilterDate.MONTHLY
            }
        }
        setDate()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun getSelectedData(): FilterDialogDate? {
        return selected
    }

    override fun getDates(date:LocalDate): List<FilterDialogDate> {
        val list = ArrayList<FilterDialogDate>()
        for(i in 0..3){
            val model = generateModel(date,i)
            list.add(model)
        }
        return list
    }

    override fun setDate() {
        view.setMonthYear(Helper.getDateFormat(context, selectedDate.toString(), "yyyy-MM-dd", "MMMM yyyy"))
        view.setList(getDates(selectedDate),selected!!)
    }

    override fun onNextMonth() {
        val maxYear = maxDate.year
        val maxMonth = maxDate.monthValue
        val selYear = selectedDate.year
        val selMonth = selectedDate.monthValue

        val cal = Calendar.getInstance()
        cal.set(selYear, selMonth, selectedDate.dayOfMonth)
        cal.add(Calendar.MONTH, +1)
        val month = cal.get(Calendar.MONTH)

        if(selYear < maxYear){
            selectedDate = CalendarDay.from(selectedDate.year,month,selectedDate.dayOfMonth).date
            setDate()
        }
        else{
            if(selMonth < maxMonth){
                selectedDate = CalendarDay.from(selectedDate.year,month,selectedDate.dayOfMonth).date
                setDate()
            }
        }

    }

    override fun onPrevMonth() {
        val cal = Calendar.getInstance()
        cal.set(selectedDate.year, selectedDate.monthValue, selectedDate.dayOfMonth)
        cal.add(Calendar.MONTH, -1)
        val month = cal.get(Calendar.MONTH)
        selectedDate = CalendarDay.from(selectedDate.year,month,selectedDate.dayOfMonth).date
        setDate()
    }

    private fun generateModel(date:LocalDate,count:Int):FilterDialogDate{
        val max = date.lengthOfMonth()
        val year = date.year
        val month = date.monthValue
        val first = CalendarDay.from(year,month,(count*7)+1)
        val model = FilterDialogDate()
        model.id = AppConstant.FilterDate.MONTHLY
        model.firstDate = first
        var last = CalendarDay.from(year,month,(count*7)+7)
        if(count == 3){
            last = CalendarDay.from(year,month,max)
        }
        model.lastDate = last

        return model
    }



}