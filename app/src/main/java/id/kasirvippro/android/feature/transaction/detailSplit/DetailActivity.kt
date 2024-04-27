package id.kasirvippro.android.feature.transaction.detailSplit

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.feature.dialog.PaymentDialog
import id.kasirvippro.android.feature.printer.PrinterActivity
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.feature.addOrder.main.AddOrderActivity
import id.kasirvippro.android.feature.dialog.SingleDateDialog
import id.kasirvippro.android.feature.manageOrder.main.ManageOrderActivity
import id.kasirvippro.android.feature.transaction.success.SuccessActivity
import id.kasirvippro.android.rest.entity.RestException
import id.kasirvippro.android.ui.ext.toast
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_transaction_detail.*
import kotlinx.android.synthetic.main.activity_transaction_detail.btn_cancel
import kotlinx.android.synthetic.main.activity_transaction_detail.btn_printer
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.*
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.ll_discount
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.ll_service
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.ll_tax
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.ns_scroll
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.rv_list
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.sw_refresh
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_date
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_discount
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_id
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_operator
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_powered_by
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_service
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_status
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_subtotal
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_tax
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_total
import kotlinx.android.synthetic.main.activity_transaction_detailsplit.tv_total_big
import kotlinx.android.synthetic.main.item_list_transactionsplit.tv_count
import org.threeten.bp.LocalDate
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


class DetailActivity : BaseActivity<DetailPresenter, DetailContract.View>(), DetailContract.View, PaymentDialog.Listener,
    SingleDateDialog.Listener {

    val adapter = DetailAdapter()
    private val CODEORDER = 1002

    override fun createPresenter(): DetailPresenter {
        return DetailPresenter(this, this)
    }

    override fun createLayout(): Int {
        return R.layout.activity_transaction_detailsplit
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

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_list.layoutManager = layoutManager
        rv_list.adapter = adapter

        adapter.callback = object : DetailAdapter.ItemClickCallback{

            override fun onDelete(data: DetailTransaction.Data) {
                showLoadingDialog()
                getPresenter()?.deleteProduct(data.no_invoice!!,data.id_product!!)
            }

            override fun onPlus(data: DetailTransaction.Data) {
                showLoadingDialog()
                getPresenter()?.plusProduct(data.no_invoice!!,data.id_product!!)
            }
            override fun onMinus(data: DetailTransaction.Data) {
                showLoadingDialog()
                getPresenter()?.minusProduct(data.no_invoice!!,data.id_product!!)
            }
        }

        btn_addorder.setOnClickListener {
            getPresenter()?.addOrder()
        }

        btn_cancel.setOnClickListener {
            showLoadingDialog()
            getPresenter()?.deleteDetail()
        }

        btn_printer.setOnClickListener {
            getPresenter()?.onCheckBluetooth()
        }

        et_hour.setOnClickListener {
            getTime(et_hour)
        }

        btn_date.setOnClickListener {
            openSingleDatePickerDialog(getPresenter()?.getSelectedDate())
        }

        btn_ubah.setOnClickListener {
            showLoadingDialog()
            val note = "Event hours: " + et_hour.text.toString().trim()
            getPresenter()?.checkPiutang(note)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getTime(textView: TextView){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            et_hour.text = SimpleDateFormat("HH:mm").format(cal.time)
        }
        textView.setOnClickListener {
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }

    override fun openSingleDatePickerDialog(selected: CalendarDay?) {
        val dateDialog = SingleDateDialog.newInstance()
        val now = LocalDate.now()
        dateDialog.setData(selected, now, null, -1)
        dateDialog.show(this.supportFragmentManager, SingleDateDialog.TAG)
    }

    //override fun onCreateOptionsMenu(menu: Menu): Boolean {
    //    menuInflater.inflate(R.menu.menu_printer, menu)
    //   return super.onCreateOptionsMenu(menu)
    // }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> onClose()
           // R.id.action_add -> openPrinterPage()
        }
        return super.onOptionsItemSelected(item!!)
    }




    private fun setupToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.lbl_manage_editorder)
            elevation = 0f
        }

    }

    override fun setProducts(list: List<DetailTransaction.Data>) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        adapter.setItems(list)
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }

    override fun openAddOrderPage(id: String) {
        val intent = Intent(this, AddOrderActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        intent.putExtra("invoice", id);
        startActivityForResult(intent,CODEORDER)
    }

    override fun showMessage(code: Int, msg: String?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
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

    override fun showTunaiView() {

    }



    override fun getTotalValue(): Double {

        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            val value = tv_count.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "").replace(".", "")

            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()

        }else{
            val nf: NumberFormat = NumberFormat.getInstance(Locale.GERMAN)
            val number: Double = nf.parse(tv_count.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")).toDouble()

            val value = tv_total.text.toString().replace(AppConstant.CURRENCY.getCurrencyData(), "")
            if (value.isBlank() || value.isEmpty()) {
                return 0.0
            }
            return value.toDouble()
        }

    }

    override fun showSuccessMessage(msg: String?) {
        hideLoadingDialog()
        sw_refresh.isRefreshing = false
        msg?.let {
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
        }
        reloadData()

    }


    override fun reloadData() {
        hideLoadingDialog()
        sw_refresh.isRefreshing = true
        adapter.clearAdapter()
        hideShowActionButton(View.GONE)
        getPresenter()?.loadDetail()
    }

    override fun onClose(status: Int) {
        setResult(status,intent)
        finish()
    }



    override fun openSuccessPage(id: String) {
        val intent = Intent(this, SuccessActivity::class.java)
        intent.putExtra(AppConstant.DATA,id)
        startActivity(intent)

    }



    override fun setInfo(id: String, date: String, status: String, totalorder: String, operator: String,service:String?,pajak:String?,diskon:String?,subtotal: String) {
        tv_id.text = id
        tv_date.text = date
        tv_status.text = status
        tv_total.text = totalorder
        tv_operator.text = operator
        tv_total_big.text = totalorder
        tv_subtotal.text = subtotal


        ll_service.visibility = View.GONE
        service?.let {
            ll_service.visibility = View.VISIBLE
            tv_service.text = it
        }

        ll_tax.visibility = View.GONE
        pajak?.let {
            ll_tax.visibility = View.VISIBLE
            tv_tax.text = it
        }

        ll_discount.visibility = View.GONE
        diskon?.let {
            ll_discount.visibility = View.VISIBLE
            tv_discount.text = it
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onBackPressed() {
        onClose()
    }


    override fun openPrinterPage() {
        val intent = Intent(this,PrinterActivity::class.java)
        intent.putExtra(AppConstant.DATA,getPresenter()?.getDataStruk())
        startActivity(intent)
    }


    override fun onPay(selected: DetailTransaction, type: Int, value: String) {
        showLoadingDialog()
        getPresenter()?.onPay(value)
    }

    override fun onClose() {
        if(getPresenter()?.isOpenMain()!!){
            val intent = Intent(this,ManageOrderActivity::class.java)
            startActivity(intent)
        }
        else{
            finish()
        }
    }

    override fun getParentLayout(): NestedScrollView {
        return ns_scroll
    }



    override fun hideShowActionButton(visibility: Int) {
    }

    override fun onPremiumPage(isPremium: Boolean) {
        if(isPremium){
            tv_powered_by.visibility = View.GONE
        }
        else{
            tv_powered_by.visibility = View.VISIBLE
        }
    }

    override fun onDateClicked(selected: CalendarDay?, type: Int) {
        getPresenter()?.setSelectedDate(selected)
        if (selected == null) {
            et_date.text = ""
        } else {
            et_date.text =
                Helper.getDateFormat(this, selected.date.toString(), "yyyy-MM-dd", "dd MMMM yyyy")
        }
    }


}
