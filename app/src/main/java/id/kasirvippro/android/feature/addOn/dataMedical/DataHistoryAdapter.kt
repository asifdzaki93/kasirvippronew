package id.kasirvippro.android.feature.addOn.dataMedical

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.customer.MedicalRecord
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.item_list_medical_record.view.*

class DataHistoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<MedicalRecord>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_medical_record, parent, false))
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

    fun setItems(listProduct: List<MedicalRecord>?) {
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
        private val complaintTv = view.tv_complaint
        private val adviceTv = view.tv_advice
        private val imageIv = view.tv_day
        private val dateIv = view.tv_date
        private val opIv = view.tv_operator
        private val invIv = view.tv_inv

        @SuppressLint("SetTextI18n")
        fun bindData(data: MedicalRecord, position: Int) {
            val date = Helper.getDateFormat(itemView.context,data.date!!,"yyyy-MM-dd h:s","dd-MM-yyyy h:s")
            dateIv.text = date
            nameTv.text = "${data.name_customer}"
            opIv.text = "Doctor: ${data.operator}"
            complaintTv.text = "${data.complaint}"
            adviceTv.text = "${data.advice}"
            invIv.text = "No Billing: ${data.no_invoice}"
            imageIv.text = Helper.getInisialName(data.name_customer)

            itemView.setOnClickListener {
                if(callback != null){
                    callback?.onClick(data)
                }
            }

        }
    }

    var callback: ItemClickCallback ?= null

    interface ItemClickCallback{
        fun onClick(data: MedicalRecord)
    }
}