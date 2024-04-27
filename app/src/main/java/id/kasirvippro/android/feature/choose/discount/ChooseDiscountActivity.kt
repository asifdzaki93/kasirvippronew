package id.kasirvippro.android.feature.choose.discount

import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_choose_discount.*

class ChooseDiscountActivity : BaseActivity<ChooseDiscountPresenter, ChooseDiscountContract.View>(),
    ChooseDiscountContract.View {

    val adapter = ChooseDiscountAdapter()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun createPresenter(): ChooseDiscountPresenter {
        return ChooseDiscountPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_choose_discount
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView() {
        sw_refresh.setOnRefreshListener {
            scrollListener.resetState()
            reloadData()
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onFirstItemVisible(isFirstItem: Boolean) {
                sw_refresh.isEnabled = isFirstItem && adapter.itemCount > 0

            }

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {

            }
        }
        rv_list.addOnScrollListener(scrollListener)

        adapter.callback = object : ChooseDiscountAdapter.ItemClickCallback{
            override fun onClick(data: Discount) {
                onSelected(data)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Discount"
            elevation = 0f
        }

    }

    override fun setData(list: List<Discount>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun showErrorMessage(code: Int, msg: String?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                msg?.let {
                    toast(this,it)}
                }

        }

    }

    override fun showSuccessMessage(msg: String?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        msg?.let {
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }
        reloadData()

    }

    override fun reloadData() {
        sw_refresh.isRefreshing = true
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = this.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        adapter.clearAdapter()
        val dataManager = DataManager (this)
        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    getPresenter()?.loadData()
                    dataManager.clearDiscountAll()
                }else{
                    var allDiscount = dataManager.allDiscount()

                    var discountList = mutableListOf<Discount>()
                    for (item in allDiscount){
                        var discount = Discount();
                        discount.set(item.key,item.id_discount,item.name_discount,
                            item.note,item.type,
                            item.nominal
                        )
                        discountList.add(discount)
                    }
                    getPresenter()?.setDiscount(discountList);
                }
            }else{
                var allDiscount = dataManager.allDiscount()

                var discountList = mutableListOf<Discount>()
                for (item in allDiscount){
                    var discount = Discount();
                    discount.set(item.key,item.id_discount,item.name_discount,
                        item.note,item.type,
                        item.nominal
                    )
                    discountList.add(discount)
                }
                getPresenter()?.setDiscount(discountList);
            }
        }else{
            var allDiscount = dataManager.allDiscount()

            var discountList = mutableListOf<Discount>()
            for (item in allDiscount){
                var discount = Discount();
                discount.set(item.key,item.id_discount,item.name_discount,
                    item.note,item.type,
                    item.nominal
                )
                discountList.add(discount)
            }
            getPresenter()?.setDiscount(discountList);
        }
    }

    override fun onSelected(data: Discount) {
        val newintent: Intent = intent
        newintent.putExtra(AppConstant.DATA,data)
        setResult(RESULT_OK,newintent)
        finish()
    }



}
