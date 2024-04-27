package id.kasirvippro.android.feature.history.pengeluaran

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_history_header.view.*
import kotlinx.android.synthetic.main.item_list_history_pengeluaran.view.*
import id.kasirvippro.android.utils.AppConstant


class SpendAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Transaction>()
    private val HEADER = 1
    private val DATA = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = if (viewType == HEADER) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_history_header, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_history_pengeluaran, parent, false)
        }

        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val product = listProduct[position]
            holder.bindData(product, getItemViewType(position))
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

    fun setItems(listProduct: List<Transaction>?) {
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

        private val idTv = view.tv_id
        private val totalTv = view.tv_total
        private val supplierTv = view.tv_user


        @SuppressLint("SetTextI18n")
        fun bindData(data: Transaction, type: Int) {
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(HEADER == type){
                headerDateTv.text = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd","EEE, dd MMMM yyyy")
                if(decimal=="No Decimal") {
                    headerTotalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalorder!!)
                }else{
                    headerTotalTv.text = AppConstant.CURRENCY.getCurrencyData() + data.totalorder!!
                }
            }
            else{
                supplierTv.visibility = View.GONE
                data.by?.let {
                    supplierTv.text = data.by
                    supplierTv.visibility = View.VISIBLE
                }

                idTv.text = data.no_invoice
                if(decimal=="No Decimal") {
                    totalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.nominal!!)
                }else{
                    totalTv.text = AppConstant.CURRENCY.getCurrencyData() + data.nominal!!
                }


                itemView.setOnClickListener {
                    callback?.onClick(data)
                }

            }

        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onClick(data: Transaction)
    }
}