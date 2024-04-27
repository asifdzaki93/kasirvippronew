package id.kasirvippro.android.feature.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.news.News
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_menu.view.*

class MenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_menu, parent, false))
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

    fun setItems(listProduct: List<News>?) {
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
        fun bindData(data: News, position: Int) {
            nameTv.text = "${data.title}"

            if(data.img == null){
                GlideApp.with(itemView.context)
                    .load(R.drawable.ic_noimage)
                    .transform(CenterCrop(), RoundedCorners(15))
                    .into(imageIv)

            }
            else{
                GlideApp.with(itemView.context)
                    .load(data.img)
                    .error(R.drawable.ic_noimage)
                    .placeholder(R.drawable.ic_noimage)
                    .transform(CenterCrop(), RoundedCorners(15))
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
        fun onClick(data: News)
    }
}