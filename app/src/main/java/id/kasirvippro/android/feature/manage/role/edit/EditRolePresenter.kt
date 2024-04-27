package id.kasirvippro.android.feature.manage.role.edit

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel


class EditRolePresenter(val context: Context, val view: EditRoleContract.View) : BasePresenter<EditRoleContract.View>(),
    EditRoleContract.Presenter, EditRoleContract.InteractorOutput {

    private var interactor = EditRoleInteractor(this)
    private var restModel = RoleRestModel(context)
    private var roleRestModel = RoleRestModel(context)
    private var role:Role?=null
    private var roles:String? = ""
    override fun onViewCreated() {

        role = interactor.loadData(context)
        role?.let {
            view.setInfo(it)
        }

        loadData()
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun loadData() {
        val idrole = view.getIdRole()
        interactor.callGetProfileAPI(context,roleRestModel,idrole!!)
    }

    override fun setActive(value: String?, id: String?, name: String?) {
        value?.let {
            interactor.callMenuOrderAPI(context,roleRestModel,value,id!!,name!!)
        }
    }

    override fun onSuccessGetProfile(list: List<Role>) {
             val role = list[0]
        interactor.saveUser(role)
        view.setProfile(role.name_role, role.menu_order, role.menu_purchase, role.menu_spending, role.menu_transaction, role.menu_debt, role.menu_printlabel, role.menu_manageorder, role.menu_managestock, role.menu_return, role.menu_addon, role.menu_othermenu, role.add_product, role.add_category, role.add_supplier, role.add_customer, role.add_discount, role.report_product, role.report_sumary, role.report_daily, role.report_accounting)
    }

    override fun onSuccessMenuOrder(message: String?) {
        view.showMessage(999,message)
    }


}