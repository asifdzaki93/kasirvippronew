package id.kasirvippro.android.feature.report.listCashflow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.transaction.FlowCash
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_cashflow_header.view.*
import kotlinx.android.synthetic.main.item_list_cashflow.view.*
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.item_list_cashflow.view.ll_detail

class ListCashflowAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<FlowCash>()
    private val HEADER = 1
    private val DATA = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate = if (viewType == HEADER) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_cashflow_header, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_cashflow, parent, false)
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

    fun setItems(listProduct: List<FlowCash>?) {
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
        private val salesTotalTv = view.tv_header_totalsales
        private val purchaseTotalTv = view.tv_header_totalpurchase
        private val spendTotalTv = view.tv_header_totalspend
        private val returnTotalTv = view.tv_header_totalreturn
        private val mapTv = view.tv_map

        private val idTv = view.tv_id
        private val totalTv = view.tv_total
        private val methodTv = view.tv_method
       // private val statusTv = view.tv_status


        @SuppressLint("SetTextI18n")
        fun bindData(data: FlowCash, position: Int, type:Int) {

            if(HEADER == type){
                headerDateTv.text = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd","EEE, dd MMMM yyyy")
                headerTotalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalorder!!)
                salesTotalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalsales!!)
                purchaseTotalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalpurchase!!)
                spendTotalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalspend!!)
                returnTotalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalreturn!!)

                mapTv.setOnClickListener {
                    if(callback != null){
                        callback?.onClick(data)
                    }
                }

            }
            else{
                if(data.status == "minus"){
                    totalTv.text = "(-) " + AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalorder!!)
                }else{
                    totalTv.text = "(+) " + AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalorder!!)
                }
                idTv.text = data.no_invoice
                methodTv.text = data.payment
               // statusTv.text = data.status


                when {
                    "minus" == data.status -> {
                        totalTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_credit_text))
                        totalTv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_credit)
                    }
                    else -> {
                        totalTv.setTextColor(ContextCompat.getColor(itemView.context,R.color.status_success_text))
                        totalTv.background = ContextCompat.getDrawable(itemView.context,R.drawable.circle_status_success)
                    }
                }



            }


            itemView.setOnClickListener {
                callback?.onClick(data)
            }

        }

    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onClick(data: FlowCash)
    }
}