package id.kasirvippro.android.feature.manage.discount.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.discount.Discount
import id.kasirvippro.android.models.discount.DiscountRestModel


interface DiscountListContract {

    interface View : BaseViewImpl {
        fun setDiscounts(list:List<Discount>)
        fun setData(list:List<Discount>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openAddPage()
        fun openEditPage(data:Discount?)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadDiscounts()
        fun deleteDiscount(id:String,position:Int,increment:String)
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetDataAPI(context: Context, restModel:DiscountRestModel)
        fun callDeleteAPI(context: Context, restModel:DiscountRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetData(list:List<Discount>)
        fun onSuccessDelete(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}