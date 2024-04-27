package id.kasirvippro.android.feature.hutangpiutang.detailPiutang

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.models.hutangpiutang.DetailPiutang
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_piutang_detail.*


class DetailPiutangActivity : BaseActivity<DetailPiutangPresenter, DetailPiutangContract.View>(), DetailPiutangContract.View {

    val adapter = DetailPiutangAdapter()


    override fun createPresenter(): DetailPiutangPresenter {
        return DetailPiutangPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_piutang_detail
    }

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
    }

    private fun renderView(){
        setupToolbar()
        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {

                var title = ""
                getPresenter()?.getTitleName()?.let {
                    title = it
                }
                ctl.title = title

            } else {
                ctl.title = ""

            }
        })
        ctl.title = ""
        sw_refresh.isRefreshing = true
        sw_refresh.setOnRefreshListener {
            reloadData()
        }

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }

    override fun setCustomer(name: String?, email: String?, phone: String?, address: String?) {
        name?.let {
            tv_name.text = it
            tv_initial.text = Helper.getInisialName(it)
        }

        tv_email.visibility = View.GONE
        email?.let {
            tv_email.text = it
            tv_email.visibility = View.VISIBLE
        }

        tv_phone.visibility = View.GONE
        phone?.let {
            tv_phone.text = it
            tv_phone.visibility = View.VISIBLE
        }

        tv_address.visibility = View.GONE
        address?.let {
            tv_address.text = it
            tv_address.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun showMessage(code: Int, msg: String?) {
        sw_refresh.isRefreshing = false
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

    private fun reloadData() {
        sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        getPresenter()?.loadDetailCustomer()
    }

    override fun setPiutang(tagihan: String, piutang: String, total: String, jatuhTempo: String) {
        sw_refresh.isRefreshing = false
        tv_tagihan.text = tagihan
        tv_piutang.text = piutang
        tv_total.text = total
        tv_jatuh_tempo.text = jatuhTempo
    }

    override fun setList(list: List<DetailPiutang.Data>) {
        adapter.setItems(list)
    }

}
