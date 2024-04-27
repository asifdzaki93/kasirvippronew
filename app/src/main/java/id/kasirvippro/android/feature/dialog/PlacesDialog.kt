package id.kasirvippro.android.feature.dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.kasirvippro.android.R
import kotlinx.android.synthetic.main.fragment_places_dialog.*
import kotlinx.android.synthetic.main.item_place.view.*

class PlacesDialog : BottomSheetDialogFragment() {
    private var mListener: Listener? = null
    private var data: List<String> ?= null
    private var title: String? = ""
    private var selected: String ?= null
    private var type:Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_places_dialog, container, false)
    }

    fun setListener(listener: Listener){
        mListener = listener
    }

    fun setDataProvince(title:String, type:Int, list:List<String>, select:String){
        this.title = title
        this.type = type
        data = list
        selected = select
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (context == null) return
        list.layoutManager = LinearLayoutManager(context)
        tv_title.text = title
        list.adapter = ItemAdapter(data!!,selected!!)

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
        fun onItemClicked(data: String, type:Int)
    }

    private inner class ViewHolder internal constructor(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_place, parent, false)) {

        //        internal val text: TextView = itemView.name_tv
//        internal val icon: ImageView = itemView.icon_iv
        internal val button: TextView = itemView.tv_name
        internal val check: ImageView = itemView.iv_check

        init {
            button.setOnClickListener {
                mListener?.let {
                    data?.get(adapterPosition)?.let { it1 -> it.onItemClicked(it1,type) }
                    dismiss()
                }
            }
        }
    }

    private inner class ItemAdapter internal constructor(val data: List<String>, val selected: String) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val place = data[position]
            holder.button.text = place

            if(place == selected){
                holder.check.visibility = View.VISIBLE
            }
            else{
                holder.check.visibility = View.INVISIBLE
            }

        }

        override fun getItemCount(): Int {
            return data.size
        }
    }

    companion object {
        const val TAG = "PlacesDialog"

        fun newInstance(): PlacesDialog =
           PlacesDialog().apply {
                arguments = Bundle().apply {
                }
            }

    }
}
