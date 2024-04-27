package id.kasirvippro.android.feature.manage.packages.item

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.packages.PackagesRestModel

class PackagesItemPresenter(val context: Context, val view: PackagesItemContract.View) : BasePresenter<PackagesItemContract.View>(),
    PackagesItemContract.Presenter, PackagesItemContract.InteractorOutput {

    private var interactor: PackagesItemInteractor = PackagesItemInteractor(this)
    private var categoryRestModel = PackagesRestModel(context)

    override fun onViewCreated() {
        loadProduct()
    }

    override fun loadProduct() {
        val sesi = view.getSesi()
        interactor.callGetProductAPI(context,categoryRestModel,sesi!!)
    }

    fun setCategory(list: List<Product>){
        view.setCategorys(list)
    }

    override fun deleteProduct(id: String,position:Int) {
        val sesi = view.getSesi()
        interactor.callDeleteProductAPI(context,categoryRestModel,id,sesi!!)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetCategories(list: List<Product>) {
        view.setData(list)
    }

    override fun onSuccessDeleteCategory(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
}