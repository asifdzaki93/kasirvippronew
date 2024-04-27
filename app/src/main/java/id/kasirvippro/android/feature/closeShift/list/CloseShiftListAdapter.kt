package id.kasirvippro.android.feature.closeShift.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.closeShift.CloseShift
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_closingshift.view.*

class CloseShiftListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<CloseShift>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_closingshift, parent, false))
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

    fun setItems(listProduct: List<CloseShift>?) {
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
        private val dateTV = view.tv_date
        private val shiftTV = view.tv_shift
        private val flagTV = view.tv_flag
        private val ccTV = view.tv_sales_credit_card
        private val cashawalTV = view.tv_cash_awal
        private val cashactualTV = view.tv_cash_actual
        private val salesdebitTV = view.tv_sales_debit
        private val ppnTV = view.tv_ppn
        private val salescomputerTV = view.tv_sales_computer

        @SuppressLint("SetTextI18n")
        fun bindData(data: CloseShift, position: Int) {
            nameTv.text = "${data.name_cashier}"
            dateTV.text = "${data.initial_date}"
            shiftTV.text = "${data.shift}"
            flagTV.text = "${data.flag}"

            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                cashawalTV.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.cash_awal!!)
                ccTV.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.sales_non_cash!!)
                ppnTV.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.ppn!!)
                salesdebitTV.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.sales_debt!!)
                salescomputerTV.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.sales_cash!!)
                cashactualTV.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.cash_actual!!)
            }else{
                cashawalTV.text = AppConstant.CURRENCY.getCurrencyData() + data.cash_awal!!
                ccTV.text = AppConstant.CURRENCY.getCurrencyData() + data.sales_non_cash!!
                ppnTV.text = AppConstant.CURRENCY.getCurrencyData() + data.ppn!!
                salesdebitTV.text = AppConstant.CURRENCY.getCurrencyData() + data.sales_debt!!
                salescomputerTV.text = AppConstant.CURRENCY.getCurrencyData() + data.sales_cash!!
                cashactualTV.text = AppConstant.CURRENCY.getCurrencyData() + data.cash_actual!!
            }


            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                }
            }

        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: CloseShift)
    }
}