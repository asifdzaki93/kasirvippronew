package id.kasirvippro.android.feature.manage.main

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.role.RoleRestModel
import id.kasirvippro.android.models.role.Role

interface ManageContract {

    interface View : BaseViewImpl {
        fun setRole(name_role: String?, add_product: String?, add_category: String?, add_supplier: String?, add_customer: String?, add_discount: String?)
        fun showMessage(code: Int, msg: String?)
        fun openProductPage()
        fun openPackagesPage()
        fun openRawMaterialPage()
        fun openStorePage()
        fun openKelolatokoPage()
        fun openCategoryPage()
        fun openDiscountPage()
        fun openTablePage()
        fun openCustomerPage()
        fun openSupplierPage()
        fun openRolePage()
        fun openStaffPage()
        fun openOngkirPage()
        fun openDivisiPage()
        fun openWebviewPage(title:String,url:String)
        fun loadRole()
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun onCheckStaff()
        fun loadRole()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun getToken(context: Context):String?
        fun getUserLevel(context: Context):String?
        fun getUserPaket(context: Context):String?
        fun callGetRoleAPI(context: Context, restModel: RoleRestModel)

    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onFailedAPI(code:Int,msg:String)
        fun onSuccessGetRole(list:List<Role>)
    }


}