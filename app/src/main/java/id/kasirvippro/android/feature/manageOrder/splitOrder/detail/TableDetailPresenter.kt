package id.kasirvippro.android.feature.manageOrder.splitOrder.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.table.Table
import id.kasirvippro.android.utils.AppConstant

class TableDetailPresenter(val context: Context, val view: TableDetailContract.View) : BasePresenter<TableDetailContract.View>(),
    TableDetailContract.Presenter,
    TableDetailContract.InteractorOutput {

    private var interactor = TableDetailInteractor(this)
    private var title = ""
    private var data : Table?= null


    override fun onViewCreated(intent: Intent) {
        data = intent.getSerializableExtra(AppConstant.DATA) as Table
        if(data == null){
            view.onClose(Activity.RESULT_CANCELED)
            return
        }

        data?.let {
            title = it.name_table!!
            view.setTable(it.name_table)
        }
    }

    override fun getTitleName(): String {
        return title
    }

    override fun setTableData(dt: Table) {
        data = dt
        data?.let {
            title = it.name_table!!
            view.setTable(it.name_table)
        }
    }

    override fun getTableData(): Table? {
        return data
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}