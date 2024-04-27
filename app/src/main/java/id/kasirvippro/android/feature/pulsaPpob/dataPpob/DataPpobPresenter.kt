package id.kasirvippro.android.feature.pulsaPpob.dataPpob

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.productPpob.ProductPpob
import id.kasirvippro.android.models.productPpob.ProductPpobRestModel

class DataPpobPresenter(val context: Context, val view: DataPpobContract.View) : BasePresenter<DataPpobContract.View>(),
    DataPpobContract.Presenter, DataPpobContract.InteractorOutput {

    private var interactor: DataPpobInteractor = DataPpobInteractor(this)
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