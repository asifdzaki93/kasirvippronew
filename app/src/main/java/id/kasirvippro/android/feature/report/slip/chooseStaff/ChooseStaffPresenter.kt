package id.kasirvippro.android.feature.report.slip.chooseStaff

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.models.staff.StaffRestModel
import id.kasirvippro.android.utils.AppConstant

class ChooseStaffPresenter(val context: Context, val view: ChooseStaffContract.View) : BasePresenter<ChooseStaffContract.View>(),
    ChooseStaffContract.Presenter, ChooseStaffContract.InteractorOutput {

    private var interactor  = ChooseStaffInteractor(this)
    private var restModel = StaffRestModel(context)
    private var codeKaryawan = AppConstant.Code.CODE_KARYAWAN_GAJI

    override fun onViewCreated(intent: Intent) {
        codeKaryawan = intent.getIntExtra(AppConstant.CODE,AppConstant.Code.CODE_KARYAWAN_GAJI)
        loadData()
    }

    override fun loadData() {
        interactor.callGetDataAPI(context,restModel)
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

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

    override fun getCodeKaryawan(): Int {
        return codeKaryawan
    }


}