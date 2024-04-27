package id.kasirvippro.android.feature.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.kasirvippro.android.R
import id.kasirvippro.android.feature.addOn.main.AddOnActivity
import id.kasirvippro.android.feature.afiliate.main.AfiliateActivity
import id.kasirvippro.android.feature.manage.main.ManageActivity
import id.kasirvippro.android.feature.qrCode.QrCodeActivity
import id.kasirvippro.android.feature.shift.closingShift.list.ClosingShiftListActivity
import id.kasirvippro.android.feature.report.main.ReportActivity
import kotlinx.android.synthetic.main.fragment_menu_dialog.*

class MenuDialog : BottomSheetDialogFragment() {
    private var mListener: Listener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_dialog, container, false)
    }

    fun setListener(listener: Listener){
        mListener = listener
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (context == null) return

        btn_report.setOnClickListener {
            startActivity(Intent(activity, ReportActivity::class.java))
        }
        btn_manage.setOnClickListener {
            startActivity(Intent(activity, ManageActivity::class.java))
        }

        btn_close_shift.setOnClickListener {
                startActivity(Intent(activity, ClosingShiftListActivity::class.java))
        }

        btn_qrcode.setOnClickListener {
                startActivity(Intent(activity, QrCodeActivity::class.java))
        }
        btn_afiliate.setOnClickListener {
            startActivity(Intent(activity, AfiliateActivity::class.java))
        }
        btn_addon.setOnClickListener {
            startActivity(Intent(activity, AddOnActivity::class.java))
        }
        close_btn.setOnClickListener {
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment

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
    }

    companion object {
        const val TAG = "MenuDialog"

        fun newInstance(): MenuDialog =
            MenuDialog().apply {
                arguments = Bundle().apply {
                }
            }

    }
}
