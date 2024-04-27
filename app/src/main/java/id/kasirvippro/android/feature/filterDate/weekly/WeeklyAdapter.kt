package id.kasirvippro.android.feature.filterDate.weekly

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_weekly.view.*
import org.threeten.bp.LocalDate

class WeeklyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<FilterDialogDate>()
    private var selected:FilterDialogDate?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_weekly, parent, false))
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val product = listProduct[position]
            holder.bindData(product, position)
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
        private val dateTv = view.tv_datee
        private val check = view.iv_check
        private val parentView = view.ll_parent


        @SuppressLint("SetTextI18n")
        fun bindData(data: FilterDialogDate, position: Int) {

            val today = LocalDate.now()
            val firstday = Helper.getDateFormat(itemView.context, data.firstDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
            val lastday = Helper.getDateFormat(itemView.context, data.lastDate?.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
            nameTv.text = "week ${position.plus(1)}"
            dateTv.text = "$firstday - $lastday"
            check.visibility = View.GONE
            selected?.let { filter->
                filter.firstDate?.let {

                    val selectedMonthYear = Helper.getDateFormat(itemView.context, it.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")

                    Log.d("firstday", firstday)
                    Log.d("selectedMonthYear", selectedMonthYear)

                    if(firstday == selectedMonthYear){
                        check.visibility = View.VISIBLE
                    }
                    else{
                        check.visibility = View.GONE
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
                }
            }
        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onClick(data: FilterDialogDate)
    }
}