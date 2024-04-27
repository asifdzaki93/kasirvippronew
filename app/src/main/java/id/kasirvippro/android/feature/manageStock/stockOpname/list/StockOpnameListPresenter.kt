package id.kasirvippro.android.feature.manageStock.stockOpname.list

import android.content.Context
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BasePresenter
import id.kasirvippro.android.callback.PermissionCallback
import id.kasirvippro.android.models.product.Product
import id.kasirvippro.android.models.product.ProductRestModel
import id.kasirvippro.android.utils.PermissionUtil

class StockOpnameListPresenter(val context: Context, val view: StockOpnameListContract.View) : BasePresenter<StockOpnameListContract.View>(),
    StockOpnameListContract.Presenter, StockOpnameListContract.InteractorOutput {

    private var interactor: StockOpnameListInteractor = StockOpnameListInteractor(this)
    private var restModel = ProductRestModel(context)
    private var permissionUtil: PermissionUtil = PermissionUtil(context)
    private lateinit var cameraPermission: PermissionCallback
    private var sort = false

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
        interactor.callGetProductsAPI(context,restModel)
    }

    override fun searchProduct(search: String) {
        interactor.onRestartDisposable()
        if(search.isNullOrEmpty() || search.isNullOrBlank()){
            interactor.callGetProductsAPI(context,restModel)
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
        view.setProducts(list)
    }

    fun setProduct(list: List<Product>){
        view.setProducts(list)
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


}