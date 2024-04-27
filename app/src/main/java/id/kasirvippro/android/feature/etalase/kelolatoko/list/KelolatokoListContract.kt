package id.kasirvippro.android.feature.etalase.kelolatoko.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.kelolatoko.Kelolatoko
import id.kasirvippro.android.models.kelolatoko.KelolatokoRestModel

interface KelolatokoListContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Kelolatoko>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openEditPage(data:Kelolatoko)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetsAPI(context: Context, restModel:KelolatokoRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGets(list:List<Kelolatoko>)
        fun onFailedAPI(code:Int,msg:String)
    }


}