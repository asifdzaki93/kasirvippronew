package id.kasirvippro.android.feature.manage.discount.list

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import kotlinx.android.synthetic.main.activity_list_discount.*
import id.kasirvippro.android.feature.manage.discount.add.AddActivity
import id.kasirvippro.android.feature.manage.discount.edit.EditActivity
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_list_discount.rv_list
import kotlinx.android.synthetic.main.activity_list_discount.sw_refresh


class DiscountListActivity : BaseActivity<DiscountListPresenter, DiscountListContract.View>(),
    DiscountListContract.View {

    private val codeOpenAdd = 1001
    private val codeOpenEdit = 1002

    val adapter = DiscountListAdapter()
    var list2 = arrayListOf<Discount>()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    override fun createPresenter(): DiscountListPresenter {
        return DiscountListPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_list_discount
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated()
    }

    public fun hideLoading(){
        hideLoadingDialog()
    }

    private fun renderView() {
        sw_refresh.isRefreshing = true
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

        adapter.callback = object : DiscountListAdapter.ItemClickCallback{
            override fun onClick(data: Discount) {
                openEditPage(data)
            }

            override fun onDelete(data: Discount,position :Int) {
                showLoadingDialog()
                getPresenter()?.deleteDiscount(data.id_discount!!,position,data.inc!!)
            }
        }


        btn_add.setOnClickListener {
            openAddPage()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu)
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
            title = getString(R.string.lbl_manage_discount_title)
            elevation = 0f
        }

    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun setData(list: List<Discount>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    public fun setList(){
        adapter.clearAdapter()
        adapter.setItems(list2)
        adapter.notifyDataSetChanged()
    }

    override fun setDiscounts(list: List<Discount>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        list2 = list as ArrayList<Discount>
        adapter.setItems(list)
    }

    override fun showErrorMessage(code: Int, msg: String) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> toast(this,msg)
        }

    }

    override fun showSuccessMessage(msg: String?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        msg?.let {
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
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
                    getPresenter()?.loadDiscounts()
                    dataManager.clearDiscountAll()
                    return
                }else{
                    var alldiscount = dataManager.allDiscount()
                    var discountList = mutableListOf<Discount>()
                    for (item in alldiscount){
                        var discount = Discount();
                        discount.set(
                            item.id_discount,
                            item.name_discount,
                            item.note,
                            item.type,
                            item.nominal,
                            item.increment
                        )
                        discountList.add(discount)
                    }
                    getPresenter()?.setDiscount(discountList);
                }
            }else{
                var alldiscount = dataManager.allDiscount()

                var discountList = mutableListOf<Discount>()
                for (item in alldiscount){
                    var discount = Discount();
                    discount.set(
                        item.id_discount,
                        item.name_discount,
                        item.note,
                        item.type,
                        item.nominal,
                        item.increment
                    )
                    discountList.add(discount)
                }
                getPresenter()?.setDiscount(discountList);
            }
        }else{
            var alldiscount = dataManager.allDiscount()

            var discountList = mutableListOf<Discount>()
            for (item in alldiscount){
                var discount = Discount();
                discount.set(
                    item.id_discount,
                    item.name_discount,
                    item.note,
                    item.type,
                    item.nominal,
                    item.increment
                )
                discountList.add(discount)
            }
            getPresenter()?.setDiscount(discountList);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            reloadData()
        }
    }

    override fun openAddPage() {
        val intent = Intent(this,AddActivity::class.java)
        startActivityForResult(intent,codeOpenAdd)
    }

    override fun openEditPage(data: Discount?) {
        val intent = Intent(this,EditActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,codeOpenEdit)
    }


}
