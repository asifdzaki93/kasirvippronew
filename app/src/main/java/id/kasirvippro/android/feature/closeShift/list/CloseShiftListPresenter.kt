package id.kasirvippro.android.feature.closeShift.list

import android.app.Activity
import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.events.onReloadTransaction
import id.kasirvippro.android.models.closeShift.CloseShift
import id.kasirvippro.android.models.closeShift.CloseShiftRestModel
import org.greenrobot.eventbus.EventBus

class CloseShiftListPresenter(val context: Context, val view: CloseShiftListContract.View) : BasePresenter<CloseShiftListContract.View>(),
    CloseShiftListContract.Presenter, CloseShiftListContract.InteractorOutput {

    private var interactor = CloseShiftListInteractor(this)
    private var restModel = CloseShiftRestModel(context)
    private var id: String? = null

    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetsAPI(context,restModel)
    }


    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGets(list: List<CloseShift>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

    override fun deleteDetail() {
            interactor.callDeleteDetailAPI(context,restModel,id!!)
    }

    override fun onSuccessDeleteDetail(message: String?) {
        view.showMessage(999,message)
        EventBus.getDefault().post(onReloadTransaction(true))
        view.onClose(Activity.RESULT_OK)
    }
}