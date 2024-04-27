package id.kasirvippro.android.feature.choose.tableActive

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel

class ChooseTablePresenter(val context: Context, val view: ChooseTableContract.View) : BasePresenter<ChooseTableContract.View>(),
    ChooseTableContract.Presenter, ChooseTableContract.InteractorOutput {

    private var interactor = ChooseTableInteractor(this)
    private var restModel = TableRestModel(context)

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetData(list: List<Table>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}