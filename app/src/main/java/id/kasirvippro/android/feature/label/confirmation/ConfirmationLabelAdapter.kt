package id.kasirvippro.android.feature.label.confirmation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.cart.Cart
import kotlinx.android.synthetic.main.item_list_label_transaction.view.*
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant


class ConfirmationLabelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Cart>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_label_transaction, parent, false))
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

    fun setItems(listProduct: List<Cart>?) {
        //this.listProduct.clear()
        listProduct?.let {
            this.listProduct.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun clearAdapter(){
        listProduct.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTv = view.tv_name
        private val countTv = view.tv_count

        @SuppressLint("SetTextI18n")
        fun bindData(data: Cart) {
            val product = data.product
            val count = data.count!!.toDouble()


            nameTv.text = "${product?.name_product}"
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                countTv.text = "${Helper.convertToCurrency(count)}x"
            }else{
                countTv.text = "${count}x"
            }
        }
    }
}