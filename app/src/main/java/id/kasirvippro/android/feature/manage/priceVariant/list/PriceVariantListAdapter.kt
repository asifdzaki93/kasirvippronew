package id.kasirvippro.android.feature.manage.priceVariant.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.priceVariant.PriceVariant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.item_list_addon_product.view.*

class PriceVariantListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<PriceVariant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_addon_product, parent, false))
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

    fun setItems(listProduct: List<PriceVariant>?) {
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
        private val deleteBtn = view.btn_delete

        @SuppressLint("SetTextI18n")
        fun bindData(data: PriceVariant, position: Int) {
            nameTv.text = "${data.name}"


            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                priceTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.price!!)
            }else{
                priceTv.text = AppConstant.CURRENCY.getCurrencyData() + data.price!!
            }
            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                }
            }

            deleteBtn.setOnClickListener {
                if(callback != null){
                    callback?.onDelete(data,position)
                }
            }

        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: PriceVariant)
        fun onDelete(data: PriceVariant,position: Int)
    }
}