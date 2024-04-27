package id.kasirvippro.android.feature.manageOrder.menuOrder

import android.content.Context
import id.kasirvippro.android.base.BasePresenter

class MenuOrderPresenter(val context: Context, val view: MenuOrderContract.View) : BasePresenter<MenuOrderContract.View>(),
    MenuOrderContract.Presenter, MenuOrderContract.InteractorOutput {

    private var interactor = MenuOrderInteractor(this)
    private var premium:Boolean = false
    private var level:String? = "kasir"

    override fun onViewCreated() {
        premium = "1" == interactor.getUserPaket(context)
        level = interactor.getUserLevel(context)
        when(level){
            "master" -> view.onMasterPage(true)
            "admin" -> view.onAdminPage()
            "kasir" -> view.onSalesPage()
            "waiter" -> view.onWaiterPage()
            else -> view.onKitchenPage()
        }

    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

}