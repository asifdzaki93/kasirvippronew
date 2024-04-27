package id.kasirvippro.android.feature.manageOrder.table.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_table_order.view.tv_name
import kotlinx.android.synthetic.main.item_list_table_order.view.iv_photo

class TableDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Table>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_table_order, parent, false))
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

    fun setItems(listProduct: List<Table>?) {

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
        private val imageIv = view.iv_photo


        fun bindData(data: Table, position: Int) {
            nameTv.text = "${data.name_table}"

            if(data.img == null){
                GlideApp.with(itemView.context)
                    .load(R.drawable.ic_table_management)
                    .into(imageIv)

            }
            else{
                GlideApp.with(itemView.context)
                    .load(data.img)
                    .error(R.drawable.ic_table_management)
                    .placeholder(R.drawable.ic_table_management)
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
        fun onClick(data: Table)
    }
}