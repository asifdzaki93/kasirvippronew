package id.kasirvippro.android.feature.manage.packages.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.packages.Packages
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_packagesdata.view.*
import kotlinx.android.synthetic.main.item_list_packagesdata.view.btn_delete
import kotlinx.android.synthetic.main.item_list_packagesdata.view.tv_name
import kotlinx.android.synthetic.main.item_list_packagesdata.view.*

class PackagesListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Packages>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_packagesdata, parent, false))
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

    fun setItems(listProduct: List<Packages>?) {
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
        private val phoneTv = view.tv_phone
        private val deleteBtn = view.btn_delete
        private val addBtn = view.btn_add
        private val detailBtn = view.btn_detail

        fun bindData(data: Packages, position: Int) {
            nameTv.text = "${data.name_packages}"

            nameTv.text = "${data.name_packages}"
            phoneTv.text = "${data.details}"

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

            addBtn.setOnClickListener {
                if(callback != null){
                    callback?.onAdd(data)
                }
            }

            detailBtn.setOnClickListener {
                if(callback != null){
                    callback?.onDetail(data)
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
        fun onClick(data: Packages)
        fun onAdd(data: Packages)
        fun onDetail(data: Packages)
        fun onDelete(data: Packages,position: Int)
    }
}