package id.kasirvippro.android.feature.choose.decimal

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.currency.CurrencyRestModel
import id.kasirvippro.android.models.currency.Decimal


interface ChooseDecimalContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Decimal>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun onSelected(data:Decimal)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadData()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDataAPI(context: Context, restModel:CurrencyRestModel)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetData(list:List<Decimal>)
        fun onFailedAPI(code:Int,msg:String)
    }


}