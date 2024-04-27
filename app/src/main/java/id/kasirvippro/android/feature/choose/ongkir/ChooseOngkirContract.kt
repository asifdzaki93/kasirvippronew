package id.kasirvippro.android.feature.choose.ongkir

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.ongkir.Ongkir
import id.kasirvippro.android.models.ongkir.OngkirRestModel


interface ChooseOngkirContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Ongkir>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun onSelected(data:Ongkir)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDataAPI(context: Context, restModel:OngkirRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetData(list:List<Ongkir>)
        fun onFailedAPI(code:Int,msg:String)
    }
}