package id.kasirvippro.android.feature.transaction.successadd

import android.content.Intent
import android.os.Bundle
import android.view.View
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.transaction.detailSplit.DetailActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_transaction_successadd.*

class SuccessAddActivity : BaseActivity<SuccessAddPresenter, SuccessAddContract.View>(), SuccessAddContract.View {

    override fun createPresenter(): SuccessAddPresenter {
        return SuccessAddPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_transaction_successadd
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
        getPresenter()?.loadDetail()
    }

    override fun onClose() {
        val intent = Intent(this, DetailActivity::class.java)
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
