package id.kasirvippro.android.feature.manageOrder.table.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.models.table.TableRestModel

class TableListPresenter(val context: Context, val view: TableListContract.View) : BasePresenter<TableListContract.View>(),
    TableListContract.Presenter, TableListContract.InteractorOutput {

    private var interactor: TableListInteractor = TableListInteractor(this)
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