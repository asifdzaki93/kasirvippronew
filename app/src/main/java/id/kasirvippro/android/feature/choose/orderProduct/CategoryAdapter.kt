package id.kasirvippro.android.feature.choose.orderProduct

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.newProduct.CategoryData
import id.kasirvippro.android.models.newProduct.SubCategory
import kotlinx.android.synthetic.main.item_category.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CategoryAdapter(private val lists: List<CategoryData>, private val key: String) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var selected = -1
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = lists[position]

            holder.itemView.sub.visibility = View.VISIBLE


        Api.client().getSubCategory(key, list.id_category).enqueue(object : Callback<SubCategory>{
            override fun onResponse(call: Call<SubCategory>, response: Response<SubCategory>) {
                if(response.isSuccessful){
                    holder.itemView.sub.layoutManager = GridLayoutManager(context, 1)
                    holder.itemView.sub.adapter = SubCategoryAdapter(response.body()!!.data)
                }
            }

            override fun onFailure(call: Call<SubCategory>, t: Throwable) {
                when (t) {
                    is UnknownHostException -> Toast.makeText(context, "Network Error...Please try again", Toast.LENGTH_LONG).show()
                    is SocketTimeoutException -> Toast.makeText(context, "A connection timeout occurred", Toast.LENGTH_LONG).show()
                    else -> {
                        //error
                    }
                }
            }

        })
        holder.itemView.setOnClickListener {
            if(selected >= 0) notifyItemChanged(selected)
            selected = position
            notifyItemChanged(selected)
        }
    }

    override fun getItemCount() = lists.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}