package id.kasirvippro.android.feature.dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.fragment_filter_date_dialog.*
import org.threeten.bp.LocalDate

class RangeDateDialog : BottomSheetDialogFragment() {
    private var mListener: Listener? = null
    private var firstDate: CalendarDay ?= null
    private var lastDate: CalendarDay ?= null
    private var minDate: LocalDate?= null
    private var maxDate: LocalDate?= null
    private var type:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filter_date_dialog, container, false)
    }

    fun setListener(listener: Listener){
        mListener = listener
    }

    fun setType(tag: Int){
        this.type = tag
    }

    fun setData(minDate:LocalDate?,maxDate:LocalDate?,firstDate: CalendarDay?,lastDate: CalendarDay?){
        this.minDate = minDate
        this.maxDate = maxDate
        this.firstDate = firstDate
        this.lastDate = lastDate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (context == null) return

        if(minDate != null || maxDate != null){
            val edit = calendarView.state().edit()
            minDate?.let {
                edit.setMinimumDate(it)
            }
            maxDate?.let {
                edit.setMaximumDate(it)
            }
            edit.commit()
        }

        if(firstDate != null && lastDate != null){
            calendarView.selectRange(firstDate,lastDate)
            tv_awal.text = Helper.getDateFormat(activity!!, firstDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
            tv_akhir.text = Helper.getDateFormat(activity!!, lastDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
        }

        calendarView.setOnRangeSelectedListener { widget, dates ->
            if(dates.isEmpty()){
                firstDate = null
                tv_awal.text = ""
                lastDate = null
                tv_akhir.text = ""
                return@setOnRangeSelectedListener
            }
            Log.d("setOnRangeSelected",Gson().toJson(dates))
            val size = dates.size
            if(size > 1){
                firstDate = dates[0]
                tv_awal.text = Helper.getDateFormat(activity!!, firstDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
                lastDate = dates[size-1]
                tv_akhir.text = Helper.getDateFormat(activity!!, lastDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
            }
            else{
                firstDate = dates[0]
                tv_awal.text = Helper.getDateFormat(activity!!, firstDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
                lastDate = null
                tv_akhir.text = ""
            }

        }

        calendarView.setOnDateChangedListener { widget, date, selected ->
            if(!selected)
            {
                calendarView.selectedDate = date
            }

            when (val size = calendarView.selectedDates.size) {
                0 -> {
                    firstDate = null
                    tv_awal.text = ""
                    lastDate = null
                    tv_akhir.text = ""
                }
                1 -> {
                    firstDate = date
                    tv_awal.text = Helper.getDateFormat(activity!!, firstDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
                    lastDate = date
                    tv_akhir.text = Helper.getDateFormat(activity!!, lastDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
                }
                else -> {
                    firstDate = calendarView.selectedDates[0]
                    tv_awal.text = Helper.getDateFormat(activity!!, firstDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
                    lastDate = calendarView.selectedDates[size-1]
                    tv_akhir.text = Helper.getDateFormat(activity!!, lastDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
                }
            }
        }

        close_btn.setOnClickListener {
            dismiss()
        }

        btn_save.setOnClickListener {
            if(firstDate == null || lastDate == null){
                Toast.makeText(activity,"Choose the date first",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mListener?.onDateRangeClicked(firstDate,lastDate,type)
            dismiss()
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        mListener = if (parent != null) {
            parent as Listener
        } else {
            context as Listener
        }
    }

    override fun onDetach() {
        mListener = null
        super.onDetach()
    }

    override fun getTheme(): Int {
        return R.style.BottomSheetDialogTheme
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        dialog.setOnShowListener { dialog1 ->
            val d = dialog1 as BottomSheetDialog
            val bottomSheet = d.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
            BottomSheetBehavior.from(bottomSheet!!).setState(BottomSheetBehavior.STATE_EXPANDED)
        }
        return dialog
    }

    interface Listener {
        fun onDateRangeClicked(firstDate: CalendarDay?, lastDate:CalendarDay?, type:Int)
    }

    companion object {
        const val TAG = "FilterDateDialog"

        fun newInstance(): RangeDateDialog =
           RangeDateDialog().apply {
                arguments = Bundle().apply {
                }
            }

    }
}
