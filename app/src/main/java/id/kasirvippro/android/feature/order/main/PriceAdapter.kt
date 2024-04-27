package id.kasirvippro.android.feature.sell.confirmation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.price.Price
import kotlinx.android.synthetic.main.item_list_price.view.*
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_choose_product.view.*
import kotlinx.android.synthetic.main.item_list_price.view.ll_wrapper
import kotlinx.android.synthetic.main.item_list_price.view.tv_name


class PriceAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Price>()
    private var checkStock = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_price, parent, false)
        )
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

    fun setItems(listProduct: List<Price>?) {
        //this.listProduct.clear()
        val lastCount = itemCount
        listProduct?.let { this.listProduct.addAll(it) }
        notifyItemRangeInserted(lastCount, listProduct!!.size)
    }

    fun clearAdapter() {
        listProduct.clear()
        notifyDataSetChanged()
    }

    fun setCheckStok(isCheck: Boolean) {
        checkStock = isCheck
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTv = view.tv_name
        private val wrapper = view.ll_wrapper

        @SuppressLint("SetTextI18n")
        fun bindData(data: Price, position: Int) {
            nameTv.text = "${data.name_price}"


            wrapper.setOnClickListener {
                if (callback != null) {
                    callback?.onClick(data)
                }
            }
        }

    }

    var callback: ItemClickCallback? = null

    interface ItemClickCallback {
        fun onClick(data: Price)
    }
}