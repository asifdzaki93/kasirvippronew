package id.kasirvippro.android.feature.choose.ongkir

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.ongkir.Ongkir
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_choose_customer.view.*

class ChooseOngkirAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Ongkir>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_choose_customer, parent, false))
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

    fun setItems(listProduct: List<Ongkir>?) {
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
        private val infoTv = view.tv_initial
        private val phoneTv = view.tv_phone

        @SuppressLint("SetTextI18n")
        fun bindData(data: Ongkir) {
            nameTv.text = "${data.name_ongkir}"
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                phoneTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.nominal!!)
            }else{
                phoneTv.text = AppConstant.CURRENCY.getCurrencyData() + data.nominal!!
            }
            infoTv.text = Helper.getInisialName(data.name_ongkir)

            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                }
            }
        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onClick(data: Ongkir)
    }
}