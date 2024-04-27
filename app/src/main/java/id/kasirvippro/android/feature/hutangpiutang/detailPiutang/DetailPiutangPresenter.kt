package id.kasirvippro.android.feature.hutangpiutang.detailPiutang

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.customer.CustomerRestModel
import id.kasirvippro.android.models.hutangpiutang.DetailPiutang
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper

class DetailPiutangPresenter(val context: Context, val view: DetailPiutangContract.View) : BasePresenter<DetailPiutangContract.View>(),
    DetailPiutangContract.Presenter, DetailPiutangContract.InteractorOutput {

    private var interactor = DetailPiutangInteractor(this)
    private val customerRestModel = CustomerRestModel(context)
    private val hutangRestModel = HutangPiutangRestModel(context)
    private var customer:Customer ?= null

    override fun onViewCreated(intent: Intent) {
        customer = intent.getSerializableExtra(AppConstant.DATA) as Customer
        checkCustomer()
        loadDetailCustomer()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun getTitleName(): String {
        return if(customer == null){
            ""
        } else{
            customer?.name_customer!!
        }

    }

    private fun checkCustomer(){
        if(customer == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        view.setCustomer(customer?.name_customer,customer?.email,customer?.telephone,customer?.address)
    }

    override fun loadDetailCustomer() {
        interactor.callGetDetailCustomer(context,customerRestModel,customer?.id_customer!!)
    }

    override fun onSuccessGetDetailCustomer(data: Customer) {
        if(data == null){
            onFailedAPI(999,"Data not found")
            return
        }
        customer = data
        checkCustomer()
        loadHutang()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun loadHutang() {
        interactor.callGetHutang(context,hutangRestModel,customer?.id_customer!!)
    }

    override fun onSuccessGetHutang(data: DetailPiutang) {
        if(data == null){
            onFailedAPI(999,"Data not found")
            return
        }
        val piutang = data.datadebt
        val list = data.sudah_bayar
        val decimal = AppConstant.DECIMAL.getDecimalData()
        if(decimal=="No Decimal") {
            view.setPiutang(AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(piutang?.total_tagihan!!),AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(piutang?.jumlah_piutang!!),
                AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(piutang.total_dibayar!!),
                Helper.getDateFormat(context, piutang.jatuh_tempo!!,
                    "yyyy-MM-dd","dd MMMM yyyy")
            )
        }else{
            view.setPiutang(AppConstant.CURRENCY.getCurrencyData() + piutang?.total_tagihan!!,AppConstant.CURRENCY.getCurrencyData() + piutang.jumlah_piutang!!,
                AppConstant.CURRENCY.getCurrencyData() + piutang.total_dibayar!!,
                Helper.getDateFormat(context, piutang.jatuh_tempo!!,
                    "yyyy-MM-dd","dd MMMM yyyy")
            )
        }

        view.setList(list!!)
    }
}