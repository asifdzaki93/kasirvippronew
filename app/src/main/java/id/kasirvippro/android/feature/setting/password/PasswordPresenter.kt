package id.kasirvippro.android.feature.setting.password

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.user.UserRestModel

class PasswordPresenter(val context: Context, val view: PasswordContract.View) : BasePresenter<PasswordContract.View>(),
    PasswordContract.Presenter, PasswordContract.InteractorOutput {


    private var interactor = PasswordInteractor(this)
    private var restModel = UserRestModel(context)


    override fun onViewCreated() {

    }

    override fun onCheck(lama:String,baru:String,konfirmasi:String) {
        if(lama.isBlank() || lama.isEmpty()){
            view.showMessage(999,"Password must be filled")
            return
        }

        if(lama.length < 6){
            view.showMessage(999,"Password minimum 6 characters")
            return
        }

        if(baru.isBlank() || baru.isEmpty()){
            view.showMessage(999,"New password must be filled")
            return
        }

        if(baru.length < 6){
            view.showMessage(999,"New password at least 6 characters")
            return
        }

        if(konfirmasi.isBlank() || konfirmasi.isEmpty()){
            view.showMessage(999,"Confirm password must be filled")
            return
        }

        if(konfirmasi != baru){
            view.showMessage(999,"Confirm password is wrong")
            return
        }

        interactor.callUpdateAPI(context,restModel,lama,baru)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessAPI(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
    }

}