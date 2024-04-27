package id.kasirvippro.android.feature.choose.nonTunai

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.NonTunai
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_choose_non_tunai.view.*

class ChooseNonTunaiAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<NonTunai>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_choose_non_tunai, parent, false))
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

    fun setItems(listProduct: List<NonTunai>?) {
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
        private val imageIv = view.iv_photo


        @SuppressLint("SetTextI18n")
        fun bindData(data: NonTunai) {
            nameTv.text = "${data.name_link}"

            if(data.img == null){
                GlideApp.with(itemView.context)
                    .load(R.drawable.logo_bulat)
                    .into(imageIv)

            }
            else{
                GlideApp.with(itemView.context)
                    .load(data.img)
                    .error(R.drawable.logo_bulat)
                    .placeholder(R.drawable.logo_bulat)
                    .into(imageIv)
            }

            itemView.setOnClickListener {
                callback?.onClick(data)
            }

        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: NonTunai)
    }
}