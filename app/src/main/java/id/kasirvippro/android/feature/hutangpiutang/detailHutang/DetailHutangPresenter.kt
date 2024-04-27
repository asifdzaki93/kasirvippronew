package id.kasirvippro.android.feature.hutangpiutang.detailHutang

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.hutangpiutang.DetailHutang
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel
import id.kasirvippro.android.models.supplier.Supplier
import id.kasirvippro.android.models.supplier.SupplierRestModel
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper

class DetailHutangPresenter(val context: Context, val view: DetailHutangContract.View) : BasePresenter<DetailHutangContract.View>(),
    DetailHutangContract.Presenter, DetailHutangContract.InteractorOutput {

    private var interactor = DetailHutangInteractor(this)
    private val supplierRestModel = SupplierRestModel(context)
    private val hutangRestModel = HutangPiutangRestModel(context)
    private var supplier:Supplier ?= null

    override fun onViewCreated(intent: Intent) {
        supplier = intent.getSerializableExtra(AppConstant.DATA) as Supplier
        checkSupplier()
        loadDetailSupplier()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun getTitleName(): String {
        return if(supplier == null){
            ""
        } else{
            supplier?.name_supplier!!
        }

    }

    private fun checkSupplier(){
        if(supplier == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }
        view.setSupplier(supplier?.name_supplier,supplier?.email,supplier?.telephone,supplier?.address)
    }

    override fun loadDetailSupplier() {
        interactor.callGetDetailSupplier(context,supplierRestModel,supplier?.id_supplier!!)
    }

    override fun onSuccessGetDetailSupplier(data: Supplier) {
        if(data == null){
            onFailedAPI(999,"Data not found")
            return
        }
        supplier = data
        checkSupplier()
        loadHutang()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun loadHutang() {
        interactor.callGetHutang(context,hutangRestModel,supplier?.id_supplier!!)
    }

    override fun onSuccessGetHutang(data: DetailHutang) {
        if(data == null){
            onFailedAPI(999,"Data not found")
            return
        }
        val piutang = data.datapiutang
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