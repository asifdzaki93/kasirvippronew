package id.kasirvippro.android.feature.report.main

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel

class ReportPresenter(val context: Context, val view: ReportContract.View) : BasePresenter<ReportContract.View>(),
    ReportContract.Presenter, ReportContract.InteractorOutput {

    private var interactor = ReportInteractor(this)
    private var premium:Boolean = false
    private var roleRestModel = RoleRestModel(context)
    private var level:String? = "kasir"


    override fun onViewCreated() {
        premium = "1" == interactor.getUserPaket(context)
        level = interactor.getUserLevel(context)
        when(level){
           // "master" -> view.onMasterPage(true)
           // "admin" -> view.onAdminPage()
          //  else -> view.onSalesPage()
        }
        view.onPremiumPage(true)
        loadRole()
    }

    override fun loadRole() {
        interactor.callGetRoleAPI(context,roleRestModel)
    }

    override fun onCheckSalary() {
//        if(premium){
//            view.openSalary()
//        }
//        else{
//            val key = interactor.getToken(context)
//            val url = AppConstant.URL.PREMIUM+key
//            view.openWebviewPage("Premium",url)
//        }
        view.openSalary()
    }

    override fun onCheckAbsensi() {
//        if(premium){
//            view.openAbsensi()
//        }
//        else{
//            val key = interactor.getToken(context)
//            val url = AppConstant.URL.PREMIUM+key
//            view.openWebviewPage("Premium",url)
//        }
        view.openAbsensi()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onSuccessGetRole(list: List<Role>) {
        val role = list[0]
        view.setRole(role.name_role,role.report_product,role.report_sumary,role.report_daily,role.report_accounting)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}