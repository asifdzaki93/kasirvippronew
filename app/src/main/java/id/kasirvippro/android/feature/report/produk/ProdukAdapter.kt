package id.kasirvippro.android.feature.report.produk

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.report.ReportProduct
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_report_product.view.*

class ProdukAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<ReportProduct.Sales>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_report_product, parent, false))
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

    fun setItems(listProduct: List<ReportProduct.Sales>?) {
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

        private val nameTv = view.tv_name
        private val priceTv = view.tv_sell
        private val dateTv = view.tv_date
        private val terjualTv = view.tv_terjual
        private val sisaTv = view.tv_last_stock
        private val penjualanTv = view.tv_penjualan
        private val beliTv = view.tv_buy_price
        private val jualTv = view.tv_sell_price
        private val profitTv = view.tv_profit
        private val detailView = view.ll_detail
        private val moreBtn = view.btn_more
        private val lessBtn = view.btn_less

        @SuppressLint("SetTextI18n")
        fun bindData(data: ReportProduct.Sales, position: Int) {
            val decimal = AppConstant.DECIMAL.getDecimalData()


            if(decimal=="No Decimal") {
                nameTv.text = "${data.name_product}"
                priceTv.text =
                    AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.laba_rugi!!)
                terjualTv.text = "Terjual : ${
                    AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.totalorder!!)
                }"
                sisaTv.text = "Stok Terakhir : ${Helper.convertToCurrency(data.last_stock!!)}"
                penjualanTv.text = Helper.convertToCurrency(data.sales!!)
                beliTv.text =
                    AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.purchase_price!!)
                jualTv.text =
                    AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.selling_price!!)
                profitTv.text =
                    AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.laba_rugi!!)
            }else{
                nameTv.text = "${data.name_product}"
                priceTv.text =
                    AppConstant.CURRENCY.getCurrencyData() + data.laba_rugi!!
                terjualTv.text = "Terjual : ${
                    AppConstant.CURRENCY.getCurrencyData() + data.totalorder!!
                }"
                sisaTv.text = "Stok Terakhir : ${data.last_stock!!}"
                penjualanTv.text = data.sales!!
                beliTv.text =
                    AppConstant.CURRENCY.getCurrencyData() + data.purchase_price!!
                jualTv.text =
                    AppConstant.CURRENCY.getCurrencyData() + data.selling_price!!
                profitTv.text =
                    AppConstant.CURRENCY.getCurrencyData() + data.laba_rugi!!
            }

            var day1 = ""
            var day2 = ""
            if(!data.first_date.isNullOrEmpty() && !data.first_date.isNullOrBlank()){
                day1 = Helper.getDateFormat(itemView.context,data.first_date!!,"yyyy-MM-dd","dd MMM yyyy")
            }
            if(!data.last_date.isNullOrEmpty() && !data.last_date.isNullOrBlank()){
                day2 = Helper.getDateFormat(itemView.context,data.last_date!!,"yyyy-MM-dd","dd MMM yyyy")
            }

            if(day1 == day2){
                dateTv.text = day1
            }
            else{
                dateTv.text = "$day1 - $day2"
            }

            showLess()

            moreBtn.setOnClickListener {
                showMore()
            }

            lessBtn.setOnClickListener {
                showLess()
            }

        }

        fun showMore(){
            priceTv.visibility = View.GONE
            moreBtn.visibility = View.GONE
            sisaTv.visibility = View.VISIBLE
            detailView.visibility = View.VISIBLE

            val dt = dateTv.text.toString()
            if(dt.isEmpty() || dt.isBlank()){
                dateTv.visibility = View.GONE
            }
            else{
                dateTv.visibility = View.VISIBLE
            }
        }

        fun showLess(){
            priceTv.visibility = View.VISIBLE
            moreBtn.visibility = View.VISIBLE
            dateTv.visibility = View.GONE
            sisaTv.visibility = View.GONE
            detailView.visibility = View.GONE
        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: ReportProduct)
    }
}