package id.kasirvippro.android.feature.forgot

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.user.UserRestModel
import id.kasirvippro.android.utils.Helper

class ForgotPresenter(val context: Context, val view: ForgotContract.View) : BasePresenter<ForgotContract.View>(),
    ForgotContract.Presenter, ForgotContract.InteractorOutput {

    private var interactor = ForgotInteractor(this)
    private val restModel = UserRestModel(context)

    override fun onViewCreated() {

    }

    override fun onCheck(email:String,telpon:String) {

        if(telpon.isBlank() || telpon.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_phone))
            return
        }

        if(!Helper.isPhoneValid(telpon)){
            view.showMessage(999,context.getString(R.string.err_phone_format))
            return
        }

/*        if(email.isBlank() || email.isEmpty()){
            view.showMessage(999,context.getString(R.string.err_empty_email))
            return
        }

        if(!Helper.isEmailValid(email)){
            view.showMessage(999,context.getString(R.string.err_email_format))
            return
        }*/

        interactor.callForgotAPI(context,restModel,email,telpon)
    }

    override fun onSuccessAPI(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }



}