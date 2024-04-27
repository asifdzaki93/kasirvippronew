package id.kasirvippro.android.feature.choose.newProduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import id.kasirvippro.android.R
import id.kasirvippro.android.models.newProduct.CategoryData
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.newProduct.ProductNew
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import kotlinx.android.synthetic.main.activity_sub_category.*
import kotlinx.android.synthetic.main.activity_sub_category.emptyX
import kotlinx.android.synthetic.main.layout_empty.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ActivitySubCategory : AppCompatActivity() {
    private val appSession = AppSession()
    private lateinit var key: String
    private val productList = mutableListOf<Product>()
    private lateinit var adapter: RecyclerView.Adapter<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)
        val data = Gson().fromJson(intent.getStringExtra("data"), CategoryData::class.java)
        setupToolbar(data.name_category)
        key = appSession.getToken(this)!!

        emptyX.title.text = "Product not available"
        emptyX.subtitle.text = "Sorry, Category ${data.name_category} is still empty"

        //val layoutManager = GridLayoutManager(this,4)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        subCategory.layoutManager = layoutManager
        adapter = ProductAdapter(productList, object : ProductAdapter.OnProductClickListener{
            override fun onItemClick(item: Product, position: Int) {
                val newIntent = intent
                newIntent.putExtra(AppConstant.DATA, item)
                setResult(RESULT_OK, newIntent)
                finish()
            }
        })

        subCategory.adapter = adapter
        Api.client().getProductBySubCategory(key, data.id_category).enqueue(object : Callback<ProductNew> {
            override fun onResponse(call: Call<ProductNew>, response: Response<ProductNew>) {
                if(response.body()!!.data.isNullOrEmpty()) emptyX.visibility = View.VISIBLE
                else {
                    productList.clear()
                    emptyX.visibility = View.GONE
                    productList.addAll(response.body()!!.data!!)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ProductNew>, t: Throwable) {
                when (t) {
                    is UnknownHostException -> Toast.makeText(this@ActivitySubCategory, "Network Error...Please try again", Toast.LENGTH_LONG).show()
                    is SocketTimeoutException -> Toast.makeText(this@ActivitySubCategory, "A connection timeout occurred", Toast.LENGTH_LONG).show()
                    else -> {
                        //error
                    }
                }
            }

        })
    }

    private fun setupToolbar(string: String) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = string
            elevation = 0f
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_category, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.action_category -> { startActivity(Intent(this, ActivityCategory::class.java)) }
        }
        return super.onOptionsItemSelected(item!!)
    }
}