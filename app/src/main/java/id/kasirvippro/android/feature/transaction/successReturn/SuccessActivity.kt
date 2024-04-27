package id.kasirvippro.android.feature.transaction.successReturn

import android.app.Service
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.transaction.detail.DetailActivity
import id.kasirvippro.android.feature.transaction.detailReturn.DetailReturnActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.sqlite.Model.SalesDataSQL
import id.kasirvippro.android.sqlite.Model.SalesSQL
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_transaction_success.*

class SuccessActivity : BaseActivity<SuccessPresenter, SuccessContract.View>(), SuccessContract.View {

    private var sales : ArrayList<SalesSQL> = ArrayList()
    private lateinit var salesdata : SalesDataSQL

    override fun createPresenter(): SuccessPresenter {
        return SuccessPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_transaction_success
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView() {
        sw_refresh.isRefreshing = false
        sw_refresh.setOnRefreshListener {
            reloadData()
        }

        btn_end.setOnClickListener {
            onClose()
        }

    }

    override fun showMessage(code: Int, msg: String?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                onErrorView(msg.toString())
            }

        }

    }

    private fun reloadData() {
        sw_refresh.isRefreshing = true
        val intent = Intent(this, DetailActivity::class.java)
        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = this.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    getPresenter()?.loadDetail()
                }
            }
        }

    }

    fun set(sales : ArrayList<SalesSQL>, salesdata : SalesDataSQL){
        this.sales = sales
        this.salesdata = salesdata

        if (sales.size > 0){
            tv_id.text = salesdata.no_invoice
            tv_date.text = salesdata.date
        }
    }

    override fun onClose() {
        val intent = Intent(this, DetailReturnActivity::class.java)

        if (sales.size > 0){
            intent.putExtra(AppConstant.sales, sales)
            intent.putExtra(AppConstant.salesData,salesdata)
        }

        intent.putExtra(AppConstant.CODE, AppConstant.Code.CODE_TRANSACTION_CUSTOMER)
        intent.putExtra(AppConstant.DATA,getPresenter()?.getInvoice())
        intent.putExtra(AppConstant.MAIN,true)
        startActivity(intent)
    }

    override fun onSuccess(date:String,id:String) {
        tv_id.text = id
        tv_date.text = date
        onSuccessView()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onBackPressed() {
        onClose()
    }

    override fun onErrorView(msg: String) {
        sw_refresh.isRefreshing = false
        fl_content.visibility = View.GONE
        ll_error.visibility = View.VISIBLE
        tv_error.text = msg
    }

    override fun onSuccessView() {
        sw_refresh.isRefreshing = false
        fl_content.visibility = View.VISIBLE
        ll_error.visibility = View.GONE
    }

}
