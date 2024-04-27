package id.kasirvippro.android.feature.manage.main

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel

class ManagePresenter(val context: Context, val view: ManageContract.View) : BasePresenter<ManageContract.View>(),
    ManageContract.Presenter, ManageContract.InteractorOutput {

    private var interactor = ManageInteractor(this)
    private var premium:Boolean = false
    private var roleRestModel = RoleRestModel(context)
    private var level:String? = "kasir"

    override fun onViewCreated() {
        premium = "1" == interactor.getUserPaket(context)
        level = interactor.getUserLevel(context)
        when(level){
            //"master" -> view.onMasterPage(true)
            //"admin" -> view.onAdminPage()
            //else -> view.onSalesPage()
        }
        loadRole()
    }

    override fun loadRole() {
        interactor.callGetRoleAPI(context,roleRestModel)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onCheckStaff() {
//        if(premium){
//            view.openStaffPage()
//        }
//        else{
//            val key = interactor.getToken(context)
//            val url = AppConstant.URL.PREMIUM+key
//            view.openWebviewPage("Premium",url)
//        }
        view.openStaffPage()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onSuccessGetRole(list: List<Role>) {
        val role = list[0]
        view.setRole(role.name_role,role.add_product,role.add_category,role.add_supplier,role.add_customer,role.add_discount)
    }
}