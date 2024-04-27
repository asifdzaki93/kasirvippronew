package id.kasirvippro.android.feature.choose.addProduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.newProduct.ProductNew
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import kotlinx.android.synthetic.main.fragment_product_of.*
import kotlinx.android.synthetic.main.layout_empty.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ProductOf : Fragment() {

    private val appSession = AppSession()
    private  lateinit var key: String
    private lateinit var data: String
    private val productList = mutableListOf<Product>()
    private lateinit var adapter: RecyclerView.Adapter<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_of, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        key = appSession.getToken(requireContext())!!
        data = arguments?.getString("data")!!

        //val layoutManager = GridLayoutManager(requireContext(), 4)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager

        adapter = ProductAdapter(productList, object : ProductAdapter.OnProductClickListener{
            override fun onItemClick(item: Product, position: Int) {
                val newIntent = activity!!.intent
                newIntent.putExtra(AppConstant.DATA, item)
                activity!!.setResult(AppCompatActivity.RESULT_OK, newIntent)
                activity!!.finish()
            }
        })
        rv_list.adapter = adapter

        empty.title.text = "Product not available"
        empty.subtitle.text = "Sorry, this category is still empty"

        init()

        refresh.setOnRefreshListener {
            refresh.isRefreshing = true
            empty.visibility = View.GONE
            init()
        }

    }

    private fun init(){
        rv_list.bringToFront()
        rv_list.invalidate()
        refresh.isRefreshing = true
        Api.client().getProductByCategory(key, data).enqueue(object : Callback<ProductNew>{
            override fun onResponse(call: Call<ProductNew>, response: Response<ProductNew>) {
                refresh.isRefreshing = false
                if(response.body()!!.data.isNullOrEmpty()) empty.visibility = View.VISIBLE
                else{
                    productList.clear()
                    empty.visibility = View.GONE
                    productList.addAll(response.body()!!.data!!)
                    adapter.notifyDataSetChanged()

                    println("Kategori $data = ${response.body()!!.data!!.size}")
                }
            }

            override fun onFailure(call: Call<ProductNew>, t: Throwable) {
                refresh.isRefreshing = false
                when (t) {
                    is UnknownHostException -> Toast.makeText(requireContext(), "Network Error...Please try again", Toast.LENGTH_LONG).show()
                    is SocketTimeoutException -> Toast.makeText(requireContext(), "A connection timeout occurred", Toast.LENGTH_LONG).show()
                    else -> {
                        //error
                    }
                }
            }

        })
    }

}