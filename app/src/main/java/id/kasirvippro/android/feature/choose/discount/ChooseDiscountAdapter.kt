package id.kasirvippro.android.feature.choose.discount

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.item_list_choose_discount.view.*

class ChooseDiscountAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Discount>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_choose_discount, parent, false))
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

    fun setItems(listProduct: List<Discount>?) {
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
        private val infoTv = view.tv_info
        private val jenisTv = view.tv_jenis


        fun bindData(data: Discount, position: Int) {
            nameTv.text = "${data.name_discount}"
            infoTv.text = "${data.note}"

            var jenis = "%"
            if(AppConstant.CURRENCY.getCurrencyData() == data.type){
                jenis = AppConstant.CURRENCY.getCurrencyData()
            }
            jenisTv.text = jenis

            itemView.setOnClickListener {
                callback?.onClick(data)
            }
        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: Discount)
    }
}