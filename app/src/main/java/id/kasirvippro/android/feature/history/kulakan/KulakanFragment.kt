package id.kasirvippro.android.feature.history.kulakan

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
import id.kasirvippro.android.events.onReloadTransaction
import id.kasirvippro.android.models.transaction.Transaction
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.feature.transaction.detail.DetailActivity
import id.kasirvippro.android.feature.filterDate.main.MainActivity
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import kotlinx.android.synthetic.main.activity_report_kulakan.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class KulakanFragment : BaseFragment<KulakanPresenter, KulakanContract.View>(),
    KulakanContract.View{

    private lateinit var _view: View
    private var listener: Listener? = null
    private val openFilter = 1100

    val adapter = KulakanAdapter()

    companion object {


        @JvmStatic
        fun newInstance() =
            KulakanFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun createPresenter(): KulakanPresenter {
        return KulakanPresenter(activity as Context, this)
    }

    override fun onCreateLayout(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.activity_report_kulakan, container, false)
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

        adapter.callback = object : KulakanAdapter.ItemClickCallback {
            override fun onClick(data: Transaction) {
                openDetail(data.no_invoice!!)
            }
        }

        _view.btn_date.setOnClickListener {
            openFilter(getPresenter()?.getFilterDateSelected())
        }

        _view.btn_sort.setOnClickListener {
            listener?.openFilterByStatusDialog("Filter by status",getPresenter()?.getFilters()!!,getPresenter()?.getFilterSelected(),AppConstant.Code.CODE_TRANSACTION_SUPPLIER)
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
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(AppConstant.CODE, AppConstant.Code.CODE_TRANSACTION_SUPPLIER)
        intent.putExtra(AppConstant.DATA, id)
        startActivity(intent)
    }

//    override fun openFilterByStatusDialog(title: String, list: List<DialogModel>, selected: DialogModel?, type: Int) {
//        val dialog = BottomDialog.newInstance()
//        dialog.setData(title,type,list,selected)
//        dialog.show(this.supportFragmentManager, BottomDialog.TAG)
//    }

//    override fun openFilterDateDialog(minDate: LocalDate?, maxDate: LocalDate?, firstDate: CalendarDay?, lastDate: CalendarDay?, type:Int) {
//        val dateDialog = RangeDateDialog.newInstance()
//        dateDialog.setType(type)
//        dateDialog.setData(minDate,maxDate,firstDate,lastDate)
//        dateDialog.show(this.supportFragmentManager, RangeDateDialog.TAG)
//    }
//
//    override fun onDateRangeClicked(firstDate: CalendarDay?, lastDate: CalendarDay?, type: Int) {
//        adapter.clearAdapter()
//        sw_refresh.isRefreshing = true
//        getPresenter()?.onChangeDate(firstDate,lastDate)
//    }
//
//    override fun onItemClicked(data: DialogModel, type: Int) {
//        adapter.clearAdapter()
//        sw_refresh.isRefreshing = true
//        getPresenter()?.onChangeStatus(data)
//    }


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

    interface Listener {
        fun openFilterByStatusDialog(title:String, list: List<DialogModel>, selected: DialogModel?, type:Int)
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
