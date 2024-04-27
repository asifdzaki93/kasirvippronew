package id.kasirvippro.android.feature.manage.supplier.credit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant

import kotlinx.android.synthetic.main.item_list_customer_credit.view.*

class SupplierCreditAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Transaction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_customer_credit, parent, false))
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

        private val idTv = view.tv_id
        private val dateTv = view.tv_date
        private val totalTv = view.tv_total

        @SuppressLint("SetTextI18n")
        fun bindData(data: Transaction) {
            idTv.text = "${data.no_invoice}"
            dateTv.text = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd","dd MMMM yyyy")

            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                totalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalorder!!)
            }else{
                totalTv.text = AppConstant.CURRENCY.getCurrencyData() + data.totalorder!!
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
        fun onClick(data: Transaction)
    }
}