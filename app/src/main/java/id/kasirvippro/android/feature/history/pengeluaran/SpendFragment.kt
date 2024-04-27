package id.kasirvippro.android.feature.history.pengeluaran

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseFragment
import id.kasirvippro.android.events.onReloadSpendTransaction
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.feature.transaction.detailSpend.DetailSpendActivity
import id.kasirvippro.android.feature.filterDate.main.MainActivity
import id.kasirvippro.android.models.FilterDialogDate
import kotlinx.android.synthetic.main.fragment_history_spend.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class SpendFragment : BaseFragment<SpendPresenter, SpendContract.View>(),
    SpendContract.View{

    private lateinit var _view: View
    private val openFilter = 1100

    val adapter = SpendAdapter()

    companion object {


        @JvmStatic
        fun newInstance() =
            SpendFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun createPresenter(): SpendPresenter {
        return SpendPresenter(activity as Context, this)
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_history_spend,container, false)
    }

    override fun initAction(view: View) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        _view = view
        renderView()
        getPresenter()?.onViewCreated()
    }

    private fun renderView() {

        _view.sw_refresh.setOnRefreshListener {
            reloadData()
        }

        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        _view.rv_list.layoutManager = layoutManager
        _view.rv_list.adapter = adapter

        adapter.callback = object : SpendAdapter.ItemClickCallback {
            override fun onClick(data: Transaction) {
                openDetail(data.no_invoice!!)
            }
        }

        _view.btn_date.setOnClickListener {
            openFilter(getPresenter()?.getFilterDateSelected())
        }

        _view.et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                adapter.clearAdapter()
                _view.sw_refresh.isRefreshing = true
                getPresenter()?.onSearch(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }


    override fun onDetach() {
        super.onDetach()
        getPresenter()?.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun reloadData() {
        _view.sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        getPresenter()?.loadTransaction()
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
        val intent = Intent(activity, DetailSpendActivity::class.java)
        intent.putExtra(AppConstant.DATA, id)
        startActivity(intent)
    }

    @Subscribe
    fun onEvent(event: onReloadSpendTransaction){
        if(event.isReload){
            reloadData()
        }
    }

    override fun openFilter(data: FilterDialogDate?) {
        val intent = Intent(activity!!,MainActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,openFilter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == openFilter){
            val filter = data?.getParcelableExtra(AppConstant.DATA) as FilterDialogDate?
            adapter.clearAdapter()
            _view.sw_refresh.isRefreshing = true
            getPresenter()?.setFilterDateSelected(filter)
        }
    }



}
