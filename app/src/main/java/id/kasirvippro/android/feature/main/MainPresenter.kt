package id.kasirvippro.android.feature.main

import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel

class MainPresenter(val context: Context, val view: MainContract.View) : BasePresenter<MainContract.View>(),
    MainContract.Presenter, MainContract.InteractorOutput {

    private var interactor = MainInteractor(this)

    private var idMenu:Int = R.id.navigation_home
    private var restModel = UserRestModel(context)
    private var user:User?=null

    override fun onViewCreated() {
        view.selectMenu(idMenu)

        user = interactor.loadProfile(context)
        user?.let {
            view.setInfo(it)
        }

    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


    override fun setSelectedIdMenu(id: Int) {
        idMenu = id
    }

    override fun getSelectedIdMenu(): Int {
        return idMenu
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onLogout() {
        interactor.onLogout()
    }

    override fun onLogoutSuccess() {
        view.restartLoginActivity()
    }


}