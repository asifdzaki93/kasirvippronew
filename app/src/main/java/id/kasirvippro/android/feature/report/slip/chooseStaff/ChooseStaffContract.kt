package id.kasirvippro.android.feature.report.slip.chooseStaff

import android.content.Context
import android.content.Intent
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.models.staff.StaffRestModel

interface ChooseStaffContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Staff>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun onSelected(data:Staff)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated(intent: Intent)
        fun onDestroy()
        fun loadData()
        fun search(search:String)
        fun getCodeKaryawan():Int
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDataAPI(context: Context, restModel:StaffRestModel)
        fun callSearchAPI(context: Context, restModel:StaffRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetData(list:List<Staff>)
        fun onFailedAPI(code:Int,msg:String)
    }


}