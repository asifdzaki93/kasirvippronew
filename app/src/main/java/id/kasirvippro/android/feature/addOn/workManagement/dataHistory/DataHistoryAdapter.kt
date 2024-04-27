package id.kasirvippro.android.feature.addOn.workManagement.dataHistory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.DetailJob
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_trackingjob.view.*

class DataHistoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<DetailJob>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_trackingjob, parent, false))
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

    fun setItems(listProduct: List<DetailJob>?) {
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
        private val detailTv = view.tv_detail
        private val statusTv = view.tv_status
        private val imageIv = view.tv_day
        private val dateIv = view.tv_date

        @SuppressLint("SetTextI18n")
        fun bindData(data: DetailJob, position: Int) {
            val date = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd h:i:s","dd-MM-yyyy h:i:s")
            dateIv.text = date
            nameTv.text = "${data.no_invoice}"
            detailTv.text = "${data.note}"
            statusTv.text = data.status!!

            statusTv.visibility = View.VISIBLE
                val status = data.status!!
                if(status == "pending" || status == "billing"){
                    statusTv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_credit)
                    statusTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.vermillion))
                }
                else if(status == "paid of"){
                    statusTv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_success)
                    statusTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_finish_text))
                }
                else{
                    statusTv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_finish)
                    statusTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.bg_header_success))
                }
            imageIv.text = Helper.getInisialName(data.status)

            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                }
            }

        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: DetailJob)
    }
}