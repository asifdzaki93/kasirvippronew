package id.kasirvippro.android.feature.choose.kurir

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.models.staff.StaffRestModel

class ChooseKurirPresenter(val context: Context, val view: ChooseKurirContract.View) : BasePresenter<ChooseKurirContract.View>(),
    ChooseKurirContract.Presenter, ChooseKurirContract.InteractorOutput {

    private var interactor  = ChooseKurirInteractor(this)
    private var restModel = StaffRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    fun setCustomer(list: List<Staff>){
        view.setData(list)
    }

    override fun onSuccessGetData(list: List<Staff>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}