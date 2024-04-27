package id.kasirvippro.android.feature.choose.color

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.color.Color
import id.kasirvippro.android.models.color.ColorRestModel

interface ChooseColorContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Color>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun onSelected(data:Color)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetsAPI(context: Context, restModel:ColorRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGets(list:List<Color>)
        fun onFailedAPI(code:Int,msg:String)
    }


}