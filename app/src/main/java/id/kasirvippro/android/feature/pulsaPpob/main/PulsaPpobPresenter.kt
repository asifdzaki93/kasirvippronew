package id.kasirvippro.android.feature.pulsaPpob.main

import android.content.Context
import android.util.Log
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.user.User
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.utils.AppConstant

class PulsaPpobPresenter(val context: Context, val view: PulsaPpobContract.View) : BasePresenter<PulsaPpobContract.View>(),
    PulsaPpobContract.Presenter, PulsaPpobContract.InteractorOutput {

    private var interactor = PulsaPpobInteractor(this)
    private var premium:Boolean = false
    private var userRestModel = UserRestModel(context)

    override fun onViewCreated() {
        premium = "1" == interactor.getUserPaket(context)
        view.onPremiumPage(true)

        loadProfile()
    }

    override fun loadProfile() {
        interactor.callGetProfileAPI(context,userRestModel)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onSuccessGetProfile(list: List<User>) {
        if(list.isEmpty()){
            onFailedAPI(999,"Akun tidak ditemukan")
            return
        }

        val user = list[0]
        interactor.saveUser(user)
        view.setProfile(user.saldo)
    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun getPremiumUrl(): String {
        val key = interactor.getToken(context)
        val url = AppConstant.URL.TOPUP+key
        Log.d("getPremiumUrl",url)
        return url
    }
}