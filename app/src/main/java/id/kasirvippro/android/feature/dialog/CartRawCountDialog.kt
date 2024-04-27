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
import id.kasirvippro.android.R
import id.kasirvippro.android.models.cartRaw.CartRaw
import kotlinx.android.synthetic.main.fragment_cart_count_dialog.*
import id.kasirvippro.android.ui.NumberTextWatcher

class CartRawCountDialog : BottomSheetDialogFragment() {
    private var mListener: Listener? = null
    private var selected: CartRaw?= null
    private var position: Int ?= -1
    private var check = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart_count_dialog, container, false)
    }

    fun setListener(listener: Listener){
        mListener = listener
    }

    fun setData(selected:CartRaw,pos:Int,check:Boolean){
        this.selected = selected
        this.position = pos
        this.check = check
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (context == null) return

        et_count.addTextChangedListener(NumberTextWatcher(et_count))
        selected?.let {cart ->
            et_count.setText(cart.count?.toInt().toString())
        }

        btn_save.setOnClickListener {
            val count = et_count.text.toString().trim().replace(".","")
            if(count.isBlank() || count.isEmpty() || count == "0"){
                Toast.makeText(activity!!,"Stock must be filled",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val countDouble = count.toDouble()
            val produk = selected!!.product!!
            val stok = produk.stock!!.toDouble()
            if(countDouble > stok){
                Toast.makeText(activity!!,"Insufficient stock",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            selected!!.count = countDouble
            mListener?.onCountSaved(selected!!,position!!)
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
        fun onCountSaved(selected:CartRaw,pos:Int)
    }

    companion object {
        const val TAG = "CartRawCountDialog"

        fun newInstance(): CartRawCountDialog =
           CartRawCountDialog().apply {
                arguments = Bundle().apply {
                }
            }

    }
}
