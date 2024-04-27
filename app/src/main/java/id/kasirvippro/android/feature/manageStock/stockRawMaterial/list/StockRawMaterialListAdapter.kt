package id.kasirvippro.android.feature.manageStock.stockRawMaterial.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_product.view.*

class StockRawMaterialListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<RawMaterial>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_product_opname, parent, false))
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

    fun setItems(listProduct: List<RawMaterial>?) {
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
        private val stockTv = view.tv_stok
        private val imageIv = view.iv_photo

        @SuppressLint("SetTextI18n")
        fun bindData(data: RawMaterial, position: Int) {
            val decimal = AppConstant.DECIMAL.getDecimalData()
            nameTv.text = "${data.name}"
            var desc = data.description
            if(desc.isNullOrEmpty() || desc.isNullOrBlank()){
                desc = "-"
            }

                stockTv.visibility = View.VISIBLE
                val stok = data.stock!!.toDouble()
                if(stok > 0){
                    if(decimal=="No Decimal") {
                        stockTv.text = "Last Stock : ${data.stock!!} ${data.unit}"
                    }else{
                        stockTv.text = "Last Stock : ${data.stock!!} ${data.unit}"
                    }
                    stockTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.primaryText))
                }
                else{
                    stockTv.text = "* Stock Out of stock"
                    stockTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.vermillion))
                }



            if(data.img == null){
                GlideApp.with(itemView.context)
                    .load(R.drawable.logo_bulat)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(imageIv)

            }
            else{
                GlideApp.with(itemView.context)
                    .load(data.img)
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
        fun onClick(data: RawMaterial)
    }
}