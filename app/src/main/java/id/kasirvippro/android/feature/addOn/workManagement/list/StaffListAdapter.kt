package id.kasirvippro.android.feature.addOn.workManagement.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import id.kasirvippro.android.R
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_staffwork.view.*

class StaffListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Staff>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_staffwork, parent, false))
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

    fun setItems(listProduct: List<Staff>?) {
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
        private val photoIv = view.iv_photo
        private val totalIv = view.tv_totalpending
        private val totalfinishIv = view.tv_totalfinish
        private val totalsuccessIv = view.tv_totalsuccess


        @SuppressLint("SetTextI18n")
        fun bindData(data: Staff, position: Int) {
            nameTv.text = "${data.full_name}"
            totalIv.text = "Pending: ${data.work_procces}"
            totalfinishIv.text = "Finish: ${data.work_finish}"
            totalsuccessIv.text = "Paid Of: ${data.work_paid}"

            GlideApp.with(itemView.context)
                .load(data.img)
                .error(R.drawable.ic_user)
                .transform(CenterCrop(), CircleCrop())
                .into(photoIv)

            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                }
            }
        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onClick(data: Staff)
    }
}