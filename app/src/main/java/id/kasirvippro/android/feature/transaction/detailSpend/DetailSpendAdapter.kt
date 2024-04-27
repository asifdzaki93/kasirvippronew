package id.kasirvippro.android.feature.transaction.detailSpend

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.DetailSpend
import id.kasirvippro.android.ui.ext.htmlText
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.item_list_detail_spend.view.*

class DetailSpendAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<DetailSpend.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_detail_spend, parent, false))
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val product = listProduct[position]
            holder.bindData(product,position)
        }
    }

    fun setItems(listProduct: List<DetailSpend.Data>?) {
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
        private val subtotalTv = view.tv_nominal
        private val line = view.line

        @SuppressLint("SetTextI18n")
        fun bindData(data: DetailSpend.Data,position: Int) {
            val decimal = AppConstant.DECIMAL.getDecimalData()
            nameTv.htmlText("${data.name_spending}")

            if(decimal=="No Decimal") {
                subtotalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.nominal!!)
            }else{
                subtotalTv.text = AppConstant.CURRENCY.getCurrencyData() + data.nominal!!
            }

            line.visibility = View.VISIBLE
            if(position == itemCount-1){
                line.visibility = View.GONE
            }
        }
    }
}