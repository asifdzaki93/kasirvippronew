package id.kasirvippro.android.feature.choose.newProduct

import android.annotation.SuppressLint
import android.content.Context
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
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.utils.glide.GlideApp
import kotlinx.android.synthetic.main.item_list_choose_product.view.*

class ProductAdapter(private val lists: MutableList<Product>,  private val onClick: OnProductClickListener) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var checkStock = true
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_choose_product, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = lists[position]

        holder.itemView.tv_name.text = "${list.name_product}"
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            holder.itemView.tv_price.text = AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(list.selling_price!!)
        }else{
            holder.itemView.tv_price.text = AppConstant.CURRENCY.getCurrencyData() + list.selling_price!!
        }
        holder.itemView.tv_info.text = "${list.description}"

        if ("0" == list.have_stock) {
            holder.itemView.tv_stok.visibility = View.GONE
            holder.itemView.ll_wrapper.isEnabled = true
            holder.itemView.ll_wrapper.isClickable = true
        } else {
            holder.itemView.tv_stok.visibility = View.VISIBLE
            val stok = list.stock!!.toDouble()
            if (stok > 0) {
                holder.itemView.tv_stok.text = "Stock : ${list.stock!!}"
                holder.itemView.tv_stok.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryLight))
            } else {
                holder.itemView.tv_stok.text = "* Out of stock"
                holder.itemView.tv_stok.setTextColor(ContextCompat.getColor(context, R.color.vermillion))
            }

            holder.itemView.ll_wrapper.isEnabled = true
            holder.itemView.ll_wrapper.isClickable = true
        }

        if (list.img == null) {
            GlideApp.with(context)
                .load(R.drawable.logo_bulat)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(holder.itemView.iv_photo)
        } else {
            GlideApp.with(context)
                .load(list.img)
                .error(R.drawable.logo_bulat)
                .placeholder(R.drawable.logo_bulat)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(holder.itemView.iv_photo)
        }

        holder.itemView.ll_wrapper.setOnClickListener {
            if(checkStock){
                if("0" == list.have_stock) onClick.onItemClick(list, position)
                else{
                    val stok = list.stock!!.toDouble()
                    if (stok > 0) onClick.onItemClick(list, position)
                    else Toast.makeText(context,"Out of stock. Please add stock first", Toast.LENGTH_SHORT).show()
                }
            }
            else onClick.onItemClick(list, position)
        }
    }

    override fun getItemCount() = lists.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnProductClickListener{ fun onItemClick(item: Product, position: Int) }
}