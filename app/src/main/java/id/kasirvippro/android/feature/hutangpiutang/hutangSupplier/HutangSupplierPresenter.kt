package id.kasirvippro.android.feature.hutangpiutang.hutangSupplier

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel
import id.kasirvippro.android.models.supplier.Supplier

class HutangSupplierPresenter(val context: Context, val view: HutangSupplierContract.View) : BasePresenter<HutangSupplierContract.View>(),
    HutangSupplierContract.Presenter, HutangSupplierContract.InteractorOutput {

    private var interactor = HutangSupplierInteractor(this)
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

    override fun onSuccessGetHutang(list: List<Supplier>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}