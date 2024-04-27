package id.kasirvippro.android.feature.choose.orderProduct

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import id.kasirvippro.android.R
import id.kasirvippro.android.feature.order.main.OrderActivity
import id.kasirvippro.android.models.newProduct.SubCategoryData
import kotlinx.android.synthetic.main.item_sub_category.view.*

class SubCategoryAdapter(private val lists: List<SubCategoryData>) : RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sub_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = lists[position]
        holder.itemView.text.text = list.name_category

        holder.itemView.setOnClickListener {
            OrderActivity.data = Gson().toJson(list)
                context.startActivity(Intent(context, OrderActivity::class.java))
        }

    }

    override fun getItemCount() = lists.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}