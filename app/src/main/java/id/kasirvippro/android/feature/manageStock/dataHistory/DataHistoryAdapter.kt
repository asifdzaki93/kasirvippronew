package id.kasirvippro.android.feature.manageStock.dataHistory

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.DetailHistory
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_tracking.view.*
import kotlinx.android.synthetic.main.item_list_tracking.view.tv_name

class DataHistoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<DetailHistory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_tracking, parent, false))
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

    fun setItems(listProduct: List<DetailHistory>?) {
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
        private val stockTv = view.tv_amount
        private val imageIv = view.tv_day
        private val dateIv = view.tv_date

        @SuppressLint("SetTextI18n")
        fun bindData(data: DetailHistory, position: Int) {
            val decimal = AppConstant.DECIMAL.getDecimalData()
            val date = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd","dd-MM-yyyy")
            dateIv.text = date
            nameTv.text = "${data.name_product}"
            detailTv.text = "${data.detail}"

                stockTv.visibility = View.VISIBLE
                val status = data.status!!
                if(status == "0"){
                    if(decimal=="No Decimal") {
                        stockTv.text = "+${Helper.convertToCurrency(data.stock!!)}"
                    }else{
                        stockTv.text = "+${data.stock!!}"
                    }

                    stockTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.bg_header_success))
                }
                else{
                    if(decimal=="No Decimal") {
                        stockTv.text = "-${Helper.convertToCurrency(data.stock!!)}"
                    }else{
                        stockTv.text = "-${data.stock!!}"
                    }
                    stockTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.vermillion))
                }
            imageIv.text = Helper.getInisialName(data.name_product)


            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                }
            }

        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: DetailHistory)
    }
}