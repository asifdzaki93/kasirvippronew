package id.kasirvippro.android.feature.report.penjualan

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.report.ReportPenjualan
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant

import kotlinx.android.synthetic.main.item_list_report_penjualan.view.*

class PenjualanAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<ReportPenjualan.Penjualan>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_report_penjualan, parent, false))
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

    fun setItems(listProduct: List<ReportPenjualan.Penjualan>?) {
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
        private val totalTv = view.tv_total
        private val infoTv = view.tv_info

        @SuppressLint("SetTextI18n")
        fun bindData(data: ReportPenjualan.Penjualan) {
            nameTv.text = "${data.name_product}"
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                totalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalprice!!)
            }else{
                totalTv.text = AppConstant.CURRENCY.getCurrencyData() + data.totalprice!!
            }
            var jual = data.selling_price
            if(jual == null){
                jual = "0"
            }
            if(decimal=="No Decimal") {
                val info = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(jual)} x${Helper.convertToCurrency(data.amount!!)} ${data.unit}"
                infoTv.text = info
            }else{
                val info = AppConstant.CURRENCY.getCurrencyData() + "${jual} x${data.amount!!} ${data.unit}"
                infoTv.text = info
            }



        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: ReportPenjualan.Penjualan)
    }
}