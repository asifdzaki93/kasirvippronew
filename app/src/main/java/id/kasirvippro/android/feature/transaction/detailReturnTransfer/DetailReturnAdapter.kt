package id.kasirvippro.android.feature.transaction.detailReturnTransfer

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.ui.ext.htmlText
import kotlinx.android.synthetic.main.item_list_transfer.view.*
import id.kasirvippro.android.utils.AppConstant

class DetailReturnAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<DetailTransaction.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_transfer, parent, false))
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

    fun setItems(listProduct: List<DetailTransaction.Data>?) {
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
        private val countTv = view.tv_count
        private val priceTv = view.tv_price
        private val subtotalTv = view.tv_subtotal
        private val line = view.line


        @SuppressLint("SetTextI18n")
        fun bindData(data: DetailTransaction.Data, position: Int) {
            nameTv.htmlText("${data.name_product}")
            countTv.text = "${(data.amount!!)}x"
            priceTv.text = "@${(data.price!!)}"
            subtotalTv.text = AppConstant.CURRENCY.getCurrencyData() + (data.totalprice!!)


            line.visibility = View.VISIBLE
            if(position == itemCount-1){
                line.visibility = View.GONE
            }

        }
    }
}