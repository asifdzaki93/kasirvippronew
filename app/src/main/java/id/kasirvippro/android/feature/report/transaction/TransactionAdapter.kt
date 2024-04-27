package id.kasirvippro.android.feature.report.transaction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.report.ReportTransaksi
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_report_transaction.view.*

class TransactionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<ReportTransaksi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_report_transaction, parent, false))
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

    fun setItems(listProduct: List<ReportTransaksi>?) {
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
        private val terjualTv = view.tv_terjual
        private val sisaTv = view.tv_last_stock

        @SuppressLint("SetTextI18n")
        fun bindData(data: ReportTransaksi) {
            nameTv.text = "${data.name_product}"
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                terjualTv.text = "Transactions : ${Helper.convertToCurrency(data.sales!!)} ${data.unit!!}"
            }else{
                terjualTv.text = "Transactions : ${data.sales!!} ${data.unit!!}"
            }


            if("0" == data.have_stock){
                sisaTv.visibility = View.GONE
            }
            else{
                sisaTv.visibility = View.VISIBLE
                val stok = data.last_stock!!.toDouble()
                if(stok > 0){
                    if(decimal=="No Decimal") {
                        sisaTv.text = "Remaining Stock : ${Helper.convertToCurrency(data.last_stock!!)} ${data.unit!!}"
                    }else{
                        sisaTv.text = "Remaining Stock : ${data.last_stock!!} ${data.unit!!}"
                    }
                    sisaTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.secondaryText))
                }
                else{
                    sisaTv.text = "* Out of stock"
                    sisaTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.vermillion))
                }
            }


        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: ReportTransaksi)
    }
}