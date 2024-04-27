package id.kasirvippro.android.feature.report.preOrder.daily

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.report.ReportPreOrder
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_preorder_header.view.*
import kotlinx.android.synthetic.main.item_list_preorder.view.*
import id.kasirvippro.android.utils.AppConstant

class ListPreOrderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<ReportPreOrder>()
    private val HEADER = 1
    private val DATA = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = if (viewType == HEADER) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_preorder_header, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_preorder, parent, false)
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

    fun setItems(listProduct: List<ReportPreOrder>?) {
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

        private val idTv = view.tv_name
        private val totalTv = view.tv_total
        private val methodTv = view.tv_info
       // private val statusTv = view.tv_status


        @SuppressLint("SetTextI18n")
        fun bindData(data: ReportPreOrder, position: Int, type:Int) {

            if(HEADER == type){
                headerDateTv.text = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd","EEE, dd MMMM yyyy")
                headerTotalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalsales!!)



            }
            else{
                val decimal = AppConstant.DECIMAL.getDecimalData()
                    totalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalsales!!)
                if(decimal=="No Decimal") {
                    val price = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.payment!!)
                    methodTv.text = price + " " + data.detail
                }else{
                    val price = data.payment!!
                    methodTv.text = price + " " + data.detail
                }

                idTv.text = data.no_invoice
               // statusTv.text = data.status



            }


            itemView.setOnClickListener {
                callback?.onClick(data)
            }

        }

    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onClick(data: ReportPreOrder)
    }
}