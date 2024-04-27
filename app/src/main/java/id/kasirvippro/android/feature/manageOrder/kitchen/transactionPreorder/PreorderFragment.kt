package id.kasirvippro.android.feature.manageOrder.kitchen.transactionPreorder

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
import id.kasirvippro.android.base.BaseFragment
import id.kasirvippro.android.R
import id.kasirvippro.android.events.onReloadTransaction
import id.kasirvippro.android.feature.filterDate.main.MainActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.models.transaction.Transaction
import kotlinx.android.synthetic.main.fragment_dataorder_transaction.view.*
import id.kasirvippro.android.feature.transaction.detail.DetailActivity
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import kotlinx.android.synthetic.main.fragment_dataorder_transaction.view.rv_list
import kotlinx.android.synthetic.main.fragment_dataorder_transaction.view.sw_refresh
import kotlinx.android.synthetic.main.fragment_dataorder_transaction.view.tv_error
import kotlinx.android.synthetic.main.fragment_dataorder_preorder.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class PreorderFragment : BaseFragment<PreorderPresenter, PreorderContract.View>(),
    PreorderContract.View {

    private lateinit var _view: View
    val adapter = PreorderAdapter()
    private var listener: Listener? = null
    private val openFilter = 1100

    companion object {


        @JvmStatic
        fun newInstance() =
                PreorderFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun createPresenter(): PreorderPresenter {
        return PreorderPresenter(activity as Context, this)
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_dataorder_preorder, container, false)
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

        adapter.callback = object : PreorderAdapter.ItemClickCallback{
            override fun onClick(data: Transaction) {
                openDetail(data.no_invoice!!)
            }
        }

        _view.btn_date.setOnClickListener {
            openFilter(getPresenter()?.getFilterDateSelected())
        }

        _view.et_search.addTextChangedListener(object: TextWatcher {
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

    override fun reloadData() {
        _view.sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        getPresenter()?.loadTransaction()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement Listener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        getPresenter()?.onDestroy()
        EventBus.getDefault().unregister(this)
        listener = null
    }


    interface Listener {
        fun openFilterByStatusDialog(title:String, list: List<DialogModel>, selected: DialogModel?, type:Int)
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

    override fun onFilterStatusSelected(data: DialogModel?) {
        adapter.clearAdapter()
        _view.sw_refresh.isRefreshing = true
        getPresenter()?.onChangeStatus(data)
    }

    override fun openFilter(data: FilterDialogDate?) {
        val intent = Intent(activity!!, MainActivity::class.java)
        intent.putExtra(AppConstant.DATA,data)
        startActivityForResult(intent,openFilter)
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
            getPresenter()?.setFilterDateSelected(filter)
        }
    }





}
