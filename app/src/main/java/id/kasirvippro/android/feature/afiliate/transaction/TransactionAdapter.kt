package id.kasirvippro.android.feature.afiliate.transaction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.DetailCOmmision
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_history_header.view.*
import kotlinx.android.synthetic.main.item_list_history_transaction.view.*
import id.kasirvippro.android.utils.AppConstant

class TransactionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<DetailCOmmision>()
    private val HEADER = 1
    private val DATA = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = if (viewType == HEADER) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_history_header, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_history_transaction, parent, false)
        }

        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val product = listProduct[position]
            holder.bindData(product, position, getItemViewType(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val transaction = listProduct[position]
        return if ("header" == transaction.type) {
            HEADER
        } else {
            DATA
        }
    }

    fun setItems(listProduct: List<DetailCOmmision>?) {
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

        private val headerDateTv = view.tv_header_date
        private val headerTotalTv = view.tv_header_total

        private val photoIv = view.iv_photo
        private val idTv = view.tv_id
        private val totalTv = view.tv_total
        private val methodTv = view.tv_method
        private val statusTv = view.tv_status


        @SuppressLint("SetTextI18n")
        fun bindData(data: DetailCOmmision, position: Int, type:Int) {

            if(HEADER == type){
                headerDateTv.text = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd","EEE, dd MMMM yyyy")
                headerTotalTv.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(data.amount!!)}"
            }
            else{
                idTv.text = data.detail
                totalTv.text = AppConstant.CURRENCY.getCurrencyData() + "${Helper.convertToCurrency(data.amount!!)}"
                statusTv.text = data.status

                var img = R.drawable.ic_cash
                if("Pending" == data.status){
                    img = R.drawable.ic_banking
                }
                GlideApp.with(itemView.context)
                    .load(img)
                    .error(R.drawable.logo_bulat)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(photoIv)

                when {
                    "Pending" == data.status -> {
                        statusTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_credit_text))
                        statusTv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_credit)
                    }
                    "cancel" == data.status -> {
                        statusTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_cancel_text))
                        statusTv.background =  ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_cancel)
                    }
                    else -> {
                        statusTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_success_text))
                        statusTv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_success)
                    }
                }

                itemView.setOnClickListener {
                    callback?.onClick(data)
                }

            }

        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onClick(data: DetailCOmmision)
    }
}