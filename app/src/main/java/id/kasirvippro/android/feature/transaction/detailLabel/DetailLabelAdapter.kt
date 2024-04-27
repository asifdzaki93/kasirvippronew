package id.kasirvippro.android.feature.transaction.detailLabel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.DetailLabel
import id.kasirvippro.android.ui.ext.htmlText
import kotlinx.android.synthetic.main.item_list_transactionlabel.view.*
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_transactionlabel.view.tv_name

class DetailLabelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<DetailLabel.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_transactionlabel, parent, false))
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

    fun setItems(listProduct: List<DetailLabel.Data>?) {
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

        private val storeTv = view.tv_store
        private val nameTv = view.tv_name
        private val line = view.line
        private val imageIv = view.tv_img
        private val descIv = view.tv_desc

        @SuppressLint("SetTextI18n")
        fun bindData(data: DetailLabel.Data, position: Int) {
            storeTv.htmlText("${data.name_store}")
            descIv.htmlText("${data.description}")
            nameTv.htmlText("${data.name_product}")


            GlideApp.with(itemView.context)
                .load(data.img)
                .error(R.drawable.logo_bulat)
                .placeholder(R.drawable.logo_bulat)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(imageIv)

            line.visibility = View.VISIBLE
            if(position == itemCount-1){
                line.visibility = View.GONE
            }

        }
    }
}