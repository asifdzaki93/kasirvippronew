package id.kasirvippro.android.feature.manageOrder.kitchen.transactionSuccess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.base.BaseFragment
import id.kasirvippro.android.R
import id.kasirvippro.android.events.onReloadTransaction
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.feature.transaction.detail.DetailActivity
import id.kasirvippro.android.models.FilterDialogDate
import kotlinx.android.synthetic.main.fragment_dataorder_transaction_success.view.rv_list
import kotlinx.android.synthetic.main.fragment_dataorder_transaction_success.view.sw_refresh
import kotlinx.android.synthetic.main.fragment_dataorder_transaction_success.view.tv_error
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class TransactionSuccessFragment : BaseFragment<TransactionSuccessPresenter, TransactionSuccessContract.View>(),
    TransactionSuccessContract.View {

    private lateinit var _view: View
    val adapter = TransactionSuccessAdapter()
    private val openFilter = 1100

    companion object {


        @JvmStatic
        fun newInstance() =
                TransactionSuccessFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun createPresenter(): TransactionSuccessPresenter {
        return TransactionSuccessPresenter(activity as Context, this)
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_dataorder_transaction_success, container, false)
    }


    override fun initAction(view: View) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        _view = view
        renderView()
        _view.sw_refresh.isRefreshing = true
        getPresenter()?.onViewCreated()
    }

    private fun renderView(){
        _view.sw_refresh.setOnRefreshListener {
            reloadData()
        }

        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        _view.rv_list.layoutManager = layoutManager
        _view.rv_list.adapter = adapter

        adapter.callback = object : TransactionSuccessAdapter.ItemClickCallback{
            override fun onClick(data: Transaction) {
                openDetail(data.no_invoice!!)
            }
        }

    }

    override fun reloadData() {
        _view.sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        getPresenter()?.loadTransaction()
    }

    override fun onDetach() {
        super.onDetach()
        getPresenter()?.onDestroy()
        EventBus.getDefault().unregister(this)
    }



    override fun setList(list: List<Transaction>) {
        _view.sw_refresh.isRefreshing = false
        _view.rv_list.visibility = View.VISIBLE
        _view.tv_error.visibility = View.GONE
        adapter.setItems(list)
    }

    override fun showErrorMessage(code: Int, msg: String) {
        _view.sw_refresh.isRefreshing = false
        when (code) {
            RestException.CODE_USER_NOT_FOUND -> restartLoginActivity()
            RestException.CODE_MAINTENANCE -> openMaintenanceActivity()
            RestException.CODE_UPDATE_APP -> openUpdateActivity()
            else -> {
                _view.rv_list.visibility = View.GONE
                _view.tv_error.visibility = View.VISIBLE
                _view.tv_error.text = msg
            }
        }
    }

    override fun openDetail(id: String) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
    }

    @Subscribe
    fun onEvent(event: onReloadTransaction){
        if(event.isReload){
            reloadData()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == openFilter){
            val filter = data?.getParcelableExtra(AppConstant.DATA) as FilterDialogDate?
            adapter.clearAdapter()
            _view.sw_refresh.isRefreshing = true
        }
    }





}
