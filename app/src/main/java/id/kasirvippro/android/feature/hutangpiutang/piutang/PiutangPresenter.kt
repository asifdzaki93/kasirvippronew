package id.kasirvippro.android.feature.hutangpiutang.piutang

import android.content.Context
import id.kasirvippro.android.utils.Helper
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel
import id.kasirvippro.android.models.hutangpiutang.Piutang
import id.kasirvippro.android.utils.AppConstant

class PiutangPresenter(val context: Context, val view: PiutangContract.View) : BasePresenter<PiutangContract.View>(),
    PiutangContract.Presenter,
    PiutangContract.InteractorOutput {

    private var interactor = PiutangInteractor(this)
    private var restModel = HutangPiutangRestModel(context)

    override fun onViewCreated() {
        loadHutang()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun loadHutang() {
        interactor.callGetHutangAPI(context,restModel)
    }

    override fun onSuccessGetHutang(data: Piutang) {
        if(data == null){
            onFailedAPI(999,"Data not found")
            return
        }
        val info = data.datapiutang
        val list = data.list

        info?.let {
            val decimal = AppConstant.DECIMAL.getDecimalData()
            if(decimal=="No Decimal") {
                view.setInfo(Helper.convertToCurrency(it.amount_debt!!), AppConstant.CURRENCY.getCurrencyData() + Helper.convertToCurrency(it.nominal_debt!!),
                    Helper.convertToCurrency(it.due_date!!),Helper.convertToCurrency(it.notyet_paidoff!!))
            }else{
                view.setInfo(it.amount_debt!!.toString(), AppConstant.CURRENCY.getCurrencyData() + it.nominal_debt!!.toString(),
                    it.due_date!!.toString(),it.notyet_paidoff!!.toString())
            }

        }

        if(list == null){
            onFailedAPI(999,"No data available")
            return
        }
        if(list.isEmpty()){
            onFailedAPI(999,"No data available")
        }
        else{
            view.setList(list!!)
        }


    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}