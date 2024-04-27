package id.kasirvippro.android.feature.manage.role.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel


interface RoleListContract {

    interface View : BaseViewImpl {
        fun setRole(list:List<Role>)
        fun setData(list:List<Role>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openEditRolePage(data:Role)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadRole()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetRoleAPI(context: Context, restModel:RoleRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetRole(list:List<Role>)
        fun onFailedAPI(code:Int,msg:String)
    }


}