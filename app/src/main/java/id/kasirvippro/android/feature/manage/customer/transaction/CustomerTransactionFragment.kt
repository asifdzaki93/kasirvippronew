package id.kasirvippro.android.feature.manage.customer.transaction

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
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.feature.transaction.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_customer_transaction.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class CustomerTransactionFragment : BaseFragment<CustomerTransactionPresenter, CustomerTransactionContract.View>(),
    CustomerTransactionContract.View {

    private val ARGUMENT_PARAM = "ARGUMENT_PARAM"

    private lateinit var _view: View

    val adapter = CustomerTransactionAdapter()


    companion object {

        @JvmStatic
        fun newInstance(data : Customer?) =
            CustomerTransactionFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARGUMENT_PARAM, data)
                    }
                }
    }

    override fun createPresenter(): CustomerTransactionPresenter {
        return CustomerTransactionPresenter(
            activity as Context,
            this
        )
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_customer_transaction, container, false)
    }


    override fun initAction(view: View) {
        EventBus.getDefault().register(this)
        _view = view
        renderView()
        var data:Customer? = null
        if (arguments != null) {
            data = arguments?.getSerializable(ARGUMENT_PARAM) as Customer
        }
        getPresenter()?.onViewCreated(data)
    }

    private fun renderView(){
        _view.sw_refresh.setOnRefreshListener {
            _view.sw_refresh.isRefreshing = true
            adapter.clearAdapter()
            getPresenter()?.loadTransactions()
        }
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        _view.rv_list.layoutManager = layoutManager
        _view.rv_list.adapter = adapter

        adapter.callback = object : CustomerTransactionAdapter.ItemClickCallback{
            override fun onClick(data: Transaction) {
                openDetail(data.no_invoice!!)
            }
        }

    }

    override fun setData(list: List<Transaction>) {
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

    override fun onDetach() {
        super.onDetach()
        getPresenter()?.onDestroy()
        EventBus.getDefault().unregister(this)

    }

    override fun openDetail(id: String) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)
    }

    @Subscribe
    fun onEvent(event:onReloadTransaction){
        if(event.isReload){
            _view.sw_refresh.isRefreshing = true
            adapter.clearAdapter()
            getPresenter()?.loadTransactions()
        }
    }


}
