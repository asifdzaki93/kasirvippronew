package id.kasirvippro.android.feature.purchaseMaterial.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.kasirvippro.android.R
import id.kasirvippro.android.models.cartRaw.CartRaw
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_kulakan.view.*
import id.kasirvippro.android.utils.AppConstant


class PurchaseMaterialAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listProduct = mutableListOf<CartRaw>()
    private val tempList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_kulakan, parent, false))
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

    fun updateItem(cart:CartRaw,position: Int){
        listProduct[position] = cart
        notifyItemChanged(position)
    }

    fun deleteItem(position: Int){
        listProduct.removeAt(position)
        tempList.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(cart:CartRaw){
        val pos = tempList.indexOf(cart.product?.id_raw_material)
        if(pos > -1){
            listProduct[pos] = cart
            tempList[pos] = cart.product?.id_raw_material!!
            notifyItemChanged(pos)
        }
        else{
            listProduct.add(cart)
            tempList.add(cart.product?.id_raw_material!!)
            notifyItemInserted(itemCount-1)
        }

    }

    fun setItems(listProduct: List<CartRaw>?) {
        //this.listProduct.clear()
        val lastCount = itemCount
        listProduct?.let {
            it.forEach {cart->
                this.listProduct.add(cart)
                tempList.add(cart.product?.id_raw_material!!)
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
        private val countTv = view.tv_count
        private val decreaseBtn = view.btn_minus
        private val increaseBtn = view.btn_plus
        private val deleteBtn = view.btn_delete


        @SuppressLint("SetTextI18n")
        fun bindData(data: CartRaw, position: Int) {
            val decimal = AppConstant.DECIMAL.getDecimalData()
            val product = data.product
            nameTv.text = "${product?.name}"
            if(decimal=="No Decimal") {
                priceTv.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(product?.price!!)
                val stock = product.stock!!.toDouble()
                stockTv.text = "Stock : ${Helper.convertToCurrency(stock)} " + product.unit!!
                countTv.text = Helper.convertToCurrency(data.count!!)
            }else{
                priceTv.text = AppConstant.CURRENCY.getCurrencyData() + product?.price!!
                val stock = product.stock!!.toDouble()
                stockTv.text = "Stock : ${stock} " + product.unit!!
                countTv.text = data.count.toString()!!
            }



            if(product.img == null){
                GlideApp.with(itemView.context)
                    .load(R.drawable.logo_bulat)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(imageIv)

            }
            else{
                GlideApp.with(itemView.context)
                    .load(product.img)
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

            countTv.setOnClickListener {
                callback?.onCountDialog(data,position)
            }

        }
    }

    var callback: ItemClickCallback?= null

    interface ItemClickCallback{
        fun onDecrease(data: CartRaw, position: Int)
        fun onIncrease(data: CartRaw, position: Int)
        fun onDelete(data: CartRaw, position: Int)
        fun onCountDialog(data: CartRaw, position: Int)
    }
}