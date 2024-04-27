package id.kasirvippro.android.feature.filterDate.daily

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.base.BaseFragment
import id.kasirvippro.android.R
import id.kasirvippro.android.models.FilterDialogDate
import kotlinx.android.synthetic.main.fragment_filter_daily.view.*
import org.threeten.bp.LocalDate


class DailyFragment : BaseFragment<DailyPresenter, DailyContract.View>(),
    DailyContract.View {

    private lateinit var _view: View
    private var listener:Listener?=null

    companion object {

        @JvmStatic
        fun newInstance() =
                DailyFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun createPresenter(): DailyPresenter {
        return DailyPresenter(activity as Context, this)
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_filter_daily, container, false)
    }


    override fun initAction(view: View) {
        _view = view
        renderView()
        getPresenter()?.onViewCreated(activity!!.intent)
    }

    private fun renderView(){

        _view.calendarView.setOnRangeSelectedListener { _, dates ->
            if(dates.isEmpty()){
                getPresenter()?.setFirstDate(null)
                getPresenter()?.setLastDate(null)
                return@setOnRangeSelectedListener
            }
            val size = dates.size
            if(size > 1){
                getPresenter()?.setFirstDate(dates[0])
                getPresenter()?.setLastDate(dates[size-1])
            }
            else{
                getPresenter()?.setFirstDate(dates[0])
                getPresenter()?.setLastDate(dates[0])
            }

        }

        _view.calendarView.setOnDateChangedListener { _, date, selected ->
            if(!selected)
            {
                _view.calendarView.selectedDate = date
            }

            when (val size = _view.calendarView.selectedDates.size) {
                0 -> {
                    getPresenter()?.setFirstDate(null)
                    getPresenter()?.setLastDate(null)
                }
                1 -> {
                    getPresenter()?.setFirstDate(date)
                    getPresenter()?.setLastDate(date)
                }
                else -> {
                    getPresenter()?.setFirstDate(_view.calendarView.selectedDates[0])
                    getPresenter()?.setLastDate(_view.calendarView.selectedDates[size-1])
                }
            }
        }

        _view.btn_save.setOnClickListener {
            listener?.onSelected(getPresenter()?.getSelectedData())
        }
    }

    override fun setMaxdate(data: LocalDate?) {
        val edit = _view.calendarView.state().edit()
        data?.let {
            edit.setMaximumDate(it)
        }
        edit.commit()
    }

    override fun setFirstDateText(text: String?) {
        text?.let {
            _view.tv_awal.text = it
        }
    }

    override fun setLastDateText(text: String?) {
        text?.let {
            _view.tv_akhir.text = it
        }
    }

    override fun setRange(first: CalendarDay?, last: CalendarDay?) {
        if(first != null && last != null){
            _view.calendarView.selectRange(first,last)
        }
    }

    interface Listener {
        fun onSelected(data:FilterDialogDate?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement Listener")
        }
    }
}
