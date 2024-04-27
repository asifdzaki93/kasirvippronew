package id.kasirvippro.android.feature.sellReturn.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_sell.view.*

class SellReturnAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<Cart>()
    private val tempList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_sell, parent, false))
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

    fun updateItem(cart:Cart,position: Int){
        listProduct[position] = cart
        notifyItemChanged(position)
    }

    fun deleteItem(position: Int){
        listProduct.removeAt(position)
        tempList.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(cart:Cart){
        val pos = tempList.indexOf(cart.product?.id_product)
        if(pos > -1){
            listProduct[pos] = cart
            tempList[pos] = cart.product?.id_product!!
            notifyItemChanged(pos)
        }
        else{
            listProduct.add(cart)
            tempList.add(cart.product?.id_product!!)
            notifyItemInserted(itemCount-1)
        }

    }

    fun setItems(listProduct: List<Cart>?) {
        //this.listProduct.clear()
        val lastCount = itemCount
        listProduct?.let {
            it.forEach {cart->
                this.listProduct.add(cart)
                tempList.add(cart.product?.id_product!!)
            }

        }
        notifyItemRangeInserted(lastCount,listProduct!!.size)
    }

    fun clearAdapter(){
        listProduct.clear()
        tempList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameTv = view.tv_name
        private val priceTv = view.tv_price
        private val stockTv = view.tv_stok
        private val imageIv = view.iv_photo
        private val countLayout = view.ll_count
        private val countTv = view.tv_count
        private val decreaseBtn = view.btn_minus
        private val increaseBtn = view.btn_plus
        private val deleteBtn = view.btn_delete
        private val noteTv = view.tv_note
        private val addNoteTv = view.tv_addnote
       // private val addOnTv = view.tv_addon


        @SuppressLint("SetTextI18n")
        fun bindData(data: Cart, position: Int) {
            val decimal = AppConstant.DECIMAL.getDecimalData()
            val product = data.product
            nameTv.text = "${product?.name_product}"
           // priceTv.text = AppConstant.CURRENCY.getCurrencyData() + "${(product?.selling_price!!)}"

            if(data.new_price == "0"){
                if(decimal=="No Decimal") {
                    priceTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(product?.selling_price!!)
                }else{
                    priceTv.text = AppConstant.CURRENCY.getCurrencyData() + product?.selling_price!!
                }
            }else{
                if(decimal=="No Decimal") {
                    priceTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(data.new_price!!)
                }else{
                    priceTv.text = AppConstant.CURRENCY.getCurrencyData() + data.new_price!!
                }
            }

            val stock = product?.stock!!.toDouble()
            if(decimal=="No Decimal") {
                countTv.text = Helper.convertToCurrency(data.count!!)
            }else{
                countTv.text = data.count.toString()
            }
            noteTv.text = "${data.note}"

            if("0" == product.have_stock){
                stockTv.visibility = View.GONE
            }
            else{
                stockTv.visibility = View.VISIBLE
                if(decimal=="No Decimal") {
                    stockTv.text = "Stock : ${Helper.convertToCurrency(stock)}"
                }else{
                    stockTv.text = "Stock : ${stock}"
                }
            }

            if(product?.img == null){
                GlideApp.with(itemView.context)
                    .load(R.drawable.logo_bulat)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(imageIv)

            }
            else{
                GlideApp.with(itemView.context)
                    .load(product?.img)
                    .error(R.drawable.logo_bulat)
                    .placeholder(R.drawable.logo_bulat)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(imageIv)
            }

            increaseBtn.setOnClickListener {
                callback?.onIncrease(data,position)
            }


            decreaseBtn.setOnClickListener {
                callback?.onDecrease(data,position)

            }

            deleteBtn.setOnClickListener {
                callback?.onDelete(data,position)
            }

            addNoteTv.setOnClickListener {
                callback?.onNote(data,position)
            }

            noteTv.setOnClickListener {
                callback?.onNote(data,position)
            }

            countTv.setOnClickListener {
                callback?.onCountDialog(data,position)
            }

            /*addOnTv.setOnClickListener {
                callback?.onAddOnDialog(data,position)
            }*/

            priceTv.setOnClickListener {
                callback?.onPriceDialog(data,position)
            }

            nameTv.setOnClickListener {
                callback?.onPriceDialog(data,position)
            }


        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onDecrease(data: Cart, position: Int)
        fun onIncrease(data: Cart, position: Int)
        fun onDelete(data: Cart, position: Int)
        fun onNote(data: Cart, position: Int)
        fun onCountDialog(data: Cart, position: Int)
        fun onAddOnDialog(data: Cart, position: Int)
        fun onPriceDialog(data: Cart, position: Int)
    }
}