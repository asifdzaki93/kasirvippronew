package id.kasirvippro.android.feature.transaction.successLabel

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.DetailLabel
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.sqlite.Model.SalesDataSQL
import id.kasirvippro.android.sqlite.Model.SalesSQL
import id.kasirvippro.android.utils.*

class SuccessLabelPresenter(val context: Context, val view: SuccessLabelContract.View) : BasePresenter<SuccessLabelContract.View>(),
    SuccessLabelContract.Presenter, SuccessLabelContract.InteractorOutput {

    private var interactor = SuccessLabelInteractor(this)
    private var restModel = TransactionRestModel(context)
    private var invoice:String ?= null
    private var detail:DetailLabel?=null
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
                    (context as SuccessLabelActivity).set(sales,salesdata)
                }
            }else{
                sales = intent.getSerializableExtra(AppConstant.sales) as ArrayList<SalesSQL>
                salesdata = intent.getSerializableExtra(AppConstant.salesData) as SalesDataSQL
                (context as SuccessLabelActivity).set(sales,salesdata)
            }
        }else{
            sales = intent.getSerializableExtra(AppConstant.sales) as ArrayList<SalesSQL>
            salesdata = intent.getSerializableExtra(AppConstant.salesData) as SalesDataSQL
            (context as SuccessLabelActivity).set(sales,salesdata)
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

    override fun onSuccessLabelGetDetail(detail: DetailLabel?) {
        this.detail = detail
        if(detail == null){
            view.showMessage(999,"Nomor invoice tidak ditemukan")
            return
        }
        view.onSuccessLabel(invoice!!)

    }

    override fun getDataStruk():DetailLabel {
        return detail!!
    }

    override fun getTabPosition(): Int {
        return position!!
    }

    override fun getInvoice(): String? {
        return invoice
    }
}