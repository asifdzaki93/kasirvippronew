package id.kasirvippro.android.feature.initial

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.initial.Initial
import id.kasirvippro.android.models.initial.InitialRestModel

class InitialPresenter(val context: Context, val view: InitialContract.View) : BasePresenter<InitialContract.View>(),
    InitialContract.Presenter, InitialContract.InteractorOutput {


    private var interactor = InitialInteractor(this)
    private var InitialRestModel = InitialRestModel(context)
    private var data:Initial? = null


    override fun onViewCreated() {

    }

    override fun onCheck(modal_awal: String, sift:String) {
        if(modal_awal.isBlank() || modal_awal.isEmpty()){
            view.showMessage(999,"Cash on Hand must be filled")
            return
        }

        if(sift.isBlank() || sift.isEmpty()){
            view.showMessage(999,"Shift is required")
            return
        }
        interactor.callInitialAPI(context,InitialRestModel,modal_awal,sift)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }


    override fun onSuccessEdit(msg: String?) {
        view.showMessage(200,msg)
        view.onClose(Activity.RESULT_OK)
        view.openSuccessPage()
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showMessage(code,msg)
        view.onClose(Activity.RESULT_OK)
    }

}