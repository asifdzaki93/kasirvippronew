package id.kasirvippro.android.feature.manageStock.main

import android.content.Context
import id.kasirvippro.android.base.BasePresenter

class ManageStockPresenter(val context: Context, val view: ManageStockContract.View) : BasePresenter<ManageStockContract.View>(),
    ManageStockContract.Presenter, ManageStockContract.InteractorOutput {

    private var interactor = ManageStockInteractor(this)
    private var premium:Boolean = false
    private var level:String? = "kasir"

    override fun onViewCreated() {
        premium = "1" == interactor.getUserPaket(context)
        level = interactor.getUserLevel(context)
        when(level){
            "master" -> view.onMasterPage(true)
            "admin" -> view.onAdminPage()
            else -> view.onSalesPage()
        }

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}