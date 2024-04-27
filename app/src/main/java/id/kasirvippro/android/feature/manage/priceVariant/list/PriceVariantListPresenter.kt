package id.kasirvippro.android.feature.manage.priceVariant.list

import android.content.Context
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.priceVariant.PriceVariant
import id.kasirvippro.android.models.priceVariant.PriceVariantRestModel
import id.kasirvippro.android.utils.PermissionUtil

class PriceVariantListPresenter(val context: Context, val view: PriceVariantListContract.View) : BasePresenter<PriceVariantListContract.View>(),
    PriceVariantListContract.Presenter, PriceVariantListContract.InteractorOutput {

    private var interactor: PriceVariantListInteractor = PriceVariantListInteractor(this)
    private var restModel = PriceVariantRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var sort = false
    private var id = String()

    override fun onViewCreated() {
       loadProducts()
    }

    override fun loadProducts() {
        val idproduct = view.getIdProduct()
        interactor.callGetProductsAPI(context,restModel,idproduct!!)
    }

    override fun deleteProduct(id: String,position:Int) {
                    interactor.callDeleteProductAPI(context,restModel,id)
    }
    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetProducts(list: List<PriceVariant>) {
        sort = false
        view.setProducts(list)
    }

    fun setProduct(list: List<PriceVariant>){
        view.setProducts(list)
    }

    override fun onSuccessDeleteProduct(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }
    override fun onAddPage() {
        view.openAddPage(id)
    }


}