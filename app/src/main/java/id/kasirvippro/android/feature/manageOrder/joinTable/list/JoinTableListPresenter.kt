package id.kasirvippro.android.feature.manageOrder.joinTable.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel

class JoinTableListPresenter(val context: Context, val view: JoinTableListContract.View) : BasePresenter<JoinTableListContract.View>(),
    JoinTableListContract.Presenter, JoinTableListContract.InteractorOutput {

    private var interactor: JoinTableListInteractor = JoinTableListInteractor(this)
    private var tableRestModel = TableRestModel(context)

    override fun onViewCreated() {
        loadCategories()
    }

    override fun loadCategories() {
        interactor.callGetTableAPI(context,tableRestModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetTable(list: List<Table>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}