package id.kasirvippro.android.feature.hutangpiutang.piutangCustomer

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel

class PiutangCustomerPresenter(val context: Context, val view: PiutangCustomerContract.View) : BasePresenter<PiutangCustomerContract.View>(),
    PiutangCustomerContract.Presenter, PiutangCustomerContract.InteractorOutput {

    private var interactor = PiutangCustomerInteractor(this)
    private var restModel = HutangPiutangRestModel(context)


    override fun onViewCreated() {
        loadHutang()
    }

    override fun loadHutang() {
        interactor.callGetHutangAPI(context,restModel)
    }

    override fun searchHutang(search: String) {
        interactor.onRestartDisposable()
        if(search.isNullOrEmpty() || search.isNullOrBlank()){
            interactor.callGetHutangAPI(context,restModel)
        }
        else{
            interactor.callSearchHutangAPI(context,restModel,search)
        }
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetHutang(list: List<Customer>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}