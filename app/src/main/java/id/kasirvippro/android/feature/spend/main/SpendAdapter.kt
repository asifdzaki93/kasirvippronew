package id.kasirvippro.android.feature.spend.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.RequestSpend
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.item_list_spend.view.*

class SpendAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<RequestSpend.Barang>()
    private val tempList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_spend, parent, false))
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

    fun updateItem(cart:RequestSpend.Barang,position: Int){
        listProduct[position] = cart
        notifyItemChanged(position)
    }

    fun deleteItem(position: Int){
        listProduct.removeAt(position)
        tempList.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(cart:RequestSpend.Barang){
        val pos = tempList.indexOf(cart.id)
        if(pos > -1){
            listProduct[pos] = cart
            tempList[pos] = cart.id!!
            notifyItemChanged(pos)
        }
        else{
            listProduct.add(cart)
            tempList.add(cart.id!!)
            notifyItemInserted(itemCount-1)
        }

    }

    fun clearAdapter(){
        listProduct.clear()
        tempList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTv = view.tv_name
        private val priceTv = view.tv_price
        private val deleteBtn = view.btn_delete


        @SuppressLint("SetTextI18n")
        fun bindData(data: RequestSpend.Barang, position: Int) {

            val decimal = AppConstant.DECIMAL.getDecimalData()

            nameTv.text = "${data.name_spending}"
            if(decimal=="No Decimal") {
                priceTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.nominal.toString())
            }else{
                priceTv.text = AppConstant.CURRENCY.getCurrencyData() + data.nominal.toString()
            }

            deleteBtn.setOnClickListener {
                callback?.onDelete(data,position)
            }



        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onDelete(data: RequestSpend.Barang, position: Int)
    }
}