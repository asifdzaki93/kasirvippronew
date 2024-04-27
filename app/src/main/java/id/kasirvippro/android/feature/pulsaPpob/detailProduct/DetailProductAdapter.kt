package id.kasirvippro.android.feature.pulsaPpob.detailProduct

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.pulsaPpob.DetailPpob
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_detail_productppob.view.*

class DetailProductAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<DetailPpob>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_detail_productppob, parent, false))
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

    fun setItems(listProduct: List<DetailPpob>?) {
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
        private val priceTv = view.tv_price
        private val skuTv = view.tv_sku
        private val customernoTv = view.tv_customerno
        private val tagihanTv = view.tv_tagihan
        private val reffTv = view.tv_reff
        private val orderBtn = view.tv_order

        @SuppressLint("SetTextI18n")
        fun bindData(data: DetailPpob, position: Int) {
            nameTv.text = "${data.customer_name}"
            skuTv.text = "${data.buyer_sku_code}"
            customernoTv.text = "${data.customer_no}"
            tagihanTv.text = "${data.tagihan}"
            reffTv.text = "${data.ref_id}"
            var desc = data.desc
            if(desc.isNullOrEmpty() || desc.isNullOrBlank()){
                desc = "-"
            }

          //  infoTv.text = "$desc"
            priceTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.price!!)

            orderBtn.setOnClickListener {
                if(callback != null){
                    callback?.onOrder(data)
                }
            }

            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                }
            }
        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: DetailPpob)
        fun onOrder(data: DetailPpob)
    }
}