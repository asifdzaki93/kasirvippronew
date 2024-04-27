package id.kasirvippro.android.feature.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.DetailTransaction
import kotlinx.android.synthetic.main.fragment_pay_dialog.*
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.ui.NumberTextWatcher
import kotlinx.android.synthetic.main.fragment_pay_dialog.btn_save
import kotlinx.android.synthetic.main.fragment_pay_dialog.close_btn
import kotlinx.android.synthetic.main.fragment_pay_dialog.et_count


class PaymentDialog : BottomSheetDialogFragment() {
    private var mListener: Listener? = null
    private var selected: DetailTransaction?= null
    private var type = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pay_dialog, container, false)
    }

    fun setListener(listener: Listener){
        mListener = listener
    }

    fun setData(selected:DetailTransaction,type:Int){
        this.selected = selected
        this.type = type
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (context == null) return

        et_count.addTextChangedListener(NumberTextWatcher(et_count))
        var tagihan = 0.0
        selected?.let {trx ->
            trx.struk?.let {struk ->
                struk.no_invoice?.let {
                    tv_id.text = it
                }

                if(type == AppConstant.Code.CODE_TRANSACTION_SUPPLIER){
                    lbl_name.text = "Supplier"
                    struk.name_supplier?.let {
                        tv_name.text = it
                    }
                }
                else{
                    lbl_name.text = "Customer"
                    struk.name_customer?.let {
                        tv_name.text = it
                    }
                }

                struk.changepay?.let {
                    tagihan = it.toDouble()*-1
                    val decimal = AppConstant.DECIMAL.getDecimalData()
                    if(decimal=="No Decimal") {
                        tv_total.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(tagihan)
                    }else{
                        tv_total.text = AppConstant.CURRENCY.getCurrencyData() + tagihan
                    }
                }
            }
        }

        btn_save.setOnClickListener {
            val text = et_count.text.toString().trim().replace(",","")
            if(text.isBlank() || text.isEmpty() || text == "0"){
                Toast.makeText(activity!!,"The payment amount is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val value = text.toDouble()
            if(value > tagihan){
                Toast.makeText(activity!!,"The payment amount exceeds the bill", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mListener?.onPay(selected!!,type,value.toString())
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
        fun onPay(selected:DetailTransaction,type:Int, value:String)
    }

    companion object {
        const val TAG = "PaymentDialog"

        fun newInstance(): PaymentDialog =
           PaymentDialog().apply {
                arguments = Bundle().apply {
                }
            }

    }
}
