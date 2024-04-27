package id.kasirvippro.android.feature.afiliate.transaction

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.fragment_history_commision.view.*
import id.kasirvippro.android.feature.filterDate.main.MainActivity
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.models.transaction.DetailCOmmision
import id.kasirvippro.android.utils.AppSession
import id.kasirvippro.android.utils.Helper
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class TransactionFragment : BaseFragment<TransactionPresenter, TransactionContract.View>(),
    TransactionContract.View {

    private lateinit var _view: View
    val adapter = TransactionAdapter()
    private var listener: Listener? = null
    private val openFilter = 1100

    companion object {


        @JvmStatic
        fun newInstance() =
                TransactionFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun createPresenter(): TransactionPresenter {
        return TransactionPresenter(activity as Context, this)
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_history_commision, container, false)
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

        adapter.callback = object : TransactionAdapter.ItemClickCallback{
            override fun onClick(data: DetailCOmmision) {
            }
        }

        _view.btn_date.setOnClickListener {
            openFilter(getPresenter()?.getFilterDateSelected())
        }


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

    override fun setList(list: List<DetailCOmmision>) {
        _view.sw_refresh.isRefreshing = false
        _view.rv_list.visibility = View.VISIBLE
        _view.tv_error.visibility = View.GONE
        adapter.setItems(list)
    }

    @SuppressLint("SetTextI18n")
    override fun setCommision(totalcommision: String?) {
        val appSession = AppSession()
        val currency = appSession.getCurrency(context!!)
        val decimal = appSession.getDecimal(context!!)
        if(decimal=="No Decimal") {
            _view.total_commision.text = "Commision Pending : " + currency + Helper.convertToCurrency(totalcommision!!)
        }else{
            _view.total_commision.text = "Commision Pending : " + currency + totalcommision!!
        }
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

    @Subscribe
    fun onEvent(event: onReloadTransaction){
        if(event.isReload){
            reloadData()
        }
    }

    override fun onFilterStatusSelected(data: DialogModel?) {
        adapter.clearAdapter()
        _view.sw_refresh.isRefreshing = true
        getPresenter()?.onChangeStatus(data)
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
