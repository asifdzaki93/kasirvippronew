package id.kasirvippro.android.feature.hutangpiutang.piutangCustomer

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.customer.Customer
import id.kasirvippro.android.models.hutangpiutang.HutangPiutangRestModel


interface PiutangCustomerContract {

    interface View : BaseViewImpl {
        fun setData(list:List<Customer>)
        fun showErrorMessage(code: Int, msg: String)
        fun reloadData()
        fun openDetailPiutangPage(data: Customer)

    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadHutang()
        fun searchHutang(search:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetHutangAPI(context: Context, restModel:HutangPiutangRestModel)
        fun callSearchHutangAPI(context: Context, restModel:HutangPiutangRestModel, search:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetHutang(list:List<Customer>)
        fun onFailedAPI(code:Int,msg:String)
    }


}