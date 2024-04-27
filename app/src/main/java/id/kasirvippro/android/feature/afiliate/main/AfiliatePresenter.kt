package id.kasirvippro.android.feature.afiliate.main

import android.content.Context
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.base.BasePresenter

class AfiliatePresenter(val context: Context, val view: AfiliateContract.View) : BasePresenter<AfiliateContract.View>(),
    AfiliateContract.Presenter, AfiliateContract.InteractorOutput {

    private var interactor = AfiliateInteractor(this)
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

    override fun onDestroy() {
        interactor.onDestroy()
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



}