package id.kasirvippro.android.feature.report.absent

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.slip.Absent
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_absent.view.*

class AbsentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Absent>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_absent, parent, false))
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

    fun setItems(listProduct: List<Absent>?) {
        //this.listProduct.clear()
        listProduct?.let {
            this.listProduct.addAll(it)
            notifyDataSetChanged()
        }

    }

    fun clearAdapter(){
        listProduct.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val dateTv = view.tv_date
        private val timeTv = view.tv_time
        private val dayTv = view.tv_day

        @SuppressLint("SetTextI18n")
        fun bindData(data: Absent) {
            val day = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd","dd")
            val date = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd","EEEE, dd MMMM yyyy")
            dayTv.text = day
            dateTv.text = date
            timeTv.text = data.hour

            itemView.setOnClickListener {
                callback?.onClick(data)
            }

        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: Absent)
    }
}