package id.kasirvippro.android.feature.shift.closingShift.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.printer.PrinterActivity
import id.kasirvippro.android.feature.shift.closingShift.detail.ClosingShiftDetailActivity
import id.kasirvippro.android.models.closeShift.CloseShift
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.EndlessRecyclerViewScrollListener
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_list_shift.*
import kotlinx.android.synthetic.main.activity_list_shift.btn_daily_close

class ClosingShiftListActivity : BaseActivity<ClosingShiftListPresenter, ClosingShiftListContract.View>(),
    ClosingShiftListContract.View {

    val adapter = ClosingShiftListAdapter()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private val CODE_OPEN_ADD = 101
    private val CODE_OPEN_EDIT = 102

    override fun createPresenter(): ClosingShiftListPresenter {
        return ClosingShiftListPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_list_shift
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

        btn_daily_close.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.dailyClose()
        }

       // btn_printer.setOnClickListener {
        //   getPresenter()?.onCheckBluetooth()
        //}


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

        adapter.callback = object : ClosingShiftListAdapter.ItemClickCallback{
            override fun onClick(data: CloseShift) {
                openEditPage(data)
            }


        }
    }

    override fun onClose(status: Int) {
        val intent = Intent(this, ClosingShiftListActivity::class.java)
        startActivity(intent)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
     //   menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> restartMainActivity()
        }
        return super.onOptionsItemSelected(item!!)
    }


    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "INITIAL"
            elevation = 0f
        }

    }

    override fun setData(list: List<CloseShift>) {
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

    override fun showMessage(code: Int, msg: String?) {
        hideLoadingDialog()
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
        adapter.clearAdapter()
        getPresenter()?.loadData()
    }


    override fun openEditPage(data: CloseShift) {
        val intent = Intent(this,ClosingShiftDetailActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,CODE_OPEN_EDIT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            reloadData()
        }
    }

    override fun openPrinterPage() {
        val intent = Intent(this, PrinterActivity::class.java)
        intent.putExtra(AppConstant.DATA,getPresenter()?.getDataStruk())
        startActivity(intent)
    }



}
