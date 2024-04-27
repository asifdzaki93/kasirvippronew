package id.kasirvippro.android.feature.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.kasirvippro.android.R
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.fragment_places_dialog.*
import kotlinx.android.synthetic.main.item_list_choose_product.view.*
import kotlinx.android.synthetic.main.item_place.view.tv_name

class BottomProductDialog : BottomSheetDialogFragment() {
    private var mListener: Listener? = null
    private var data: List<Product> ?= null
    private var title: String? = ""
    private var type: Int? = -1
    private var selected: Product ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places_dialog_product, container, false)
    }

    fun setListener(listener: Listener){
        mListener = listener
    }

    fun setData(title:String, type:Int,list:List<Product>, select:Product?){
        this.title = title
        this.type = type
        data = list
        selected = select
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (context == null) return
        list.layoutManager = LinearLayoutManager(context)
        tv_title.text = title
        list.adapter = ItemAdapter(data!!,selected)

        close_btn.setOnClickListener {
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        if (parent != null) {
            mListener = parent as Listener
        } else {
            mListener = context as Listener
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
        fun onItemClicked(data: Product, type:Int)
    }

    private inner class ViewHolder internal constructor(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list_choose_product, parent, false)) {


        internal val button: TextView = itemView.tv_name
        internal val price: TextView = itemView.tv_price
        internal val stock: TextView = itemView.tv_stok
        internal val desc: TextView = itemView.tv_info
        internal val photo: ImageView? = itemView.iv_photo
        internal val wrapper: LinearLayout? = itemView.ll_wrapper


        init {
            if (wrapper != null) {
                wrapper.setOnClickListener {
                    mListener?.let {
                        data?.get(adapterPosition)?.let { it1 -> it.onItemClicked(it1,type!!) }
                        dismiss()
                    }
                }
            }
        }
    }

    private inner class ItemAdapter internal constructor(val data: List<Product>, val selected: Product?) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val place = data[position]
            holder.button.text = place.name_product
            holder.stock.text = place.stock
            holder.desc.text = place.description
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                holder.price.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(place.selling_price!!)
            }else{
                holder.price.text = AppConstant.CURRENCY.getCurrencyData() + place.selling_price!!
            }



            if ("0" == place.have_stock) {
                holder.stock.visibility = View.GONE
                holder.wrapper?.isEnabled = true
                holder.wrapper?.isClickable = true
            } else {
                holder.stock.visibility = View.VISIBLE
                val stok = place.stock!!.toDouble()
                if (stok > 0) {
                    holder.stock.text = "Stock : ${place.stock!!}"
                    holder.stock.setTextColor(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            R.color.colorPrimaryLight
                        )
                    )
                } else {
                    holder.stock.text = "* Out of stock"
                    holder.stock.setTextColor(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            R.color.vermillion
                        )
                    )
                }

                holder.wrapper?.isEnabled = true
                holder.wrapper?.isClickable = true

            }

            if (place.img == null) {
                holder.photo?.let {
                    GlideApp.with(holder.itemView.context)
                        .load(R.drawable.logo_bulat)
                        .transform(CenterCrop(), RoundedCorners(8))
                        .into(it)
                }

            } else {
                holder.photo?.let {
                    GlideApp.with(holder.itemView.context)
                        .load(place.img)
                        .error(R.drawable.logo_bulat)
                        .placeholder(R.drawable.logo_bulat)
                        .transform(CenterCrop(), RoundedCorners(8))
                        .into(it)
                }
            }



            //   holder.check.visibility = View.INVISIBLE
            //   selected?.let {
            //  if(place.id_product == it.id_product){
            //      holder.check.visibility = View.VISIBLE
            //  }
            //}


        }

        override fun getItemCount(): Int {
            return data.size
        }
    }

    companion object {
        const val TAG = "BottomProductDialog"

        fun newInstance(): BottomProductDialog =
            BottomProductDialog().apply {
                arguments = Bundle().apply {
                }
            }

    }
}
