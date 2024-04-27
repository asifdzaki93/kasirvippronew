package id.kasirvippro.android.feature.manageOrder.menuOrder

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl

interface MenuOrderContract {

    interface View : BaseViewImpl {

        fun openMovetablePage()
        fun openJointablePage()
        fun openKitchenPage()
        fun openEditOrderPage()
        fun openWebviewPage(title:String,url:String)
        fun onMasterPage(isPremium:Boolean)
        fun onAdminPage()
        fun onSalesPage()
        fun onWaiterPage()
        fun onKitchenPage()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun getUserLevel(context: Context):String?
        fun getUserPaket(context: Context):String?


    }

    interface InteractorOutput : BaseInteractorOutputImpl {

    }


}