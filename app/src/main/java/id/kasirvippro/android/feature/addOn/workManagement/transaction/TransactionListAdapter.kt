package id.kasirvippro.android.feature.addOn.workManagement.transaction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.DetailJob
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_datawork.view.*

class TransactionListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<DetailJob>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_datawork, parent, false))
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

    fun setItems(listProduct: List<DetailJob>?) {
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

        private val nameTv = view.tv_id
        private val photoIv = view.iv_photo
        private val statusIv = view.tv_status
        private val dateIv = view.tv_date


        @SuppressLint("SetTextI18n")
        fun bindData(data: DetailJob, position: Int) {
            nameTv.text = "${data.no_invoice}"
            statusIv.text = "${data.status}"
            dateIv.text = "${data.date}"

            var img = R.drawable.ic_cash
            if("pending" == data.status || "billing" == data.status){
                img = R.drawable.ic_banking
            }
            GlideApp.with(itemView.context)
                .load(img)
                .error(R.drawable.logo_bulat)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(photoIv)

            when {
                "billing" == data.status -> {
                    statusIv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_credit_text))
                    statusIv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_credit)
                }
                "process" == data.status -> {
                    statusIv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_credit_text))
                    statusIv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_credit)
                }
                "finish" == data.status -> {
                    statusIv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_finish_text))
                    statusIv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_finish)
                }
                "cancel" == data.status -> {
                    statusIv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_cancel_text))
                    statusIv.background =  ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_cancel)
                }
                "debt" == data.status -> {
                    statusIv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_debt_text))
                    statusIv.background =  ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_debt)
                }
                else -> {
                    statusIv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_success_text))
                    statusIv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_success)
                }
            }

            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                }
            }
        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onClick(data: DetailJob)
    }
}