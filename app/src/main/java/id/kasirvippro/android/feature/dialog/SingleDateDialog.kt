package id.kasirvippro.android.feature.dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import kotlinx.android.synthetic.main.fragment_single_date_dialog.*
import org.threeten.bp.LocalDate

class SingleDateDialog : BottomSheetDialogFragment() {
    private var mListener: Listener? = null
    private var selected: CalendarDay ?= null
    private var minDate: LocalDate ?= null
    private var maxDate: LocalDate ?= null
    private var type:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single_date_dialog, container, false)
    }

    fun setListener(listener: Listener){
        mListener = listener
    }

    fun setData(selected:CalendarDay?,minDate:LocalDate?,maxDate:LocalDate?,type: Int){
        this.selected = selected
        this.minDate = minDate
        this.maxDate = maxDate
        this.type = type
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (context == null) return
        val today = LocalDate.now()
        if(selected == null){
            selected =  CalendarDay.from(today)
        }
        calendarView.setTitleMonths(R.array.months)
        calendarView.selectedDate = selected
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




        calendarView.setOnDateChangedListener { widget, date, selected ->
            if(selected){
                Log.d("onDateSelected", date.date.toString())
                mListener?.onDateClicked(date,type)
                dismiss()
            }
        }

        close_btn.setOnClickListener {
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
        fun onDateClicked(selected: CalendarDay?,type:Int)
    }

    companion object {
        const val TAG = "SingleDateDialog"

        fun newInstance(): SingleDateDialog =
           SingleDateDialog().apply {
                arguments = Bundle().apply {
                }
            }

    }
}
