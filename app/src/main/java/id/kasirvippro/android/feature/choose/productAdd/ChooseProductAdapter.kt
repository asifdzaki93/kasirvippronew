package id.kasirvippro.android.feature.choose.productAdd

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_choose_product.view.*
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper


class ChooseProductAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Product>()
    private var checkStock = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_choose_product, parent, false)
        )
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

    fun setItems(listProduct: List<Product>?) {
        //this.listProduct.clear()
        val lastCount = itemCount
        listProduct?.let { this.listProduct.addAll(it) }
        notifyItemRangeInserted(lastCount, listProduct!!.size)
    }

    fun clearAdapter() {
        listProduct.clear()
        notifyDataSetChanged()
    }

    fun setCheckStok(isCheck: Boolean) {
        checkStock = isCheck
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTv = view.tv_name
        private val priceTv = view.tv_price
        private val stockTv = view.tv_stok
        private val imageIv = view.iv_photo
        private val infoTv = view.tv_info
        private val wrapper = view.ll_wrapper


        @SuppressLint("SetTextI18n")
        fun bindData(data: Product, position: Int) {
            nameTv.text = "${data.name_product}"
            var desc = data.description

            infoTv.visibility = View.VISIBLE
            if (desc.isNullOrEmpty() || desc.isNullOrBlank()) {
                desc = "-"
                infoTv.visibility = View.GONE
            }

            infoTv.text = "$desc"
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                priceTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.selling_price!!) + " " + data.unit
            }else{
                priceTv.text = AppConstant.CURRENCY.getCurrencyData() + data.selling_price!! + " " + data.unit
            }

            if ("0" == data.have_stock) {
                stockTv.visibility = View.GONE
                wrapper.isEnabled = true
                wrapper.isClickable = true
            } else {
                stockTv.visibility = View.VISIBLE
                val stok = data.stock!!.toDouble()
                if (stok > 0) {
                    stockTv.text = "Stock : ${data.stock!!} ${data.unit}"
                    stockTv.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorPrimaryLight
                        )
                    )
                } else {
                    stockTv.text = "* Out of stock"
                    stockTv.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.vermillion
                        )
                    )
                }

                wrapper.isEnabled = true
                wrapper.isClickable = true

            }





            if (data.img == null) {
                GlideApp.with(itemView.context)
                    .load(R.drawable.logo_bulat)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(imageIv)

            } else {
                GlideApp.with(itemView.context)
                    .load(data.img)
                    .error(R.drawable.logo_bulat)
                    .placeholder(R.drawable.logo_bulat)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(imageIv)
            }

            wrapper.setOnClickListener {
                if (callback != null) {
                    if(checkStock){
                        if("0" == data.have_stock){
                            callback?.onClick(data)
                        }
                        else{
                            val stok = data.stock!!.toDouble()
                            if (stok > 0) {
                                callback?.onClick(data)
                            }
                            else{
                                Toast.makeText(itemView.context,"Out of stock. Please wholesale first",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else{
                        callback?.onClick(data)
                    }



                }
            }

        }
    }

    var callback: ItemClickCallback? = null

    interface ItemClickCallback {
        fun onClick(data: Product)
    }
}