package id.kasirvippro.android.feature.addOn.workManagement.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.models.staff.StaffRestModel

class StaffListPresenter(val context: Context, val view: StaffListContract.View) : BasePresenter<StaffListContract.View>(),
    StaffListContract.Presenter, StaffListContract.InteractorOutput {

    private var interactor = StaffListInteractor(this)
    private var restModel = StaffRestModel(context)


    override fun onViewCreated() {
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
    }

    override fun delete(id: String) {
        interactor.callDeleteAPI(context,restModel,id)
    }

    override fun search(search: String) {
        interactor.onRestartDisposable()
        if(search.isEmpty() || search.isBlank()){
            interactor.callGetDataAPI(context,restModel)
        }
        else{
            interactor.callSearchAPI(context,restModel,search)
        }
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetData(list: List<Staff>) {
        view.setData(list)
    }

    override fun onSuccessDelete(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }


}