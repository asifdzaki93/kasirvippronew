package id.kasirvippro.android.feature.pulsaPpob.productPpob

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.productPpob.ProductPpob
import id.kasirvippro.android.models.productPpob.ProductPpobRestModel


interface ProductPpobContract {

    interface View : BaseViewImpl {
        fun setProduct(list:List<ProductPpob>)
        fun setData(list:List<ProductPpob>)
        fun showErrorMessage(code: Int, msg: String?)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openProductPage(data: ProductPpob)
        fun getJenis():String
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadProduct()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetProductAPI(context: Context, restModel: ProductPpobRestModel, jenis: String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetProduct(list:List<ProductPpob>)
        fun onFailedAPI(code:Int,msg:String)
    }


}