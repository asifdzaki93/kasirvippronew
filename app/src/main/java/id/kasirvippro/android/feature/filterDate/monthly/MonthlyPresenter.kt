package id.kasirvippro.android.feature.filterDate.monthly

import android.content.Context
import android.content.Intent
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.utils.AppConstant
import org.threeten.bp.LocalDate

class MonthlyPresenter(val context: Context, val view: MonthlyContract.View) : BasePresenter<MonthlyContract.View>(),
    MonthlyContract.Presenter, MonthlyContract.InteractorOutput {

    private var interactor = MonthlyInteractor(this)
    private var selected:FilterDialogDate? = null
    private val today = LocalDate.now()
    private val maxYear = today.year
    private var selectedYear = maxYear


    override fun onViewCreated(intent: Intent) {
        selected = intent.getParcelableExtra(AppConstant.DATA) as FilterDialogDate??
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

    override fun getDates(year:Int): List<FilterDialogDate> {
        val list = ArrayList<FilterDialogDate>()
        for(i in 1..12){
            val model = generateModel(year,i)
            list.add(model)
        }
        return list
    }

    override fun setDate() {
        view.setYear(selectedYear.toString())
        view.setList(getDates(selectedYear),selected!!)
    }

    override fun onNextYear() {
        if(selectedYear < maxYear){
            selectedYear++
            setDate()
        }
    }

    override fun onPrevYear() {
        selectedYear--
        setDate()
    }

    private fun generateModel(year:Int,month:Int):FilterDialogDate{
        val first = CalendarDay.from(year,month,1)
        val max = first.date.lengthOfMonth()
        val last = CalendarDay.from(year,month,max)

        val model = FilterDialogDate()
        model.id = AppConstant.FilterDate.MONTHLY
        model.firstDate = first
        model.lastDate = last

        return model
    }



}