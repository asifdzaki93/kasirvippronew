package id.kasirvippro.android.feature.manageStock.main

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl

interface ManageStockContract {

    interface View : BaseViewImpl {
        fun openStockOpnamePage()
        fun openStockHistoryPage()
        fun openTransferPage()
        fun openDataTransferPage()
        fun openPurchaseStockPage()
        fun openRawMaterialPage()
        fun openReturnTransferPage()
        fun onMasterPage(isPremium:Boolean)
        fun onAdminPage()
        fun onSalesPage()

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