package id.kasirvippro.android.feature.transaction.detailSplit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.ui.ext.htmlText
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_transactionsplit.view.iv_photo
import kotlinx.android.synthetic.main.item_list_transactionsplit.view.tv_count
import kotlinx.android.synthetic.main.item_list_transactionsplit.view.tv_name
import kotlinx.android.synthetic.main.item_list_transactionsplit.view.tv_price
import kotlinx.android.synthetic.main.item_list_transactionsplit.view.tv_stok
import kotlinx.android.synthetic.main.item_list_transactionsplit.view.btn_minus
import kotlinx.android.synthetic.main.item_list_transactionsplit.view.btn_plus
import kotlinx.android.synthetic.main.item_list_transactionsplit.view.btn_delete

class DetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<DetailTransaction.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_transactionsplit, parent, false))
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

    fun setItems(listProduct: List<DetailTransaction.Data>?) {

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
        private val stockTv = view.tv_stok
        private val imageIv = view.iv_photo
        private val countTv = view.tv_count
        private val decreaseBtn = view.btn_minus
        private val increaseBtn = view.btn_plus
        private val deleteBtn = view.btn_delete


        @SuppressLint("SetTextI18n")
        fun bindData(data: DetailTransaction.Data, position: Int) {
            nameTv.htmlText("${data.name_product}")
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                countTv.text = Helper.convertToCurrency(data.amount!!)
                priceTv.text = "@${Helper.convertToCurrency(data.price!!)}"
            }else{
                countTv.text = data.amount!!
                priceTv.text = "@${data.price!!}"
            }


            if("0" == data.have_stock!!){
                stockTv.visibility = View.GONE
            }
            else{
                stockTv.visibility = View.VISIBLE
                if(decimal=="No Decimal") {
                    stockTv.text = "Stock : ${Helper.convertToCurrency(data.stock!!)}"
                }else{
                    stockTv.text = "Stock : ${data.stock!!}"
                }

            }

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

            deleteBtn.setOnClickListener {
                if(callback != null){
                    callback?.onDelete(data)
                }
            }

            increaseBtn.setOnClickListener {
                if(callback != null){
                    callback?.onPlus(data)
                }
            }
            decreaseBtn.setOnClickListener {
                if(callback != null){
                    callback?.onMinus(data)
                }
            }



        }


    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onDelete(data: DetailTransaction.Data)
        fun onPlus(data: DetailTransaction.Data)
        fun onMinus(data: DetailTransaction.Data)
    }
}