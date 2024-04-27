package id.kasirvippro.android.feature.pulsaPpob.paketData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.pulsaPpob.PulsaPpob
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_category.view.tv_name
import kotlinx.android.synthetic.main.item_list_product.view.*

class PaketDataListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<PulsaPpob>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_productppob, parent, false))
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

    fun setItems(listProduct: List<PulsaPpob>?) {
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
        private val stockTv = view.tv_stok
        private val infoTv = view.tv_info
        private val imageIv = view.iv_photo

        fun bindData(data: PulsaPpob, position: Int) {
            nameTv.text = "${data.product_name}"
            var desc = data.desc
            if(desc.isNullOrEmpty() || desc.isNullOrBlank()){
                desc = "-"
            }

          //  infoTv.text = "$desc"
            priceTv.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(data.price!!)}"
            if("true" == data.buyer_product_status){
                // stockTv.visibility = View.GONE
            }
            else{
                stockTv.visibility = View.VISIBLE
                val stok = data.buyer_product_status!!
                if(stok > "true"){
                    stockTv.text = data.seller_product_status!!
                    stockTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.colorPrimaryLight))
                }
                else{
                    stockTv.text = data.seller_product_status!!
                    stockTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.vermillion))
                }
            }

            if(data.gbr == null){
                GlideApp.with(itemView.context)
                    .load(R.drawable.logo_bulat)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(imageIv)

            }
            else{
                GlideApp.with(itemView.context)
                    .load(data.gbr)
                    .error(R.drawable.logo_bulat)
                    .placeholder(R.drawable.logo_bulat)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(imageIv)
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
        fun onClick(data: PulsaPpob)
    }
}