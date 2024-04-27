package id.kasirvippro.android.feature.report.daily

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.report.ReportDaily
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.item_list_report_daily.view.*


class DailyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<ReportDaily.Daily>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_report_daily, parent, false))
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

    fun setItems(listProduct: List<ReportDaily.Daily>?) {
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

        private val operatorTv = view.tv_operator
        private val namestoreTv = view.tv_name_store
        private val dateTv = view.tv_date
        private val cashTv = view.tv_cash
        private val debtTv = view.tv_sales_debt
        private val bankTv = view.tv_bank_transfer
        private val posTv = view.tv_pos_payment
        private val subtotalTv = view.tv_total

        @SuppressLint("SetTextI18n")
        fun bindData(data: ReportDaily.Daily) {
            val decimal = AppConstant.DECIMAL.getDecimalData()
            operatorTv.text = "${data.operator}"

            if(decimal=="No Decimal") {
                cashTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.sales_cash!!)
                debtTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.sales_debt!!)
                bankTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.bank!!)
                posTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.pos!!)
                subtotalTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.sub_total!!)
            }else{
                cashTv.text = AppConstant.CURRENCY.getCurrencyData() + data.sales_cash!!
                debtTv.text = AppConstant.CURRENCY.getCurrencyData() + data.sales_debt!!
                bankTv.text = AppConstant.CURRENCY.getCurrencyData() + data.bank!!
                posTv.text = AppConstant.CURRENCY.getCurrencyData() + data.pos!!
                subtotalTv.text = AppConstant.CURRENCY.getCurrencyData() + data.sub_total!!
            }


            namestoreTv.text = data.name_store!!
            dateTv.text = data.date!!



        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: ReportDaily.Daily)
    }
}