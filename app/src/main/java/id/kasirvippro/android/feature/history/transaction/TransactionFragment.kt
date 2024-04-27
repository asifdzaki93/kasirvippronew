package id.kasirvippro.android.feature.history.transaction

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
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.models.transaction.Transaction
import kotlinx.android.synthetic.main.fragment_history_transaction.view.*
import id.kasirvippro.android.feature.transaction.detail.DetailActivity
import id.kasirvippro.android.feature.filterDate.main.MainActivity
import id.kasirvippro.android.models.DialogModel
import id.kasirvippro.android.models.FilterDialogDate
import id.kasirvippro.android.sqlite.DataManager
import id.kasirvippro.android.sqlite.Model.SalesSQL
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

        return inflater.inflate(R.layout.fragment_history_transaction, container, false)
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
            override fun onClick(data: Transaction) {
                openDetail(data.no_invoice!!,data.uuid)
            }
        }

        _view.btn_date.setOnClickListener {
            openFilter(getPresenter()?.getFilterDateSelected())
        }

        _view.btn_sort.setOnClickListener {
            listener?.openFilterByStatusDialog("Filter by status",getPresenter()?.getFilters()!!,getPresenter()?.getFilterSelected(),AppConstant.Code.CODE_TRANSACTION_CUSTOMER)
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

    override fun setList(list: List<Transaction>?) {
        _view.sw_refresh.isRefreshing = false
        _view.rv_list.visibility = View.VISIBLE
        _view.tv_error.visibility = View.GONE

        val transactions = ArrayList<Transaction>()
        val temptransactions = ArrayList<Transaction>()

        var totalorder = 0

        var trans = Transaction();

        val dataManager = DataManager (this.context!!)
        var allSalesData = dataManager.allSalesData()

        if(allSalesData != null && allSalesData.isNotEmpty()){

            for (item in allSalesData){
                var dat = Transaction();
                dat.set(
                    uuid = item.uuid,
                    name_product = null,
                    name_supplier = null,
                    img = null,
                    no_invoice = item.no_invoice,
                    date = item.date,
                    payment = item.payment,
                    totalorder = item.totalprice,
                    nominal = null,
                    by = null,
                    status = item.status,
                    type = item.payment
                )
                totalorder = totalorder + item.totalprice.toInt()
                temptransactions.add(dat)
            }
            trans.set(
                uuid = null,name_product = null, name_supplier = null, img = null, no_invoice = null, date = "Offline", payment = "0", totalorder = totalorder.toString(), nominal = "0", by = null, status = null, type = "header"
            )
        }else{
            trans.set(
                uuid = null,name_product = null, name_supplier = null, img = null, no_invoice = null, date = "Offline Empty", payment = "0", totalorder = "0", nominal = "0", by = null, status = null, type = "header"
            )
        }

        transactions.add(trans)
        for(item in temptransactions){
            var dat = Transaction();
            dat.set(
                uuid = item.uuid,
                name_product = item.name_product,
                name_supplier = item.name_supplier,
                img = item.img,
                no_invoice = item.no_invoice,
                date = item.date, payment = item.payment,
                totalorder = item.totalorder,
                nominal = item.nominal,
                by = item.nominal,
                status = item.status,
                type = item.type
            )
            transactions.add(dat)
        }

        if (list != null){
            for(item in list){
                var dat = Transaction();
                dat.set(
                    uuid = null,name_product = item.name_product, name_supplier = item.name_supplier, img = item.img, no_invoice = item.no_invoice, date = item.date, payment = item.payment, totalorder = item.totalorder, nominal = item.nominal, by = item.nominal, status = item.status, type = item.type
                )
                transactions.add(dat)
            }
        }

        adapter.setItems(transactions)
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

    override fun openDetail(id: String, uuid: String?) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)

        if (uuid != null){
            val dataManager = DataManager (this.context!!)

            var SalesData = dataManager.salesDataUUID(uuid)
            var Sales = dataManager.allSalesUUID(uuid)

            val list: ArrayList<SalesSQL> = ArrayList()
            for (sal in Sales){
                var sales = SalesSQL(
                    increment = sal.increment.toString(),
                    uuid_salesData = sal.uuid_salesData.toString(),
                    id_sales = sal.id_sales.toString(),
                    id_customer = sal.id_customer.toString(),
                    id_product = sal.id_product,
                    codeproduct = sal.codeproduct,
                    name_product = sal.name_product,
                    id_store = sal.id_store,
                    user = sal.user,
                    no_invoice = sal.no_invoice,
                    amount = sal.amount, price = sal.price,
                    totalprice = sal.totalprice.toString(), hpp = sal.hpp.toString(), totalcapital = sal.totalcapital.toString(),
                    date = sal.date.toString(), status = sal.status.toString(), note = sal.note.toString(), rest_stock = sal.rest_stock.toString(),
                    sift = sal.sift.toString(), station = sal.station.toString())
                list.add(sales)
            }

            intent.putExtra(AppConstant.sales,list)
            intent.putExtra(AppConstant.salesData,SalesData)
        }
        startActivity(intent)
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
