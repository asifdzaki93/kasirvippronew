package id.kasirvippro.android.feature.hutangpiutang.detailHutang

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.hutangpiutang.DetailHutang
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_hutang_detail.view.*
import id.kasirvippro.android.utils.AppConstant


class DetailHutangAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<DetailHutang.Data>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_piutang_detail, parent, false))
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

    fun setItems(listProduct: List<DetailHutang.Data>?) {
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

        private val tvDate = view.tv_date
        private val tvNominal = view.tv_price


        @SuppressLint("SetTextI18n")
        fun bindData(data: DetailHutang.Data, position: Int) {
            tvDate.text = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd","dd MMMM yyyy")
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                tvNominal.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.nominal!!)
            }else{
                tvNominal.text = AppConstant.CURRENCY.getCurrencyData() + data.nominal!!
            }


        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onClick(data: DetailHutang.Data)
    }
}