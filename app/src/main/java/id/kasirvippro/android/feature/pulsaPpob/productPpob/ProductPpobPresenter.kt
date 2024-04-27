package id.kasirvippro.android.feature.pulsaPpob.productPpob

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.productPpob.ProductPpob
import id.kasirvippro.android.models.productPpob.ProductPpobRestModel

class ProductPpobPresenter(val context: Context, val view: ProductPpobContract.View) : BasePresenter<ProductPpobContract.View>(),
    ProductPpobContract.Presenter, ProductPpobContract.InteractorOutput {

    private var interactor: ProductPpobInteractor = ProductPpobInteractor(this)
    private var productPpobRestModel = ProductPpobRestModel(context)

    override fun onViewCreated() {
                    loadProduct()
                    return
    }

    override fun loadProduct() {
        val jenis = view.getJenis()
        interactor.callGetProductAPI(context,productPpobRestModel,jenis)
    }

    fun setProduct(list: List<ProductPpob>){
        view.setProduct(list)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetProduct(list: List<ProductPpob>) {
        view.setData(list)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}