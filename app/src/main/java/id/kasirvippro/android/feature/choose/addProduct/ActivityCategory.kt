package id.kasirvippro.android.feature.choose.addProduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.kasirvippro.android.R
import id.kasirvippro.android.models.newProduct.Category
import id.kasirvippro.android.utils.AppSession
import kotlinx.android.synthetic.main.activity_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ActivityCategory : AppCompatActivity() {
    private val appSession = AppSession()
    private lateinit var key: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setupToolbar()
        key = appSession.getToken(this)!!

        category.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        category.setHasFixedSize(true)
        Api.client().getNoCategory(key).enqueue(object: Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {
                if(response.isSuccessful){
                    category.adapter = CategoryAdapter(response.body()!!.data, key)
                }
                 else Toast.makeText(this@ActivityCategory, response.code().toString(), Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {
                when (t) {
                    is UnknownHostException -> Toast.makeText(this@ActivityCategory, "Network Error...Please try again", Toast.LENGTH_LONG).show()
                    is SocketTimeoutException -> Toast.makeText(this@ActivityCategory, "A connection timeout occurred", Toast.LENGTH_LONG).show()
                    else -> {
                        //error
                    }
                }
            }

        })
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "${getString(R.string.lbl_category_title)}"
            elevation = 0f
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }
}