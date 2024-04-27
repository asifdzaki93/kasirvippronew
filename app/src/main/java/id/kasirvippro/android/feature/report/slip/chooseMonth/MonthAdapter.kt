package id.kasirvippro.android.feature.report.slip.chooseMonth

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_monthly.view.*
import org.threeten.bp.LocalDate

class MonthAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<FilterDialogDate>()
    private var selected:FilterDialogDate?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_monthly, parent, false))
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val product = listProduct[position]
            holder.bindData(product)
        }
    }

    fun setItems(listProduct: List<FilterDialogDate>,data:FilterDialogDate?) {
        //this.listProduct.clear()
        selected = data
        clearAdapter()
        this.listProduct.addAll(listProduct)
        notifyDataSetChanged()
    }

    fun clearAdapter(){
        listProduct.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTv = view.tv_name
        private val check = view.iv_check
        private val parentView = view.ll_parent


        fun bindData(data: FilterDialogDate) {

            val today = LocalDate.now()

            nameTv.text = Helper.getDateFormat(itemView.context, data.firstDate?.date.toString(), "yyyy-MM-dd", "MMMM")

            check.visibility = View.GONE
            selected?.let { filter->
                filter.firstDate?.let {

                    val monthYear = Helper.getDateFormat(itemView.context, data.firstDate?.date.toString(), "yyyy-MM-dd", "yyyy-MM")
                    val selectedMonthYear = Helper.getDateFormat(itemView.context, it.date.toString(), "yyyy-MM-dd", "yyyy-MM")

                    Log.d("monthYear", monthYear)
                    Log.d("selectedMonthYear", selectedMonthYear)

                    if(monthYear == selectedMonthYear){
                        parentView.background = ContextCompat.getDrawable(itemView.context,R.drawable.selector_btn_accent)
                        nameTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))
                    }
                    else{
                        parentView.background = ContextCompat.getDrawable(itemView.context,R.drawable.selector_btn_white)
                        nameTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.primaryText))
                    }
                }
            }


            if(today.year > data.firstDate?.year!!){
                parentView.isEnabled = true
            }
            else{
                parentView.isEnabled = today.month.value >= data.firstDate?.month!!
            }

            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                    selected = data
                    notifyDataSetChanged()
                }
            }
        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onClick(data: FilterDialogDate)
    }
}