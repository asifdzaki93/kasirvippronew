package id.kasirvippro.android.feature.addOn.main

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel

class AddOnPresenter(val context: Context, val view: AddOnContract.View) : BasePresenter<AddOnContract.View>(),
    AddOnContract.Presenter, AddOnContract.InteractorOutput {

    private var interactor = AddOnInteractor(this)
    private var userRestModel = UserRestModel(context)
    private var premium:Boolean = false
    private var level:String? = "kasir"

    override fun onViewCreated() {
        premium = "1" == interactor.getUserPaket(context)
        level = interactor.getUserLevel(context)
        when(level){
            "master" -> view.onMasterPage(true)
            "admin" -> view.onAdminPage()
            else -> view.onSalesPage()
        }
        loadProfile()

    }

    override fun loadProfile() {
        interactor.callGetProfileAPI(context,userRestModel)
    }

    override fun onSuccessGetProfile(list: List<User>) {
        if(list.isEmpty()){
            onFailedAPI(999,"Account not found")
            return
        }

        val user = list[0]
        interactor.saveUser(user)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }
}