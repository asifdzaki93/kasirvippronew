package id.kasirvippro.android.feature.shift.closingShift.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.closeShift.CloseShift
import kotlinx.android.synthetic.main.item_list_shift.view.*

class ClosingShiftListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<CloseShift>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_shift, parent, false))
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

    fun setItems(listProduct: List<CloseShift>?) {
        //this.listProduct.clear()
        val lastCount = itemCount
        listProduct?.let { this.listProduct.addAll(it) }
        notifyItemRangeInserted(lastCount,listProduct!!.size)
    }

    fun clearAdapter(){
        listProduct.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTv = view.tv_name
        private val shiftTv = view.tv_shift
        private val statusTv = view.tv_status
        private val dateTv = view.tv_date

        fun bindData(data: CloseShift, position: Int) {
            nameTv.text = "${data.name_cashier}"
            shiftTv.text = "${data.shift}"
            statusTv.text = "${data.status}"
            dateTv.text = "${data.initial_date}"

            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                }
            }

            when {
                "I" == data.flag -> {
                    statusTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_credit_text))
                    statusTv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_credit)
                }
                "C" == data.flag -> {
                    statusTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_cancel_text))
                    statusTv.background =  ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_cancel)
                }
                else -> {
                    statusTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_success_text))
                    statusTv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_success)
                }
            }

        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: CloseShift)
    }
}