package id.kasirvippro.android.feature.manage.role.edit

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel

interface EditRoleContract {

    interface View : BaseViewImpl {
        fun setProfile(name_role: String?, menu_order: String?, menu_purchase: String?, menu_spending: String?, menu_transaction: String?, menu_debt: String?, menu_printlabel: String?, menu_manageorder: String?, menu_managestock: String?, menu_return: String?, menu_addon: String?, menu_othermenu: String?, add_product: String?, add_category: String?, add_supplier: String?, add_customer: String?, add_discount: String?, report_product: String?, report_sumary: String?, report_daily: String?, report_accounting: String?)
        fun showMessage(code: Int, msg: String?)
        fun setInfo(user: Role)
        fun loadData()
        fun getIdRole(): String?
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
        fun setActive(value:String?, id:String?, name:String?)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun getToken(context: Context):String
        fun callGetProfileAPI(context: Context, restModel: RoleRestModel, id:String)
        fun callMenuOrderAPI(context: Context, restModel: RoleRestModel, value:String, id:String, name:String)
        fun onRestartDisposable()
        fun loadData(context: Context):Role?
        fun saveUser(user: Role)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessGetProfile(list:List<Role>)
        fun onSuccessMenuOrder(message: String?)
    }


}