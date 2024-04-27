package id.kasirvippro.android.feature.choose.rawMaterial

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.rawMaterial.RawMaterial
import id.kasirvippro.android.models.rawMaterial.RawMaterialRestModel

interface ChooseRawMaterialContract {

    interface View : BaseViewImpl {
        fun setData(list:List<RawMaterial>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun onSelected(data:RawMaterial)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetsAPI(context: Context, restModel:RawMaterialRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGets(list:List<RawMaterial>)
        fun onFailedAPI(code:Int,msg:String)
    }


}