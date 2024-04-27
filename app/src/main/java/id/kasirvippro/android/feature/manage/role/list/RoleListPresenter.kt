package id.kasirvippro.android.feature.manage.role.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.role.Role
import id.kasirvippro.android.models.role.RoleRestModel

class RoleListPresenter(val context: Context, val view: RoleListContract.View) : BasePresenter<RoleListContract.View>(),
    RoleListContract.Presenter, RoleListContract.InteractorOutput {

    private var interactor: RoleListInteractor = RoleListInteractor(this)
    private var roleRestModel = RoleRestModel(context)

    override fun onViewCreated() {
        loadRole()
        return
    }

    override fun loadRole() {
        interactor.callGetRoleAPI(context,roleRestModel)
    }

    fun setRole(list: List<Role>){
        view.setRole(list)
    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetRole(list: List<Role>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}