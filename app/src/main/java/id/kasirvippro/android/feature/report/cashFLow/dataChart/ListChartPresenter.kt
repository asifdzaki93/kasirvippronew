package id.kasirvippro.android.feature.report.cashFLow.dataChart

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel

class ListChartPresenter(val context: Context, val view: ListChartContract.View) : BasePresenter<ListChartContract.View>(),
    ListChartContract.Presenter, ListChartContract.InteractorOutput {

    private var interactor = ListChartInteractor(this)
    private var premium:Boolean = false
    private var roleRestModel = RoleRestModel(context)
    private var level:String? = "kasir"

    override fun onViewCreated() {
    }
    override fun onDestroy() {
        interactor.onDestroy()
    }
}