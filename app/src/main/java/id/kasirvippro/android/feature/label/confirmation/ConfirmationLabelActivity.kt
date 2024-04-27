package id.kasirvippro.android.feature.label.confirmation


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.transaction.successLabel.SuccessLabelActivity
import id.kasirvippro.android.models.cart.Cart
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import kotlinx.android.synthetic.main.activity_label_confirmation.*

class ConfirmationLabelActivity : BaseActivity<ConfirmationLabelPresenter,ConfirmationLabelContract.View>(), ConfirmationLabelContract.View{

    private val TAG = ConfirmationLabelActivity::class.java.simpleName

    private val adapter = ConfirmationLabelAdapter()

    override fun createPresenter(): ConfirmationLabelPresenter {
        return ConfirmationLabelPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_label_confirmation
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Label Confirmation"
            elevation = 0f
        }

    }

    private fun renderView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        btn_process.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.checkLabel()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
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
    override fun openSuccessPage(id: String) {
        val intent = Intent(this, SuccessLabelActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
    }

    override fun setCart(list: List<Cart>) {
        adapter.setItems(list)
    }

}
