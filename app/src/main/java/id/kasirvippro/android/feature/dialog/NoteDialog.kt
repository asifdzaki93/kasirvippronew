package id.kasirvippro.android.feature.dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.kasirvippro.android.R
import id.kasirvippro.android.models.cart.Cart
import kotlinx.android.synthetic.main.fragment_note_dialog.*

class NoteDialog : BottomSheetDialogFragment() {
    private var mListener: Listener? = null
    private var selected: Cart?= null
    private var position: Int ?= -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_dialog, container, false)
    }

    fun setListener(listener: Listener){
        mListener = listener
    }

    fun setData(selected:Cart,pos:Int){
        this.selected = selected
        this.position = pos
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (context == null) return

        selected?.let {cart ->
            cart.note?.let {
                et_note.setText(it)
            }
        }

        btn_save.setOnClickListener {
            val note = et_note.text.toString().trim()
            selected!!.note = note
            mListener?.onNoteSaved(selected!!,position!!)
            dismiss()
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
        fun onNoteSaved(selected:Cart,pos:Int)
    }

    companion object {
        const val TAG = "NoteDialog"

        fun newInstance(): NoteDialog =
           NoteDialog().apply {
                arguments = Bundle().apply {
                }
            }

    }
}
