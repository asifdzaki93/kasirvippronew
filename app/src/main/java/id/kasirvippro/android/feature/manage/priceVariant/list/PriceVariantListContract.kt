package id.kasirvippro.android.feature.manage.priceVariant.list

import android.content.Context
import id.kasirvippro.android.base.BaseInteractorImpl
import id.kasirvippro.android.base.BaseInteractorOutputImpl
import id.kasirvippro.android.base.BasePresenterImpl
import id.kasirvippro.android.base.BaseViewImpl
import id.kasirvippro.android.models.priceVariant.PriceVariant
import id.kasirvippro.android.models.priceVariant.PriceVariantRestModel


interface PriceVariantListContract {

    interface View : BaseViewImpl {
            fun setProducts(list:List<PriceVariant>)
        fun showErrorMessage(code: Int, msg: String)
        fun showSuccessMessage(msg: String?)
        fun reloadData()
        fun openEditPage(data: PriceVariant)
        fun getIdProduct(): String?
        fun openAddPage(id: String)
    }

    interface Presenter : BasePresenterImpl<View> {
        fun onViewCreated()
        fun onDestroy()
        fun loadProducts()
        fun deleteProduct(id:String,position:Int)
        fun onAddPage()
    }

    interface Interactor : BaseInteractorImpl {
        fun onDestroy()
        fun onRestartDisposable()
        fun callGetProductsAPI(context: Context, restModel:PriceVariantRestModel, id_product:String)
        fun callDeleteProductAPI(context: Context, restModel:PriceVariantRestModel, id:String)
    }

    interface InteractorOutput : BaseInteractorOutputImpl {
        fun onSuccessGetProducts(list:List<PriceVariant>)
        fun onSuccessDeleteProduct(msg: String?)
        fun onFailedAPI(code:Int,msg:String)
    }


}