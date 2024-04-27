package id.kasirvippro.android.feature.addOn.workManagement.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.staff.Staff
import id.kasirvippro.android.models.staff.StaffRestModel


interface StaffListContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Staff>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openAddPage()
        fun openEditPage(data:Staff)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
        fun delete(id:String)
        fun search(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDataAPI(context: Context, restModel:StaffRestModel)
        fun callDeleteAPI(context: Context, restModel:StaffRestModel, id:String)
        fun callSearchAPI(context: Context, restModel:StaffRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetData(list:List<Staff>)
        fun onSuccessDelete(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}