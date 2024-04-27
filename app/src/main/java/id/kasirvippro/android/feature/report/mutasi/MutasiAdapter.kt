package id.kasirvippro.android.feature.report.mutasi

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.report.ReportMutasi
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_report_mutasi.view.*
import kotlinx.android.synthetic.main.item_list_report_mutasi.view.tv_margin
import kotlinx.android.synthetic.main.item_list_report_mutasi_detail.view.*

class MutasiAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<ReportMutasi.Transaksi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_report_mutasi, parent, false))
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

    fun setItems(listProduct: List<ReportMutasi.Transaksi>?) {
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

        private val dateTv = view.tv_date
        private val omsetTv = view.tv_omset
        private val marginTv = view.tv_margin
        private val idTv = view.tv_id
        private val totalMarginTv = view.tv_total_margin
        private val totalBeliTv = view.tv_total_beli
        private val totalJualTv = view.tv_total_jual
        //private val totalQtyTv = view.tv_total_qty
        private val detailView = view.ll_detail
        private val moreBtn = view.btn_more
        private val lessBtn = view.btn_less
        private val list = view.list

        @SuppressLint("SetTextI18n")
        fun bindData(data: ReportMutasi.Transaksi, position: Int) {
            idTv.text = "${data.no_invoice}"
            dateTv.text = Helper.getDateFormat(itemView.context,data.tanggal!!,"yyyy-MM-dd","dd MMM yyyy")

            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                omsetTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.omset!!)
                marginTv.text = "${Helper.convertToCurrency(data.margin!!)}%"
            }else{
                omsetTv.text = AppConstant.CURRENCY.getCurrencyData() + data.omset!!
                marginTv.text = "${data.margin!!}%"
            }

            var totalBeli = 0.0
            var totalJual = 0.0
            var totalQty = 0.0
            var totalLaba = 0.0
            var totalMargin = 0.0
            for(dt in data.barang!!){
                dt.laba?.let {it ->
                    var laba = "0"
                    if(it.isNotEmpty() && it.isNotBlank()){
                        laba = it
                    }
                    val labaDouble = laba.toDouble()
                    totalLaba += labaDouble
                }

                dt.margin?.let {it ->
                    var margin = "0"
                    if(it.isNotEmpty() && it.isNotBlank()){
                        margin = it
                    }
                    val marginDouble = margin.toDouble()
                    totalMargin += marginDouble
                }

                dt.qty?.let {it ->
                    var qty = "0"
                    if(it.isNotEmpty() && it.isNotBlank()){
                        qty = it
                    }
                    val qtyDouble = qty.toDouble()
                    totalQty += qtyDouble
                }

                dt.harga_beli?.let {it ->
                    var beli = "0"
                    if(it.isNotEmpty() && it.isNotBlank()){
                        beli = it
                    }
                    val beliDouble = beli.toDouble()
                    totalBeli += beliDouble
                }

                dt.harga_jual?.let {it ->
                    var jual = "0"
                    if(it.isNotEmpty() && it.isNotBlank()){
                        jual = it
                    }
                    val jualDouble = jual.toDouble()
                    totalJual += jualDouble
                }
            }
            if(decimal=="No Decimal") {
                totalMarginTv.text = Helper.convertToCurrency(totalLaba)
                totalBeliTv.text = Helper.convertToCurrency(totalBeli)
                totalJualTv.text = Helper.convertToCurrency(totalJual)
            }else{
                totalMarginTv.text = totalLaba.toString()
                totalBeliTv.text = totalBeli.toString()
                totalJualTv.text = totalJual.toString()
            }

            //totalQtyTv.text = Helper.convertToCurrency(totalQty)

            list.layoutManager = LinearLayoutManager(itemView.context)
            val adapter = ItemAdapter(data.barang)
            list.adapter = adapter

            itemView.setOnClickListener {
                callback?.onClick(data)
            }

            moreBtn.setOnClickListener {
                if(detailView.isVisible){
                    showLess()
                }
                else{
                    showMore()
                }

            }

            lessBtn.setOnClickListener {
                showLess()
            }

        }

        fun showMore(){
            //moreBtn.visibility = View.GONE
            detailView.visibility = View.VISIBLE

        }

        fun showLess(){
            //moreBtn.visibility = View.VISIBLE
            detailView.visibility = View.GONE
        }

        private inner class InnerViewHolder internal constructor(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_list_report_mutasi_detail, parent, false)) {

            //        internal val text: TextView = itemView.name_tv
//        internal val icon: ImageView = itemView.icon_iv
            internal val noTv: TextView = itemView.tv_no
            internal val nameTv: TextView = itemView.tv_name
            internal val jualTv: TextView = itemView.tv_jual
            internal val beliTv: TextView = itemView.tv_beli
            internal val marginTv: TextView = itemView.tv_margin

        }

        private inner class ItemAdapter internal constructor(val data: List<ReportMutasi.Transaksi.Barang>) :
            RecyclerView.Adapter<InnerViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
                return InnerViewHolder(LayoutInflater.from(parent.context), parent)
            }

            @SuppressLint("SetTextI18n")
            override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
                val decimal = AppConstant.DECIMAL.getDecimalData()
                val model = data[position]
                val no = position+1

                if(decimal=="No Decimal") {
                    holder.noTv.text = Helper.convertToCurrency(no.toDouble())
                    holder.nameTv.text = "${model.nama_barang} x${Helper.convertToCurrency(model.qty!!)}"
                    holder.jualTv.text = Helper.convertToCurrency(model.harga_jual!!)
                    holder.beliTv.text = Helper.convertToCurrency(model.harga_beli!!)
                    holder.marginTv.text = "${Helper.convertToCurrency(model.laba!!)}(${Helper.convertToCurrency(model.margin!!)}%)"
                }else{
                    holder.noTv.text = no.toDouble().toString()
                    holder.nameTv.text = "${model.nama_barang} x${model.qty!!}"
                    holder.jualTv.text = model.harga_jual!!
                    holder.beliTv.text = model.harga_beli!!
                    holder.marginTv.text = "${model.laba!!}(${model.margin!!}%)"
                }

            }

            override fun getItemCount(): Int {
                return data.size
            }
        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: ReportMutasi.Transaksi)
    }
}