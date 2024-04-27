package id.kasirvippro.android.feature.choose.addProduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.newProduct.Category
import id.kasirvippro.android.models.newProduct.ProductNew
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.AppSession
import kotlinx.android.synthetic.main.activity_product_new.*
import kotlinx.android.synthetic.main.layout_empty.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ActivityProductNew : AppCompatActivity() {
    private val appSession = AppSession()
    private lateinit var key: String
    private val productList = mutableListOf<Product>()
    private lateinit var adapter: RecyclerView.Adapter<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_new)
        key = appSession.getToken(this)!!
        val page = PageAdapter(supportFragmentManager)
        Api.client().getCategory(key).enqueue(object: Callback<Category>{
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                page.addWithTitle(ProductOf().putExtra(""), "All Product")
                for(i in response.body()!!.data.indices) page.addWithTitle(ProductOf().putExtra(response.body()!!.data[i].id_category), response.body()!!.data[i].name_category)
                viewPager.adapter = page
                tabLayout.setupWithViewPager(viewPager)
                viewPager.offscreenPageLimit = response.body()!!.data.size
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                when (t) {
                    is UnknownHostException -> Toast.makeText(this@ActivityProductNew, "Network Error...Please try again", Toast.LENGTH_LONG).show()
                    is SocketTimeoutException -> Toast.makeText(this@ActivityProductNew, "A connection timeout occurred", Toast.LENGTH_LONG).show()
                    else -> {
                        //error
                    }
                }
            }
        } )

        sw_refresh.isEnabled = false
        emptyX.title.text = "Product not available"
        emptyX.subtitle.text = "Sorry, this category is still empty"
        emptyX.visibility = View.GONE

        //val layoutManager = GridLayoutManager(this, 4)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_listX.layoutManager = layoutManager
        adapter = ProductAdapter(productList, object : ProductAdapter.OnProductClickListener{
            override fun onItemClick(item: Product, position: Int) {
                val newIntent = intent
                newIntent.putExtra(AppConstant.DATA, item)
                setResult(RESULT_OK, newIntent)
                finish()
            }
        })

        rv_listX.adapter = adapter
    }

    fun Fragment.putExtra(bundle: String): Fragment {
        val wd = this
        val args = Bundle()
        args.putString("data", bundle)
        wd.arguments = args
        return this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu?.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.lbl_search_product)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener  {

            override fun onQueryTextChange(newText: String): Boolean {
                productList.clear()
                sw_refresh.isRefreshing = true
                Api.client().chooseSearch(key, newText).enqueue(object : Callback<ProductNew>{
                    override fun onResponse(call: Call<ProductNew>, response: Response<ProductNew>) {
                        sw_refresh.isRefreshing = false
                        if(response.body()!!.data.isNullOrEmpty()) emptyX.visibility = View.VISIBLE
                        else {
                            emptyX.visibility = View.GONE
                            productList.addAll(response.body()!!.data!!)
                            adapter.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<ProductNew>, t: Throwable) {
                        sw_refresh.isRefreshing = false
                        when (t) {
                            is UnknownHostException -> Toast.makeText(this@ActivityProductNew, "Network Error...Please try again", Toast.LENGTH_LONG).show()
                            is SocketTimeoutException -> Toast.makeText(this@ActivityProductNew, "A connection timeout occurred", Toast.LENGTH_LONG).show()
                            else -> {
                                //error
                            }
                        }
                    }

                })
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })

        menuItem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                parentLayout.visibility = View.GONE
                rv_listX.visibility = View.VISIBLE
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                parentLayout.visibility = View.VISIBLE
                emptyX.visibility = View.GONE
                rv_listX.visibility = View.GONE
                return true
            }

        })

        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
            R.id.action_category -> { startActivity(Intent(this, ActivityCategory::class.java)) }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.menu_choose_product)
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

}