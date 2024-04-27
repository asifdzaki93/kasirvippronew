package id.kasirvippro.android.feature.manage.productVariant.list

import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.utils.PermissionUtil

class ProductVariantListPresenter(val context: Context, val view: ProductVariantListContract.View) : BasePresenter<ProductVariantListContract.View>(),
    ProductVariantListContract.Presenter, ProductVariantListContract.InteractorOutput {

    private var interactor: ProductVariantListInteractor = ProductVariantListInteractor(this)
    private var restModel = ProductRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var sort = false
    private var id = String()

    override fun onViewCreated() {
        cameraPermission = object : PermissionCallback{
            override fun onSuccess() {
                view.openScanPage()
            }

            override fun onFailed() {
                view.showErrorMessage(999,context.getString(R.string.reason_permission_camera))
            }
        }
       loadProducts()
    }

    override fun onCheckScan() {
        permissionUtil.checkCameraPermission(cameraPermission)
    }

    override fun loadProducts() {
        val idproduct = view.getIdProduct()
        interactor.callGetProductsAPI(context,restModel,idproduct!!)
    }

    override fun deleteProduct(id: String,position:Int,increment:String) {
                    interactor.callDeleteProductAPI(context,restModel,id)
    }

    override fun searchProduct(search: String) {
        interactor.onRestartDisposable()
        if(search.isNullOrEmpty() || search.isNullOrBlank()){
            val idproduct = view.getIdProduct()
            interactor.callGetProductsAPI(context,restModel,idproduct!!)
        }
        else{
            interactor.callSearchProductAPI(context,restModel,search)
        }
    }

    override fun searchByBarcode(search: String) {
        interactor.callSearchByBarcodeAPI(context,restModel,search)
    }

    override fun onDestroy() {
        interactor.onDestroy()
    }

    override fun onSuccessGetProducts(list: List<Product>) {
        sort = false
        view.setProducts(list)
    }

    fun setProduct(list: List<Product>){
        view.setProducts(list)
    }

    override fun onSuccessDeleteProduct(msg: String?) {
        view.showSuccessMessage(msg)
    }

    override fun onFailedAPI(code: Int, msg: String) {
        view.showErrorMessage(code,msg)
    }

    override fun onSuccessByBarcode(list: List<Product>) {
        if(list.isEmpty()){
            view.showErrorMessage(999,"Produk tidak ditemukan")
            return
        }
        val data = list[0]
        view.openEditPage(data)
    }

    override fun onCheckSort() {
        interactor.onRestartDisposable()
        if(sort){
            val idproduct = view.getIdProduct()
            interactor.callGetProductsAPI(context,restModel, idproduct!!)
        }
        else{
            interactor.callSortProductsAPI(context,restModel)
        }
    }

    override fun onAddPage() {
        view.openAddPage(id)
    }

    override fun onSuccessSort(list: List<Product>) {
        sort = true
        view.setProducts(list)
    }


}