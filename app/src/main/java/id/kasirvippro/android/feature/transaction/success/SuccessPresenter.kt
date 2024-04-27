package id.kasirvippro.android.feature.transaction.success

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.sqlite.Model.SalesDataSQL
import id.kasirvippro.android.sqlite.Model.SalesSQL
import id.kasirvippro.android.utils.*

class SuccessPresenter(val context: Context, val view: SuccessContract.View) : BasePresenter<SuccessContract.View>(),
    SuccessContract.Presenter, SuccessContract.InteractorOutput {

    private var interactor = SuccessInteractor(this)
    private var restModel = TransactionRestModel(context)
    private var invoice:String ?= null
    private var detail:DetailTransaction?=null
    private var position:Int ?= 0

    private var sales : ArrayList<SalesSQL> = ArrayList()
    private lateinit var salesdata : SalesDataSQL

    override fun onViewCreated(intent: Intent) {

        invoice = intent.getStringExtra(AppConstant.DATA)
        if(invoice.isNullOrBlank() || invoice.isNullOrEmpty()){
            view.showMessage(999,"Nomor invoice tidak ditemukan")
            return
        }

        var connectivity : ConnectivityManager? = null
        var info : NetworkInfo? = null
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE)
                as ConnectivityManager;
        if ( connectivity != null) {
            info = connectivity!!.activeNetworkInfo
            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    loadDetail()
                }else{
                    sales = intent.getSerializableExtra(AppConstant.sales) as ArrayList<SalesSQL>
                    salesdata = intent.getSerializableExtra(AppConstant.salesData) as SalesDataSQL
                    (context as SuccessActivity).set(sales,salesdata)
                }
            }else{
                sales = intent.getSerializableExtra(AppConstant.sales) as ArrayList<SalesSQL>
                salesdata = intent.getSerializableExtra(AppConstant.salesData) as SalesDataSQL
                (context as SuccessActivity).set(sales,salesdata)
            }
        }else{
            sales = intent.getSerializableExtra(AppConstant.sales) as ArrayList<SalesSQL>
            salesdata = intent.getSerializableExtra(AppConstant.salesData) as SalesDataSQL
            (context as SuccessActivity).set(sales,salesdata)
        }

    }

    override fun loadDetail() {
        interactor.callGetDetailAPI(context,restModel,invoice!!)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onSuccessGetDetail(detail: DetailTransaction?) {
        this.detail = detail
        if(detail == null){
            view.showMessage(999,"Nomor invoice tidak ditemukan")
            return
        }
        when {
            "hutang"
                .equals(detail.struk?.status,true) -> {
                position = 1
            }
            else -> {
                position = 0
            }
        }
        view.onSuccess(Helper.getDateFormat(context,detail.struk?.date!!,"yyyy-MM-dd","EEE, dd MMMM yyyy"),invoice!!)

    }

    override fun getDataStruk():DetailTransaction {
        return detail!!
    }

    override fun getTabPosition(): Int {
        return position!!
    }

    override fun getInvoice(): String? {
        return invoice
    }
}