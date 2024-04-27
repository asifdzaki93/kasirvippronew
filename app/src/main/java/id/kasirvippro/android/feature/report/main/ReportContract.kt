package id.kasirvippro.android.feature.report.main

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel

interface ReportContract {

    interface View : BaseViewImpl {
        fun setRole(name_role: String?, report_product: String?, report_sumary: String?, report_daily: String?, report_accounting: String?)
        fun showMessage(code: Int, msg: String?)
        fun openTransaction()
        fun openProfit()
        fun openMutasi()
        fun openSalary()
        fun openSell()
        fun openDaily()
        fun openPreOrder()
        fun openProductSell()
        fun openCashFlow()
        fun openWebviewPage(title:String,url:String)
        fun openAbsensi()
        fun openRawpage()
        fun openReportRawpage()
        fun onPremiumPage(isPremium:Boolean)
        fun openRolePage()
        fun loadRole()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheckSalary()
        fun onCheckAbsensi()
        fun loadRole()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun getUserPaket(context: Context):String?
        fun getUserLevel(context: Context):String?
        fun callGetRoleAPI(context: Context, restModel: RoleRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessGetRole(list:List<Role>)
    }


}