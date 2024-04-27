package id.kasirvippro.android.feature.report.cashFLow.dataChart

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel

interface ListChartContract {

    interface View : BaseViewImpl {
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun getUserPaket(context: Context):String?
        fun getUserLevel(context: Context):String?
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
    }


}