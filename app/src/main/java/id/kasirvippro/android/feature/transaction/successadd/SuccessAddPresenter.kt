package id.kasirvippro.android.feature.transaction.successadd

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.transaction.DetailTransaction
import id.kasirvippro.android.models.transaction.TransactionRestModel
import id.kasirvippro.android.utils.*

class SuccessAddPresenter(val context: Context, val view: SuccessAddContract.View) : BasePresenter<SuccessAddContract.View>(),
    SuccessAddContract.Presenter, SuccessAddContract.InteractorOutput {

    private var interactor = SuccessAddInteractor(this)
    private var restModel = TransactionRestModel(context)
    private var invoice:String ?= null
    private var detail:DetailTransaction?=null
    private var position:Int ?= 0


    override fun onViewCreated(intent: Intent) {

        invoice = intent.getStringExtra(AppConstant.DATA)
        if(invoice.isNullOrBlank() || invoice.isNullOrEmpty()){
            view.showMessage(999,"Invoice number not found")
            return
        }
        loadDetail()
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
            view.showMessage(999,"Invoice number not found")
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