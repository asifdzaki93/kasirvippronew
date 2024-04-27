package id.kasirvippro.android.feature.hutangpiutang.hutang

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.hutangpiutang.Hutang
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_hutang.view.*
import id.kasirvippro.android.utils.AppConstant


class HutangAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Hutang.Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_hutang, parent, false))
    }

    override fun getItemCount(): Int {
        if(listProduct.size == 0){
            return 0
        }
        if(limit!! == -1){
            return listProduct.size
        }
        if(listProduct.size > limit!!){
            return limit!!
        }

        return listProduct.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val product = listProduct[position]
            holder.bindData(product, position)
        }
    }

    fun setItems(listProduct: List<Hutang.Data>?) {
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
        private val dateTv = view.tv_date
        private val totalTv = view.tv_total



        @SuppressLint("SetTextI18n")
        fun bindData(data: Hutang.Data, position: Int) {
            nameTv.text = "${data.name_supplier}"
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
    var limit: Int ?= -1


    interface ItemClickCallback{
        fun onClick(data: Hutang.Data)
    }
}